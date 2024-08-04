package com.tonilr.FinancialTracker.Entities;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private Long account_Id;
	@Column(nullable = false, updatable = true)
	private String account_name;
	@Column(nullable = false, updatable = true)
	private AccountType account_type;
	@Column(nullable = false, updatable = true)
	private double initial_balance;
	@Column(nullable = false, updatable = true)
	private String currency;
	@Column(nullable = false, updatable = true)
	private Date creation_date;
	
	public Account() {
	}

	public Account(String account_name, AccountType account_type, double initial_balance, 
			String currency, Date creation_date) {
		super();
		this.account_name = account_name;
		this.account_type = account_type;
		this.initial_balance = initial_balance;
		this.currency = currency;
		this.creation_date = creation_date;
	}

	public Long getAccount_Id() {
		return account_Id;
	}

	public void setAccount_Id(Long account_Id) {
		this.account_Id = account_Id;
	}

	public String getAccountName() {
		return account_name;
	}

	public void setAccountName(String account_name) {
		this.account_name = account_name;
	}

	public AccountType getAccountType() {
		return account_type;
	}

	public void setAccountType(AccountType account_type) {
		this.account_type = account_type;
	}

	public double getInitialBalance() {
		return initial_balance;
	}

	public void setInitialBalance(double initial_balance) {
		this.initial_balance = initial_balance;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getCreationDate() {
		return creation_date;
	}

	public void setCreationDate(Date creation_date) {
		this.creation_date = creation_date;
	}
}
