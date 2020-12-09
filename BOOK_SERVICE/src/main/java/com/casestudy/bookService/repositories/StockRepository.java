package com.casestudy.bookService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.bookService.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
	
}
