package com.java.graphql.Models;

public class Book {

    private int bookID;
    private String bookName;
    private int pageCount;

    private int authorID;

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public Book(int bID, String bookName, int pageCount){
        this.bookID = bID;
        this.bookName=bookName;
        this.pageCount=pageCount;
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
