package com.casestudy.subscriptionService.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subscriber {

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="contact_no")
	private String contactNo;
	
	private transient List<Subscription> subscription;
	
		public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Subscription> getSubscription() {
		return subscription;
	}

	public void setSubscription(List<Subscription> subscription) {
		this.subscription = subscription;
	}
	
}
