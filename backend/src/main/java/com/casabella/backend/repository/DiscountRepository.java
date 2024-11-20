package com.casabella.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casabella.backend.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long>{

    @Query("SELECT d FROM Discount d WHERE d.contract.contractId IN (SELECT c.contractId FROM Contract c WHERE c.hotel.hotelId = :hotelId)")
    List<Discount> findDiscountsByHotelId(@Param("hotelId") Long hotelId);

    
} 
