package com.casestudy.bookService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casestudy.bookService.model.Book;
import com.casestudy.bookService.model.Stock;
import com.casestudy.bookService.repositories.BookRepository;
import com.casestudy.bookService.repositories.StockRepository;

@Service
public class BookService {
	
    @Autowired
	private BookRepository bookRepository;
    
    @Autowired
    private StockRepository stockRepository;
    
    public List<Book> findAllBooks() {
    	
    	List<Book> bookList = bookRepository.findAll();
    	return bookList;
    }

    @Transactional
	public void insertBooks(Book book) {
		
	 bookRepository.save(book);
		
	}

	public List<Book> findBookByName(String name) {
		
		return bookRepository.findBookByBookName(name);
	}

	public Book findBookById(Integer id) {
		return bookRepository.findBookByBookId(id);
	}

	public void updateBooks(Integer id, String type) {
		Stock stock = null;
		if(type != null && type != "") {
			stock = findBookById(id).getStock();
			if(type.equalsIgnoreCase("plus")) {
				stock.setAvailableCount(stock.getAvailableCount() + 1);
			}
			if(type.equalsIgnoreCase("minus")) {
				stock.setAvailableCount(stock.getAvailableCount() - 1);
			}
		}
		
		if(stock != null) {
			stockRepository.save(stock);
		}
	}

	public void deleteBooks(Integer bookId) {
		
		Book book = bookRepository.findBookByBookId(bookId);
		
		if(book != null) {
			bookRepository.delete(book);
		}
		
	}

	public void addBooks(Integer bookId, Integer amount) {
		
		Book book = bookRepository.findBookByBookId(bookId);
		
		if(book != null) {
			book.getStock().setTotalCount(book.getStock().getTotalCount() + amount);
			bookRepository.save(book);
		}
	}
}
