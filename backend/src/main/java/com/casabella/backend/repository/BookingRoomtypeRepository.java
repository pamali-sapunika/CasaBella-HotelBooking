package com.casabella.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casabella.backend.model.Booking;
import com.casabella.backend.model.BookingRoomtypes;

public interface BookingRoomtypeRepository extends JpaRepository<BookingRoomtypes, Long>{

    @Query("SELECT brt FROM BookingRoomtypes brt WHERE brt.booking.id = :bookingId")
    List <BookingRoomtypes> findRoomsByBookingId(@Param("bookingId")Long bookingId);
    
}
