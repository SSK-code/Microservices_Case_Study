package com.casestudy.bookService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock {

	@Id
	@Column(name="book_id")
	private Integer bookId; 
	
	@Column(name="available_count")
	private Integer availableCount;
	
	@Column(name="total_count")
	private Integer totalCount;
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getAvailableCount() {
		return availableCount;
	}
	public void setAvailableCount(Integer availableCount) {
		this.availableCount = availableCount;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
}
