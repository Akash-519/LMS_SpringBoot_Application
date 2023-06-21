package com.spring_project1.library_management_system.DTO;

import com.spring_project1.library_management_system.Enum.Department;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class StudentRequestDto {
    private String name;
    private String email;
    private int age;
    private Department department;
}
