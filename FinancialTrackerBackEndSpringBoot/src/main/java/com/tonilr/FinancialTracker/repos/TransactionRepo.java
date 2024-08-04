package com.tonilr.FinancialTracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tonilr.FinancialTracker.Entities.Transaction;


@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long>{

}
