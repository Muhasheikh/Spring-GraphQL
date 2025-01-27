package com.java.graphql.service;

import com.java.graphql.Models.Book;
import com.java.graphql.repository.BookRepository;
import com.java.graphql.service.impl.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class iBookServiceTest {

    private iBookService bookService;

   @Mock
   private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookRepository);
    }


    @Test
    void getAllBooks() {

        //given
        List<Book> bookList = Arrays.asList(new Book(1,"Book",101,null),
                new Book(2,"Book2",10,null));
        Mockito.when(bookRepository.findAll()).thenReturn(bookList);

        //when
        List<Book>  getBooks = bookService.getBooks();
        //
        Assertions.assertThat(bookList.get(1).getBookID()).isEqualTo(getBooks.get(1).getBookID());
    }

    @Test
    @Disabled
    void findBookByID() {

    }

    @Test
    @Disabled
    void findAuthorByBook() {
    }

    @Test
    void testsaveBook() {

        //given
        Book book = new Book(1,"Test",10,null);
        Mockito.when(bookRepository.save(book)).thenReturn(book);

        //when
        Book saveBook = bookService.saveBook(book);

        //
        Assertions.assertThat(saveBook.getBookID()).isGreaterThan(0);
        Assertions.assertThat(saveBook.getBookName()).isEqualTo("Test");
    }

    @Test
    void testsaveBookFailWithExceptions() {

        //given
        Book book = new Book(1,"",10,null);

       NullPointerException exception =  assertThrows(NullPointerException.class,()-> bookService.saveBook(book));
        assertEquals("Invalid Names"+ book.getBookName(),exception.getMessage());
    }

    @Test
    @Disabled
    void updateBook() {
    }
}