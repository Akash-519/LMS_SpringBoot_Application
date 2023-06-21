package com.spring_project1.library_management_system.Service;

import com.spring_project1.library_management_system.DTO.IssueBookRequestDto;
import com.spring_project1.library_management_system.DTO.IssueBookResponseDto;
import com.spring_project1.library_management_system.Entity.Book;
import com.spring_project1.library_management_system.Entity.LibraryCard;
import com.spring_project1.library_management_system.Entity.Transaction;
import com.spring_project1.library_management_system.Enum.CardStatus;
import com.spring_project1.library_management_system.Enum.TransactionStatus;
import com.spring_project1.library_management_system.Repository.BookRepository;
import com.spring_project1.library_management_system.Repository.CardRepository;
import com.spring_project1.library_management_system.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    // to get information from database and save to database
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    JavaMailSender emailSender;

    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {

       //setting transaction attributes
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssuedOperation(true);



        //we have cardId and bookId from input
        //so get card and book by creating refernce to set it to transaction


        Book book;
        try{
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }
        catch(Exception e)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid book id");
            transactionRepository.save(transaction);
            throw new Exception("Invalid book id");
        }

        LibraryCard card;
        try {
            card = cardRepository.findById(issueBookRequestDto.getCardId()).get();
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid card id");
            transactionRepository.save(transaction);
            throw new Exception("Invalid card id");
        }

        transaction.setBook(book);
        transaction.setCard(card);

        //both card and book are valid
        if(card.getStatus() != CardStatus.ACTIVATED ) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);

            transaction.setMessage("Your card is not Activated");
            transactionRepository.save(transaction);
            throw new Exception("Your card is not activated");
        }
        if(book.isIssued() == true) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);

            transaction.setMessage("Sorry ! Book is already issued.");
            transactionRepository.save(transaction);
            throw new Exception("Sorry ! Book is already issued.");
        }

        //I can issue the book now

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("Transaction was successful");

        //Check all the relational class attributes as well and set the values
        book.setIssued(true);
        book.setCard(card);
        book.getTransaction().add(transaction);
        card.getTransactionList().add(transaction);
        card.getBookIssued().add(book);

        //to save all to database simply save the parent class of all
        cardRepository.save(card);

        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setBookName(book.getTitle());
        issueBookResponseDto.setTransactionId(transaction.getTransactionNumber());
        issueBookResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);

        String text = "Congrats !. "+card.getStudent().getName()+" . You have been issued "+book.getTitle();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("abd.akash630@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("Issue Book Notification");
        message.setText(text);
        emailSender.send(message);

        return issueBookResponseDto;

    }

}
