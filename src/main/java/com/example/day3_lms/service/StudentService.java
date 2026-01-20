package com.example.day3_lms.service;

import com.example.day3_lms.model.StudentModel;
import com.example.day3_lms.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    //Create
    public StudentModel addStudent(StudentModel student) {
        return repository.save(student); //add function of repository
    }
}
