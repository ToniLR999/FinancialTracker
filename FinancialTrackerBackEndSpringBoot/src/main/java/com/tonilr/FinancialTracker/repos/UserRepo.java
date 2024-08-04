package com.tonilr.FinancialTracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tonilr.FinancialTracker.Entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{

}
