package com.example.FinalProject.controller;

import com.example.FinalProject.dto.dtoPatron;
import com.example.FinalProject.entity.PatronEntity;
import com.example.FinalProject.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")

public class PatronController {

    @Autowired
    private PatronService patronService;

    @GetMapping()
    public List<dtoPatron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<dtoPatron> getPatronById(@PathVariable Long id) {
        PatronEntity patronEntity = patronService.getPatronById(id);
        if (patronEntity != null) {
            // Convert the entity to DTO
            dtoPatron patronDto = new dtoPatron(patronEntity.getMembership_type(), patronEntity.getName(), patronEntity.getEmail()); // Example of field mapping
            return ResponseEntity.ok(patronDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<PatronEntity> createPatron(@RequestBody dtoPatron patron) {
        PatronEntity savedPatron = patronService.savePatron(patron);
        return ResponseEntity.ok(savedPatron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronEntity> updatePatron(@PathVariable Long id, @RequestBody dtoPatron patron) {
        PatronEntity savedPatron = patronService.updatePatron(id, patron);
        return ResponseEntity.ok(savedPatron);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatron(@PathVariable Long id) {
        return patronService.deletePatron(id);
    }

}
