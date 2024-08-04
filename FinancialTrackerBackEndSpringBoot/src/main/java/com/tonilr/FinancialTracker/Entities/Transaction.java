package com.tonilr.FinancialTracker.Entities;

import java.sql.Date;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private Long transaction_Id;
	@Column(nullable = false, updatable = true)
	private double amount;
	@Column(nullable = false, updatable = true)
	private Date date;
	@Column(nullable = false, updatable = true)
	private String description;
	@Column(nullable = false, updatable = true)
	private Date register_date;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_Id",nullable = true)
    private User user;

    
	
	public Transaction() {
	}

	public Transaction(double amount, Date date, String description, Date register_date) {
		super();
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.register_date = register_date;

	}

	public Long getTransaction_Id() {
		return transaction_Id;
	}

	public void setTransaction_Id(Long transaction_Id) {
		this.transaction_Id = transaction_Id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getRegisterDate() {
		return register_date;
	}

	public void setRegisterDate(Date register_date) {
		this.register_date = register_date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
