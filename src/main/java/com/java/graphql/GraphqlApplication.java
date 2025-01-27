package com.java.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraphqlApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(GraphqlApplication.class, args);

//		Book bookNew = new Book(2,"Bible",300);
//		DatabaseConn conn = new DatabaseConn();
////		conn.insertBook(bookNew);
//		//Book book = conn.getBook(1);
//		List<Book> books = conn.getBookList();
//		for(Book book : books){
//			System.out.println(book);
//		}

//		List<Author> authorList = Arrays.asList(new Author(1,"Allah"),
////				new Author(2,"Jesus"));
//		DatabaseConn conn = new DatabaseConn();
//		System.out.println(conn.authorByBook(1));


	}
}
