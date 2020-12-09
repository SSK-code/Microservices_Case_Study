package com.casestudy.bookService.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.casestudy.bookService.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	
	@Query(value = "select * from Book where book_name = :book_name", nativeQuery = true)
	public List<Book> findBookByBookName(@Param(value = "book_name") String book_name);

	@Query(value = "select * from Book where book_id = :book_id", nativeQuery = true)
	public Book findBookByBookId(@Param(value = "book_id") Integer book_id);
	
	@EntityGraph(value = "graph.book.stock")
	public List<Book> findAll();
}
