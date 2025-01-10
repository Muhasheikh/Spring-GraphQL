package com.java.graphql.utils;

import com.java.graphql.Models.Author;
import com.java.graphql.Models.Book;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DatabaseConn {

    private static final String URL = "jdbc:mysql://localhost:3306/studentmanagement?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    private final static String BOOKS_VALUES = "INSERT INTO books VALUES (?,?,?)";
    private final static String Author_VALUES = "INSERT INTO author VALUES (?,?)";



    private Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL,USERNAME, PASSWORD);
    }

    public List<String> BookbyAuthor() throws Exception{
        String query = "SELECT * FROM Author left join author on books.authorID=author.authorID;";
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<String> data = new ArrayList<>();
        while (resultSet.next()){
            data.add (resultSet.getNString("bookName") + " : " +
                    (resultSet.getString("authorName")) );

        }
        statement.close();
        connection.close();
        return data;
    }

    public List<Author> insertAuthor(List<Author> authors) throws  Exception{


        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(Author_VALUES);
        int count = 1;
        for(int i=0;i<authors.size();i++){
            statement.setInt(1,authors.get(i).getAuthorID());
            statement.setString(2, authors.get(i).getAuthorName());
            count = statement.executeUpdate();
        }
        System.out.println("rows affected "+count);
        return authors;
    }

   public void insertBook(Book book) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(BOOKS_VALUES);
        statement.setInt(1,book.getBookID());
        statement.setString(2, book.getBookName());
        statement.setInt(3,book.getPageCount());
       int count = statement.executeUpdate();
       System.out.println("rows affected "+count);
        statement.close();
        connection.close();
    }

    public Optional<Book> getBook(int bookID) throws Exception {

             String query = "SELECT * FROM books WHERE bookID="+bookID;
             Book book = new Book() ;

            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            book.setBookID(bookID);
            book.setBookName(rs.getString(2));
            book.setPageCount(rs.getInt(3));
            statement.close();
            connection.close();
            return Optional.of(book);

    }

    public List<Book> getBookList() throws Exception {

        String query = "SELECT * FROM books";
        List<Book> bookList = new ArrayList<>();

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
       while (rs.next()){
           Book book = new Book();

           book.setBookID(rs.getInt(1));
           book.setBookName(rs.getString(2));
           book.setPageCount(rs.getInt(3));
           bookList.add(book);
       }
        System.out.println(bookList);
        statement.close();
        connection.close();
        return bookList;

    }


    public Author authorByBook(int authorID) throws Exception{
        String query = "SELECT a.authorID, a.authorName " +
                "FROM author a " +
                "LEFT JOIN books ON books.authorID = a.authorID " +
                "WHERE books.authorID = " + authorID;
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        Author author = new Author();
        resultSet.next();
        author.setAuthorID(resultSet.getInt(1));
        author.setAuthorName(resultSet.getString(2));
        statement.close();
        connection.close();
        return author;
    }


}
