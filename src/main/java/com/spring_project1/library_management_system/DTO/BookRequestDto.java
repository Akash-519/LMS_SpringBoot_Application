package com.spring_project1.library_management_system.DTO;

import com.spring_project1.library_management_system.Enum.Genre;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class BookRequestDto {
    private String title;
    private int price;
    private Genre genre;
    private int authorId;
}
