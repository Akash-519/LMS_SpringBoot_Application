package com.spring_project1.library_management_system.Entity;

import com.spring_project1.library_management_system.Enum.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Library;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String transactionNumber;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    @CreationTimestamp
    private Date transactionDate;
    private String message;

    private boolean isIssuedOperation;// transaction is to recieve book or return the book

    //mapping to book
    @ManyToOne
    @JoinColumn
    Book book;

    //mapping to LibraryCard
    @ManyToOne
    @JoinColumn
    LibraryCard card;
}
