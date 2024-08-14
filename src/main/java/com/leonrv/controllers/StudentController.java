package com.leonrv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

import com.leonrv.models.Student;
import com.leonrv.services.IStudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private IStudentService service;

    @GetMapping
    public ResponseEntity<?> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping
    public ResponseEntity<Student> update(@Valid @RequestBody Student updated) {
        return ResponseEntity.ok(service.save(updated));
    }

    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody Student created) {
        return ResponseEntity.ok(service.save(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }
}
