package com.spring_project1.library_management_system.Controller;

import com.spring_project1.library_management_system.Entity.Author;
import com.spring_project1.library_management_system.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public String addAuthor(@RequestBody() Author author)
    {
        authorService.addAuthor(author);
        return "Author has been added";
    }

    @GetMapping("/get_authors")
    public List<Author> getAuthors()
    {
        return authorService.getAuthors();
    }


}
