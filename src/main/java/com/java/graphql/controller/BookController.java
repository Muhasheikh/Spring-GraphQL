package com.java.graphql.controller;


import com.java.graphql.Models.Author;
import com.java.graphql.Models.Book;
import com.java.graphql.utils.DatabaseConn;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController  {

    private final DatabaseConn conn;

    public BookController(DatabaseConn conn) {
        this.conn = conn;
    }


    @QueryMapping
    public List<Book> books() throws Exception {
        return conn.getBookList();
    }

    @QueryMapping
    public Optional<Book> bookByID(@Argument int bookID) throws Exception{
        try {
            return conn.getBook(bookID);

        }catch (Exception e){
            throw new Exception(e);
        }
    }
//    @QueryMapping
//    public List<String> bookByAuthor() throws Exception{
//    }

    @QueryMapping
    public Author author(Book book) throws Exception {
        System.out.println(book.toString());
        Author author=  conn.authorByBook(book.getBookID());
        System.out.println(author.getAuthorID());
        return author;
    }

}
