package com.java.graphql.repository;

import com.java.graphql.Models.Author;
import com.java.graphql.Models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Test
    public void authorFindByBookIDExists() {


        //given
        Author author = new Author(1,"Muhassan");
        authorRepository.save(author);

        Book book = new Book(1,"TestBook",100,author);
        bookRepository.save(book);

        int bookID = 1;

        //when
        Author Resultauthor = bookRepository.findAuthorByBook(bookID);

        //then
        Author expectedAuthor = new Author(1,"Muhassan");
        assertThat(expectedAuthor.getAuthorID()).isEqualTo(Resultauthor.getAuthorID());
        assertThat(Resultauthor.getAuthorID()).isGreaterThan(0);
    }


    @Test
    public void authorFindByBookIDNotExists() {
        //given
        int bookID = 1;

        //when
        Author Resultauthor = bookRepository.findAuthorByBook(bookID);

        //then
        assertThat(Resultauthor.getAuthorID()).isGreaterThan(0);
    }

    @Test
    public void updateBookByBookID() {

        Book book = new Book(1,"TestBook",100,null);
        bookRepository.save(book);

        int update = bookRepository.updateBookByBookID(2,"Muha",20);

        assertThat(update).isGreaterThan(0);
    }
}