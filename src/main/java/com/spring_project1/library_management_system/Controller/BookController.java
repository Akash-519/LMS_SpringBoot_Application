package com.spring_project1.library_management_system.Controller;

import com.spring_project1.library_management_system.DTO.BookRequestDto;
import com.spring_project1.library_management_system.DTO.BookResponseDto;
import com.spring_project1.library_management_system.Entity.Book;
import com.spring_project1.library_management_system.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public BookResponseDto addBook(@RequestBody() BookRequestDto bookRequestDto) throws Exception {
        return bookService.addBook(bookRequestDto);
    }
}
