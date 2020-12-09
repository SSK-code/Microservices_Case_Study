package com.casestudy.subscriptionService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.casestudy.subscriptionService.entity.Book;
import com.casestudy.subscriptionService.model.Subscriber;
import com.casestudy.subscriptionService.model.Subscription;
import com.casestudy.subscriptionService.repositories.SubscriberRepository;
import com.casestudy.subscriptionService.repositories.SubscriptionRepository;

@Service
public class SubscriberService {

	@Autowired
	SubscriberRepository subscriberRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public List<Subscriber> findAllSubscribers() {
		return subscriberRepository.findAll();
	}

	public Subscriber findSubscriberById(Integer id) {
		return subscriberRepository.findSubscriberById(id);
	}

	public void insertSubscription(Subscription subscription) {
		
		Subscription existingSubscription = subscriptionRepository.findSubscriberByBookId(subscription.getSubscriptionId(), subscription.getSubscriberId());
		
		if(existingSubscription == null) {
			if(getAvailableStockOfBook(subscription.getBookId()) > 0) {
				subscriptionRepository.save(subscription);
				updateBookStock(subscription.getBookId(), "minus");
			}
		}
		
	}
	
	private Integer getAvailableStockOfBook(Integer bookId) {
		
		String serviceURL = "http://localhost:8081/books/getbook/id/" + bookId;

		ResponseEntity<Book> reponseEntity = restTemplate.exchange(serviceURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<Book>() {
				});

		Book book = reponseEntity.getBody();

		return book.getStock().getAvailableCount();
		
	}
	
	private void updateBookStock(Integer bookId, String type) {
		
		String serviceURL = "http://localhost:8081/books/update?id=" + bookId + "&type=" + type ;

		restTemplate.exchange(serviceURL, HttpMethod.POST, null,
				new ParameterizedTypeReference<Book>() {
				});

	}

	public void subscriptionCloser(Subscription subscription) {
		
		Subscription existingSubscription = subscriptionRepository.findSubscriberByBookId(subscription.getSubscriptionId(), subscription.getSubscriberId());
		
		if(existingSubscription != null) {
			existingSubscription.setReturnedDate(subscription.getReturnedDate());
			subscriptionRepository.save(existingSubscription);
			updateBookStock(subscription.getBookId(), "plus");
		}
	}
	
	
}
