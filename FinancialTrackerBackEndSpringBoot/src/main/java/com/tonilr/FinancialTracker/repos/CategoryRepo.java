package com.tonilr.FinancialTracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tonilr.FinancialTracker.Entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long>{

}
