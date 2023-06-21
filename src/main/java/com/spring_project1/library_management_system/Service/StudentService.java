package com.spring_project1.library_management_system.Service;

import com.spring_project1.library_management_system.DTO.StudentReponseDto;
import com.spring_project1.library_management_system.DTO.StudentRequestDto;
import com.spring_project1.library_management_system.DTO.StudentUpdateEmailRequestDto;
import com.spring_project1.library_management_system.Entity.LibraryCard;
import com.spring_project1.library_management_system.Entity.Student;
import com.spring_project1.library_management_system.Enum.CardStatus;
import com.spring_project1.library_management_system.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public void addStudent(StudentRequestDto studentRequestDto)
    {
        //I have to set student to the repository
        //create a student object
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setEmail(studentRequestDto.getEmail());

        //have to set library card as wee
        //create a card object
        LibraryCard card = new LibraryCard();
        card.setStatus(CardStatus.ACTIVATED);
        student.setCard(card);
        card.setStudent(student);

        //lastly save  student to to repository , then it will save to database
        studentRepository.save(student);



      /*  //all attributes are taken care already by sql(id) and
        // remaining name,email,department etc is set by user through postman
        //except librarycard details
        //so we are setting all the LibraryCard attributes here
        //and we set this card details to student
        LibraryCard card = new LibraryCard();
        card.setStatus(CardStatus.ACTIVATED);
        card.setValidTill("03/2025");
        card.setStudent(student);

        //finally setting card attribute here
        student.setCard(card);

        //No all the attributes of Student class is set so save it to repo
        studentRepository.save(student);

       */

    }
    public Student getStudentByEmail(String email)
    {
        return studentRepository.findByEmail(email);
    }
//    public List<Student> getByAge(int age)
//    {
//        return studentRepository.findByAge(age);
//    }

    public StudentReponseDto updateEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto)
    {
        Student student = studentRepository.findById(studentUpdateEmailRequestDto.getId()).get();
        student.setEmail(studentUpdateEmailRequestDto.getEmail());

        //updating the student in database with new email
        Student updatedStudent = studentRepository.save(student);

        //converting updated student to response dto
        StudentReponseDto studentReponseDto = new StudentReponseDto();
        studentReponseDto.setEmail(updatedStudent.getEmail());
        studentReponseDto.setId(updatedStudent.getId());
        studentReponseDto.setName(updatedStudent.getName());

        //returning reponse dto object
        return studentReponseDto;
    }

}

