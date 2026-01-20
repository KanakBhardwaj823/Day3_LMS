package com.example.day3_lms.repository;

import com.example.day3_lms.model.StudentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<StudentModel, String> {

}
