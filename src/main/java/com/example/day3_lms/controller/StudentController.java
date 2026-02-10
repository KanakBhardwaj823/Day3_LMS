package com.example.day3_lms.controller;

import com.example.day3_lms.Util.JwtUtil;
import com.example.day3_lms.dto.StudentRequestDTO;
import com.example.day3_lms.dto.StudentResponseDTO;
import com.example.day3_lms.model.StudentModel;
import com.example.day3_lms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins="*")
@RestController
public class StudentController {
    private final StudentService service;
    private final JwtUtil jwtUtil;

    public StudentController(StudentService service, JwtUtil jwtUtil) {

        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    private void checkToken(String authHeader) {
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid Token");
        }

        String token = authHeader.substring(7);
        jwtUtil.validateTokenAndGetEmail(token);
    }
    // create function api
    @PostMapping("/students")
    public StudentResponseDTO addStudent(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody StudentRequestDTO student) {
        checkToken(authHeader);
        return service.addStudent(student);
    }

    //display function api
    @GetMapping("/students")
    public List<StudentResponseDTO> getStudents(
            @RequestHeader(value = "Authorization", required = false)  String authHeader)
    {
        checkToken(authHeader);
        return service.getStudents();
    }

    //updating function api
    @PutMapping("/update/{id}")
    public StudentResponseDTO updateStudent(@PathVariable String id, @RequestBody StudentRequestDTO student) {
        return service.updateStudent(id, student);
    }

    // delete function api
    @DeleteMapping("/delete/{id}")
    public StudentResponseDTO deleteStudent(@PathVariable String id) {
        return service.deleteStudent(id);
    }


    @PatchMapping("/patch/{id}")
    public StudentResponseDTO patchStudent(@PathVariable String id,
                                           @RequestBody Map<String, Object> updates) {
        return service.patchStudent(id, updates);
    }

}

