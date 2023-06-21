package com.spring_project1.library_management_system.Repository;

import com.spring_project1.library_management_system.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    //custom funcitons based on the attributes
    Student findByEmail(String email);
    List<Student> findByAge(int age);
}

