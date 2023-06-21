package com.spring_project1.library_management_system.Service;

import com.spring_project1.library_management_system.DTO.BookRequestDto;
import com.spring_project1.library_management_system.DTO.BookResponseDto;
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
    public BookResponseDto addBook(BookRequestDto bookRequestDto) throws Exception {

        //it is a new book so we need create book object
        Book book = new Book();

        //as author is not new and already present so we need reference
       Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();

       book.setTitle(bookRequestDto.getTitle());
       book.setPrice(bookRequestDto.getPrice());
       book.setGenre(bookRequestDto.getGenre());
       book.setIssued(false);
       //as there is a relation between author and book so we also set author to book
       book.setAuthor(author);

       //as this book is writen by author so author also should update book list
        author.getBooks().add(book);
        authorRepository.save(author);

        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setPrice(book.getPrice());

        return bookResponseDto;

        }
}
