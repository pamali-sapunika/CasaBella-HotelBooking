package com.casabella.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casabella.backend.model.BookingRoomtypes;

public interface BookingRoomtypeRepository extends JpaRepository<BookingRoomtypes, Long>{
    
}
