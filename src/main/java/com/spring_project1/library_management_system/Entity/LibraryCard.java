package com.spring_project1.library_management_system.Entity;

import com.spring_project1.library_management_system.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardNo;
    private String validTill;
    @Enumerated(EnumType.STRING)
    private CardStatus status;

    //Setting student and libraryCard relation
    @OneToOne
    @JoinColumn
    Student student;


}
