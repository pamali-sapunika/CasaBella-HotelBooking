package com.casabella.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casabella.backend.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long>{

    
} 
