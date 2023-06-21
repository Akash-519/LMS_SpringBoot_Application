package com.spring_project1.library_management_system.DTO;

import com.spring_project1.library_management_system.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class IssueBookResponseDto {
    private String transactionId;
    private String bookName;
    private TransactionStatus transactionStatus;
}
