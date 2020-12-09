package com.casestudy.subscriptionService.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subscription {

	@Id
	@Column(name="subscription_id")
	private Integer subscriptionId;
	
	@Column(name="subscribed_date")
	private Date subscribedDate;
	
	@Column(name="returned_date")
	private Date returnedDate;
	
	@Column(name="book_id")
	private Integer bookId;
	
	@Column(name="subscriber_id")
	private Integer subscriberId;

	public Integer getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Date getSubscribedDate() {
		return subscribedDate;
	}

	public void setSubscribedDate(Date subscribedDate) {
		this.subscribedDate = subscribedDate;
	}

	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}
}
