package com.spring_project1.library_management_system.Entity;

import com.spring_project1.library_management_system.Enum.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private Department department;
    private String email;

    //setting student relation to card
    @OneToOne(mappedBy = "student" , cascade = CascadeType.ALL)
    LibraryCard card;


}
