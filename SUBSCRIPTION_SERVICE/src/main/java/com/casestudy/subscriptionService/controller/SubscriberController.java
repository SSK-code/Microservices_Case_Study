package com.casestudy.subscriptionService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.subscriptionService.model.Subscriber;
import com.casestudy.subscriptionService.model.Subscription;
import com.casestudy.subscriptionService.service.SubscriberService;

@RestController
@RequestMapping("/subscription")
public class SubscriberController {

	@Autowired
	SubscriberService subscriberService;
	
	@GetMapping("/allsubscriber")
	public List<Subscriber> getAllSubscriber() {
		return subscriberService.findAllSubscribers();
	}
	
	@GetMapping("/getsubscriber/{id}")
	public Subscriber getSubscriberById(@PathVariable Integer id) {
		return subscriberService.findSubscriberById(id);
	}
	
	@PostMapping("/addsubscription")
	public void saveSubscription(@RequestBody Subscription subscription) {
		subscriberService.insertSubscription(subscription);
	}
	
	@PostMapping("/closesubscription")
	public void closeSubscription(@RequestBody Subscription subscription) {
		subscriberService.subscriptionCloser(subscription);
	}
}
