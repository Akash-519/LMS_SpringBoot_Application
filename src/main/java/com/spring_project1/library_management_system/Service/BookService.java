package com.spring_project1.library_management_system.Service;

import com.spring_project1.library_management_system.Entity.Book;
import com.spring_project1.library_management_system.Repository.AuthorRepository;
import com.spring_project1.library_management_system.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import com.spring_project1.library_management_system.Entity.Author;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    public void addBook(Book book) throws Exception {
        Author author;
        try {
             author = authorRepository.findById(book.getAuthor().getId()).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        List<Book> booksWritten = author.getBooks();
        booksWritten.add(book);

        authorRepository.save(author);

        }
}
