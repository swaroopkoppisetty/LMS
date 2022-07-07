package com.project.lms.services;

import com.project.lms.models.entities.Student;
import com.project.lms.models.requests.AddStudentRequest;
import com.project.lms.models.responses.CommonDTO;
import com.project.lms.repos.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CommonDTO addStudent(AddStudentRequest addStudentRequest) {
        CommonDTO commonDTO = new CommonDTO();
        Student student = addStudentRequest.toStudent();
        studentRepository.save(student);
        commonDTO.setStatusCode(HttpStatus.OK.toString());
        commonDTO.setMessage("Student added successfully");
        return commonDTO;
    }
}
