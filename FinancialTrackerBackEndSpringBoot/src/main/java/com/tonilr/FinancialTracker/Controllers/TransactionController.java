package com.tonilr.FinancialTracker.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tonilr.FinancialTracker.Entities.Transaction;
import com.tonilr.FinancialTracker.Services.TransactionServices;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	private final TransactionServices transactionService;
	
	public TransactionController(TransactionServices transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		List<Transaction> transactions = transactionService.findAllTransactions();
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Long id) {
		Transaction transaction = transactionService.findTransactionById(id);
		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
		Transaction newTransaction = transactionService.addTransaction(transaction);
		return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction) {
		Transaction updateTransaction = transactionService.updateTransaction(transaction);
		return new ResponseEntity<>(updateTransaction, HttpStatus.OK);
	}
	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTransaction(@PathVariable("id") Long id) {
		transactionService.deleteTransaction(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
