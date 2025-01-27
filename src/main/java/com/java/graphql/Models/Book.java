package com.java.graphql.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

    @Id
    private int bookID;
    private String bookName;
    private int pageCount;
    @ManyToOne
    private Author author;


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book(int bID, String bookName, int pageCount){
        this.bookID = bID;
        this.bookName=bookName;
        this.pageCount=pageCount;
    }

    public Book(int bookID,String bookName, int pageCount, Author author) {
        this.bookName = bookName;
        this.pageCount = pageCount;
        this.author = author;
        this.bookID =bookID;
    }

    public Book(){};

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }



    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookName='" + bookName + '\'' +
                ", pageCount=" + pageCount +
                '}';
    }
}
