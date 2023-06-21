package com.spring_project1.library_management_system.Controller;

import com.spring_project1.library_management_system.DTO.StudentReponseDto;
import com.spring_project1.library_management_system.DTO.StudentRequestDto;
import com.spring_project1.library_management_system.DTO.StudentUpdateEmailRequestDto;
import com.spring_project1.library_management_system.Entity.Student;
import com.spring_project1.library_management_system.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public String addStudent(@RequestBody() StudentRequestDto studentRequestDto)
    {
    studentService.addStudent(studentRequestDto);
    return "Student has been added";
    }
    //get mapping by custom defined findByEmail function

    @GetMapping("/get_by_email")
    public String getStudentByEmail(@RequestParam("email") String email)
    {
       Student student =  studentService.getStudentByEmail(email);
       return student.getName();
    }

    //get mapping by custom defined findByAge function
//    @GetMapping("/get_by_age")
//    public List<Student> getByAge(@RequestParam("age") int age)
//    {
//
//        return studentService.getByAge(age);
//
//    }

    @PutMapping("/update_email")
    public StudentReponseDto updateEmail(@RequestBody() StudentUpdateEmailRequestDto studentUpdateEmailRequestDto)
    {
        return studentService.updateEmail(studentUpdateEmailRequestDto);
    }

}
