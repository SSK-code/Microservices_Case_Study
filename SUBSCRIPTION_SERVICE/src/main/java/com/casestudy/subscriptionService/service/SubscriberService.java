package com.casestudy.subscriptionService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.casestudy.subscriptionService.entity.Book;
import com.casestudy.subscriptionService.exception.ApplicationException;
import com.casestudy.subscriptionService.model.Subscriber;
import com.casestudy.subscriptionService.model.Subscription;
import com.casestudy.subscriptionService.repositories.SubscriberRepository;
import com.casestudy.subscriptionService.repositories.SubscriptionRepository;

@Service
@PropertySource("classpath:App-url.properties")
public class SubscriberService {

	@Value(value = "${bookservice.getbook.url}")
	private String get_book_url;
	
	@Value(value = "${bookservice.update.url}")
	private String update_book_url;
	
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
		
		List<Subscriber> subscriberList = subscriberRepository.findAll();
		if(subscriberList == null || subscriberList.size() == 0) {
			throw new ApplicationException();
		}
		return subscriberList;
	}

	public Subscriber findSubscriberById(Integer id) {
		
		Subscriber subscriber = subscriberRepository.findSubscriberById(id);
		
		if(subscriber == null) {
			throw new ApplicationException("Subscriber with Id " + id + " is not found");
		}
		
		return subscriber;
	}

	@Transactional
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
		
		String serviceURL = get_book_url + bookId;

		ResponseEntity<Book> reponseEntity = restTemplate.exchange(serviceURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<Book>() {
				});

		Book book = reponseEntity.getBody();

		return book.getStock().getAvailableCount();
		
	}
	
	@Transactional
	private void updateBookStock(Integer bookId, String type) {
		
		String serviceURL = update_book_url + "id=" + bookId + "&type=" + type ;

		restTemplate.exchange(serviceURL, HttpMethod.POST, null,
				new ParameterizedTypeReference<Book>() {
				});

	}

	@Transactional
	public void subscriptionCloser(Subscription subscription) {
		
		Subscription existingSubscription = subscriptionRepository.findSubscriberByBookId(subscription.getSubscriptionId(), subscription.getSubscriberId());
		
		if(existingSubscription != null) {
			existingSubscription.setReturnedDate(subscription.getReturnedDate());
			subscriptionRepository.save(existingSubscription);
			updateBookStock(subscription.getBookId(), "plus");
		}
	}
	
	
}
