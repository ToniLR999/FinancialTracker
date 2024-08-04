package com.tonilr.FinancialTracker.repos;

import org.springframework.stereotype.Repository;

import com.tonilr.FinancialTracker.Entities.Account;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface AccountRepo extends JpaRepository<Account,Long>{

}
