package com.example.FinalProject.controller;

import com.example.FinalProject.dto.dtoPatron;
import com.example.FinalProject.entity.PatronEntity;
import com.example.FinalProject.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
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
        dtoPatron patronDto = new dtoPatron(patronEntity.getMembership_type(), patronEntity.getName(), patronEntity.getEmail());
        return ResponseEntity.ok(patronDto);
    }

    @PostMapping()
    public ResponseEntity<PatronEntity> createPatron(@RequestBody dtoPatron patron) {
        PatronEntity savedPatron = patronService.savePatron(patron);
        return ResponseEntity.ok(savedPatron);
    }

}
