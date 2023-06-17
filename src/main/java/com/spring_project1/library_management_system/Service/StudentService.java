package com.spring_project1.library_management_system.Service;

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
    public void addStudent(Student student)
    {
        //all attributes are taken care already by sql(id) and
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
    }
}
