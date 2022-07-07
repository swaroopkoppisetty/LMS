package com.project.lms.controllers;

import com.project.lms.models.requests.AddStudentRequest;
import com.project.lms.models.responses.CommonDTO;
import com.project.lms.services.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add-student")
    public CommonDTO addStudent(@RequestBody @Valid AddStudentRequest addStudentRequest){
        return studentService.addStudent(addStudentRequest);
    }
}
