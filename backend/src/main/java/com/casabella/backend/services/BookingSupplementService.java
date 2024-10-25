package com.casabella.backend.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.casabella.backend.model.Booking;
import com.casabella.backend.model.BookingSupplements;
import com.casabella.backend.model.SeasonalSupplement;
import com.casabella.backend.repository.BookingRepository;
import com.casabella.backend.repository.BookingSupplementRepository;
import com.casabella.backend.repository.SeasonalSupplementRepository;

import jakarta.transaction.Transactional;

@Service
public class BookingSupplementService {

    private final BookingSupplementRepository bookingSupplementRepo;
    private final SeasonalSupplementRepository seasonalSupplementRepo;
    private final BookingRepository bookingRepo;

    @Autowired
    public BookingSupplementService(BookingSupplementRepository bookingSupplementRepo,
            SeasonalSupplementRepository seasonalSupplementRepo, BookingRepository bookingRepo) {
        this.bookingSupplementRepo = bookingSupplementRepo;
        this.seasonalSupplementRepo = seasonalSupplementRepo;
        this.bookingRepo = bookingRepo;
    }


    //Add supplement to booking
    @Transactional
    public ResponseEntity<BookingSupplements> addSupplementToBooking(Long bookingId, Long seasonalSupplementId, BookingSupplements bookingSupplement){
        SeasonalSupplement seasonalSupplement = seasonalSupplementRepo.findById(seasonalSupplementId)
            .orElseThrow(() -> new IllegalStateException("Seasonal SUpplement with ID" + seasonalSupplementId + " not found"));
        Booking booking = bookingRepo.findById(bookingId)
            .orElseThrow(() -> new IllegalStateException("Booking ID with" + bookingId + " not found"));

        bookingSupplement.setBooking(booking);
        bookingSupplement.setPricePerUnit(seasonalSupplement.getPricePerUnit());
        bookingSupplement.setSeasonalSupplement(seasonalSupplement);

        bookingSupplementRepo.save(bookingSupplement);
        return ResponseEntity.ok(bookingSupplement);
    }

    
    //get all bookings
    public List<BookingSupplements> getBookingSupplements(){
        return bookingSupplementRepo.findAll();
    }




    
}
