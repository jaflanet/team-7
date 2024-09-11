package com.example.FinalProject.services.impl;

import com.example.FinalProject.dto.dtoPatron;
import com.example.FinalProject.entity.PatronEntity;
import com.example.FinalProject.repository.PatronRepository;
import com.example.FinalProject.services.PatronService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

}
