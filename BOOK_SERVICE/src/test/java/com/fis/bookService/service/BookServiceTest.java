package com.fis.bookService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.casestudy.bookService.model.Book;
import com.casestudy.bookService.model.Stock;
import com.casestudy.bookService.repositories.BookRepository;
import com.casestudy.bookService.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;
	
	@Mock
	private BookRepository bookRepository;
	
	private Book book1;
	private Book book2;
	private Stock stock1;
	private Stock stock2;
	private List<Book> returedBook;
	
	@BeforeEach
	public void setup() {
		book1 = new Book();
		book1.setBookId(1);
		book1.setBookName("XYZ");
		book1.setAuthor("Schwartz");
		
		stock1 = new Stock();
		stock1.setBookId(1);
		stock1.setTotalCount(10);
		stock1.setAvailableCount(5);
		book1.setStock(stock1);
		
		
		book2 = new Book();
		book2.setBookId(2);
		book2.setBookName("XYZ");
		book2.setAuthor("Touchstone");
		
		stock2 = new Stock();
		stock2.setBookId(2);
		stock2.setTotalCount(10);
		stock2.setAvailableCount(5);
		book2.setStock(stock2);
		
		returedBook = new ArrayList<Book>();
		returedBook.add(book1);
		returedBook.add(book2);
	}
	
	@Test
	public void getBookByBookIdShouldReturnBook() {
		
		Mockito.when(bookRepository.findBookByBookId(Mockito.eq(1))).thenReturn(book1);
		
		Book returnedBook = bookService.findBookById(1);
		assertEquals(1, returnedBook.getBookId());
		assertEquals(1, returnedBook.getStock().getBookId());
		Mockito.verify(bookRepository, times(1)).findBookByBookId(1);
	}
	
	
	  @Test 
	  public void getBookByBookIdShouldRetrnNull() {
		  Mockito.when(bookRepository.findBookByBookId(Mockito.eq(3))).thenReturn(null); 
		  Book returnedBook = bookService.findBookById(3);
		  assertEquals(null, returnedBook); 
	  }
	  
	  
	
	@Test
	public void getBookByBookNameReturnListOfBooks() {

		Mockito.when(bookRepository.findBookByBookName(Mockito.any())).thenReturn(returedBook);

		List<Book> returnedbook = bookService.findBookByName("XYZ");
		assertEquals(book1, returnedbook.get(0));
		Mockito.verify(bookRepository, times(1)).findBookByBookName("XYZ");
	}

	@Test
	public void getAllBoosShouldReturnAllListOfBooks() {
		Mockito.when(bookRepository.findAll()).thenReturn(returedBook);
		List<Book> returnedUser = bookService.findAllBooks();

		assertEquals(book1, returnedUser.get(0));
		Mockito.verify(bookRepository, Mockito.times(0)).findBookByBookName(Mockito.eq("XYZ"));
		Mockito.verify(bookRepository, Mockito.times(1)).findAll();
	}
	 
	 
}
