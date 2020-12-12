package com.fis.bookService.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.casestudy.bookService.controller.BookController;
import com.casestudy.bookService.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	@Mock
	private BookService bookService;
	
	@InjectMocks
	private BookController bookController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}
	
	@Test
	void getBookByIdShouldReturnStatusIsOk() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/getbook/id/1").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	
		Mockito.verify(bookService, Mockito.times(1)).findBookById(Mockito.eq(1));
	}
	
	@Test
	void getAllBooksShouldReturnStatusIsOk() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/allbooks").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	
		Mockito.verify(bookService, Mockito.times(1)).findAllBooks();
	}
}
