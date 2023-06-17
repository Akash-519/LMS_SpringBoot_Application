package com.spring_project1.library_management_system.Controller;

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
    public String addBook(@RequestBody() Book book)
    {
        try{
            bookService.addBook(book);
        } catch (Exception e) {
            throw new RuntimeException(" Book not added");
        }

        return "Book added Successfully";
    }
}
