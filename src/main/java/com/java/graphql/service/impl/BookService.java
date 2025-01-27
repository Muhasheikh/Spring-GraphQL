package com.java.graphql.service.impl;

import com.java.graphql.Models.Author;
import com.java.graphql.Models.Book;
import com.java.graphql.Models.BookInput;
import com.java.graphql.Models.UpdateBookInput;
import com.java.graphql.repository.BookRepository;
import com.java.graphql.service.iBookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements iBookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }



    @Override
    public List<Book> getBooks() {
        return    bookRepository.findAll();
    }

    @Override
    public Book findBookByID(int id) {
        return bookRepository.findById(id).orElseGet(()-> null);
    }

    @Override
    public Author findAuthorByBook(int bookID) {
        return bookRepository.findAuthorByBook(bookID);
    }

    @Override
    public Book saveBook(Book book )  {
        boolean validation = validateBookName(book.getBookName());
            if(validation){
                return bookRepository.save(book);

            }else {
                throw  new NullPointerException("Invalid Name" + book.getBookName());
            }




    }

    private boolean validateBookName(String bookName) {
        if(bookName==null || bookName.equals("")){
            return false;
        }

        return true;
    }

    @Override
    public void updateBook(int bookID, UpdateBookInput bookInput) {


            int updatedRows = bookRepository.updateBookByBookID(bookID,bookInput.bookName(),bookInput.pageCount());

            if(updatedRows==0){
                System.out.println("Not updated");
            }else {
                System.out.println("Updated");
            }
    }


}
