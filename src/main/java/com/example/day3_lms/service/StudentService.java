package com.example.day3_lms.service;

import com.example.day3_lms.dto.StudentRequestDTO;
import com.example.day3_lms.dto.StudentResponseDTO;
import com.example.day3_lms.exception.StudentNotFoundException;
import com.example.day3_lms.model.StudentModel;
import com.example.day3_lms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    //Create
    public StudentResponseDTO addStudent(StudentRequestDTO dto) {
        StudentModel student = new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel saved = repository.save(student);

        return new StudentResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()
        );//add function of repository
    }

    //display
    //public List<StudentModel> getStudents() {
    //    return repository.findAll();
    //}

    public List<StudentResponseDTO> getStudents() {
        return repository.findAll()
                .stream()
                .map(s -> new StudentResponseDTO(
                        s.getId(),
                        s.getName(),
                        s.getAge(),
                        s.getEmail()
                )).toList();
    }

    //update
    public StudentResponseDTO updateStudent(String id, StudentRequestDTO student) {
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Student Found"));

        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());

        StudentModel updatedStudent = repository.save(existingStudent);

        // Model → Response DTO
        StudentResponseDTO response = new StudentResponseDTO(
                updatedStudent.getId(),
                updatedStudent.getName(),
                updatedStudent.getAge(),
                updatedStudent.getEmail()
        );

        return response;
    }

    // delete
    public StudentResponseDTO deleteStudent(String id) {

        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        // Prepare response DTO before deleting
        StudentResponseDTO response = new StudentResponseDTO(
                existingStudent.getId(),
                existingStudent.getName(),
                existingStudent.getAge(),
                existingStudent.getEmail()
        );

        repository.delete(existingStudent);

        return response;
    }

    public StudentResponseDTO patchStudent(String id, Map<String, Object> updates) {
        StudentModel student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (updates.containsKey("name")) {
            student.setName((String) updates.get("name"));
        }

        if (updates.containsKey("age")) {
            student.setAge((Integer) updates.get("age"));
        }

        if (updates.containsKey("email")) {
            student.setEmail((String) updates.get("email"));
        }

        StudentModel updated = repository.save(student);

        return new StudentResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getAge(),
                updated.getEmail()
        );
    }

}
