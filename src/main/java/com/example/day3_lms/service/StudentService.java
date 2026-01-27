package com.example.day3_lms.service;

import com.example.day3_lms.model.StudentModel;
import com.example.day3_lms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;

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

    //display
    public List<StudentModel> getStudents() {
        return repository.findAll();
    }

    //update
    public StudentModel updateStudent(String id, StudentModel student ) {
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Student found!"));

        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());

        return repository.save(existingStudent);
    }

    // delete
    public void deleteStudent(String id) {
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Student found!"));

        repository.delete(existingStudent);
    }

}
