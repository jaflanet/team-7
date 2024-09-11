package com.example.FinalProject.services.impl;

import com.example.FinalProject.dto.dtoPatron;
import com.example.FinalProject.entity.PatronEntity;
import com.example.FinalProject.repository.PatronRepository;
import com.example.FinalProject.services.PatronService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Slf4j
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronRepository patronRepository;

    @Override
    public List<dtoPatron> getAllPatrons() {
        List<PatronEntity> patronEntities = patronRepository.findAll();

        // Convert entity to DTO using stream and mapping
        List<dtoPatron> dtoPatrons = patronEntities.stream()
                .map(entity -> new dtoPatron(entity.getMembership_type(), entity.getName(), entity.getEmail())) // Example of mapping fields
                .collect(Collectors.toList());

        return dtoPatrons;
    }

    public PatronEntity getPatronById(Long Id) {
        return patronRepository.findById(Id).orElse(null);
    }

    @Override
    public PatronEntity savePatron(dtoPatron createPatron) {
        // Validate email with regex
        if (!isValidEmail(createPatron.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        LocalDate currentTimestamp = LocalDate.now();

        PatronEntity patron = new PatronEntity();
        patron.setName(createPatron.getName());
        patron.setEmail(createPatron.getEmail());
        patron.setMembership_type(createPatron.getMembership_type());
        patron.setUpdated_at(currentTimestamp);
        patron.setCreated_at(currentTimestamp);

        return patronRepository.save(patron);
    }

    // Method to validate email using regex
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

}
