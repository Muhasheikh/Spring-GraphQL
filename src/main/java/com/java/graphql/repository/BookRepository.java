package com.java.graphql.repository;

import com.java.graphql.Models.Author;
import com.java.graphql.Models.Book;
import com.java.graphql.Models.BookInput;
import com.java.graphql.Models.UpdateBookInput;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query("SELECT a FROM Book b JOIN b.author a WHERE b.bookID = ?1")
    Author findAuthorByBook(int bookID);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.bookName = :bookName, b.pageCount = :pageCount WHERE b.bookID = :bookID")
    int updateBookByBookID(@Param("bookID") int bookID, @Param("bookName") String bookName, @Param("pageCount") int pageCount);
}
