package com.example.FinalProject.services;

import com.example.FinalProject.dto.dtoPatron;
import com.example.FinalProject.entity.PatronEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PatronService {

    PatronEntity savePatron(dtoPatron patron);

    List<dtoPatron> getAllPatrons();

    PatronEntity getPatronById(Long id);

}
