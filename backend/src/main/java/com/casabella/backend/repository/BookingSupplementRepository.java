package com.casabella.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casabella.backend.model.BookingSupplements;

public interface BookingSupplementRepository extends JpaRepository<BookingSupplements, Long> {

    @Query("select bs from BookingSupplements bs where bs.booking.id = :bookingId")
    List<BookingSupplements> findSupplementsByBookingId(@Param("bookingId")Long bookingId);
    
}
