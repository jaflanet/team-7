package com.example.FinalProject.controller;

import com.example.FinalProject.dto.dtoPatron;
import com.example.FinalProject.entity.PatronEntity;
import com.example.FinalProject.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping("/{id}")
//    public ResponseEntity<dtoPatron> getPatronById(@PathVariable Long id) {
//        PatronEntity patron = patronService.getPatronById(id);
//        if (patron != null) {
//            return ResponseEntity.ok(patron);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

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
}
