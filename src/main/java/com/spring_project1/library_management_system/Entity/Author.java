package com.spring_project1.library_management_system.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private double mobNo;
    private String email;

    //as books can be many to one author so we use list of books
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<Book> books = new ArrayList<>();


}
