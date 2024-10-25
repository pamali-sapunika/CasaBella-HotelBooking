package com.casabella.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casabella.backend.model.BookingSupplements;

public interface BookingSupplementRepository extends JpaRepository<BookingSupplements, Long> {
    
}
