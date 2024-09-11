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
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    @Override
    public PatronEntity updatePatron(Long id, dtoPatron createPatron) {
        PatronEntity patron = patronRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patron not found"));

        LocalDate currentTimestamp = LocalDate.now();

        if (createPatron.getName() != null) {
            patron.setName(createPatron.getName());
        }

        if (createPatron.getEmail() != null) {
            patron.setEmail(createPatron.getEmail());
        }

        if (createPatron.getMembership_type() != null) {
            patron.setMembership_type(createPatron.getMembership_type());
        }

        patron.setUpdated_at(LocalDate.from(currentTimestamp));

        return patronRepository.save(patron);
    }

    //Delete Patron by Id
//    @Override
//    public ResponseEntity<String> deletePatron(Long id) {
//        if (patronRepository.findById(id).isPresent()) {
//            patronRepository.deleteById(id);
//            return new ResponseEntity<>("Patron deleted successfully.", HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>("Failed to delete", HttpStatus.NOT_FOUND);
//    }

    @Override
    public ResponseEntity<String> deletePatron(Long id) {
        int rowsAffected = patronRepository.deletePatron(id);

        if (rowsAffected > 0) {
            return new ResponseEntity<>("Patron deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete patron with active loans.", HttpStatus.NOT_FOUND);
        }
    }

}
