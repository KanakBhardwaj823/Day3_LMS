package com.example.day3_lms.controller;

import com.example.day3_lms.dto.StudentRequestDTO;
import com.example.day3_lms.dto.StudentResponseDTO;
import com.example.day3_lms.model.StudentModel;
import com.example.day3_lms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // create function api
    @PostMapping("/add-student")
    public StudentResponseDTO addStudent(@Valid @RequestBody StudentRequestDTO student) {
        return service.addStudent(student);
    }

    //display function api
    @GetMapping("/students")
    public List<StudentResponseDTO> getStudents() {
        return service.getStudents();
    }

    //updating function api
    @PutMapping("/update/{id}")
    public StudentModel updateStudent(@PathVariable String id, @RequestBody StudentModel student) {
        return service.updateStudent(id, student);
    }

    // delete function api
//    @DeleteMapping("/delete/{id}")
//    public String deleteStudent(@PathVariable String id) {
//        service.deleteStudent(id);
//        return "Student deleted successfully!";
//    }

    // delete function api
    @DeleteMapping("/delete/{id}")
    public StudentResponseDTO deleteStudent(@PathVariable String id) {
        return service.deleteStudent(id);
    }




}

