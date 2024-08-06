package com.tonilr.FinancialTracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tonilr.FinancialTracker.Entities.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users,Long>{

}
