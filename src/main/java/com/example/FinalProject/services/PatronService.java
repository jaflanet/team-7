package com.example.FinalProject.services;

import com.example.FinalProject.dto.dtoPatron;
import com.example.FinalProject.entity.PatronEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface PatronService {

    PatronEntity savePatron(dtoPatron patron);

    PatronEntity updatePatron(Long id, dtoPatron patronRequest);

    List<dtoPatron> getAllPatrons();

    PatronEntity getPatronById(Long id);

    ResponseEntity<String> deletePatron(Long id);

    List<Map<String, Object>> getPatronBorrowingHistory(Long id);

    List<Map<String, Object>> getPatronCurrentBorrowing(Long id);
}
