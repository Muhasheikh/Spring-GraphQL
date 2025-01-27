package com.java.graphql.service;

import com.java.graphql.Models.Author;
import com.java.graphql.Models.Book;
import com.java.graphql.Models.BookInput;
import com.java.graphql.Models.UpdateBookInput;

import java.util.List;

public interface iBookService {

    List<Book> getBooks();

    Book findBookByID(int id);

    Author findAuthorByBook(int bookID);

    Book saveBook(Book book);

    void updateBook(int bookID, UpdateBookInput bookInput);
}
