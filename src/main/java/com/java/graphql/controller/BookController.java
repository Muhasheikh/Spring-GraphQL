package com.java.graphql.controller;


import com.java.graphql.Models.Author;
import com.java.graphql.Models.Book;
import com.java.graphql.Models.BookInput;
import com.java.graphql.Models.UpdateBookInput;
import com.java.graphql.service.iBookService;
import com.java.graphql.utils.DatabaseConn;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController  {

    private final DatabaseConn conn;

    private final iBookService bookService;
    public BookController(DatabaseConn conn, iBookService bookService) {
        this.conn = conn;
        this.bookService = bookService;
    }

    @MutationMapping
    public Book saveBook(@Argument BookInput book){
        return bookService.saveBook(new Book(book.bookID(),book.bookName(),book.pages(),book.author()));
    }

    @MutationMapping
    public String updateBookByID(@Argument int bookID,@Argument UpdateBookInput updateBookInput){
        Book book = bookService.findBookByID(bookID);
        if(book.getBookID()==bookID){
           bookService.updateBook(bookID,updateBookInput);
           return "";
        }

        return null;
    }

    @QueryMapping
    public List<Book> books() throws Exception {
//        return conn.getBookList();
        return bookService.getBooks();
    }

    @QueryMapping
    public Book bookByID(@Argument int bookID) throws Exception{
//        try {
//            return conn.getBook(bookID);
//
//        }catch (Exception e){
//            throw new Exception(e);
//        }
        return bookService.findBookByID(bookID);

    }
//    @QueryMapping
//    public List<String> bookByAuthor() throws Exception{
//    }

    @QueryMapping
    public Author author(@Argument Book book) throws Exception {
//        System.out.println(book.toString());
//        Author author=  conn.authorByBook(book.getBookID());
//        System.out.println(author.getAuthorID());
        return bookService.findAuthorByBook(book.getBookID());
    }




}
