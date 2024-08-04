package com.tonilr.FinancialTracker.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tonilr.FinancialTracker.Entities.Account;
import com.tonilr.FinancialTracker.exceptions.UserNotFoundException;
import com.tonilr.FinancialTracker.repos.AccountRepo;

@Service
public class AccountServices {

	@Autowired
	private final AccountRepo accountRepo;

	public AccountServices(AccountRepo accountRepo) {
		this.accountRepo = accountRepo;
	}

	public Account addAccount(Account account) {
		return accountRepo.save(account);
	}

	public List<Account> findAllAccounts() {
		return accountRepo.findAll();
	}

	public Account updateAccount(Account account) {
		return accountRepo.save(account);
	}

	public Account findAccountById(Long id) {
		return accountRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Account by id " + id + " was not found"));

	}

	public void deleteAccount(Long id) {
		accountRepo.deleteById(id);
	}
}
