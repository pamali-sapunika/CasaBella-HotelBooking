package com.casabella.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casabella.backend.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    
} 