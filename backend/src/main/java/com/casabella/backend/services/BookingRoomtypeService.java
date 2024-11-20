package com.casabella.backend.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.casabella.backend.model.Booking;
import com.casabella.backend.model.BookingRoomtypes;
import com.casabella.backend.model.SeasonalRoomtype;
import com.casabella.backend.repository.BookingRepository;
import com.casabella.backend.repository.BookingRoomtypeRepository;
import com.casabella.backend.repository.SeasonalRoomtypeRepo;
import jakarta.transaction.Transactional;


@Service
public class BookingRoomtypeService {

    public final BookingRepository bookingRepo;
    public final SeasonalRoomtypeRepo seasonalRoomtypeRepo;
    public final BookingRoomtypeRepository bookingRoomtypeRepo;

    @Autowired
    public BookingRoomtypeService(BookingRepository bookingRepo, SeasonalRoomtypeRepo seasonalRoomtypeRepo,
            BookingRoomtypeRepository bookingRoomtypeRepo) {
        this.bookingRepo = bookingRepo;
        this.seasonalRoomtypeRepo = seasonalRoomtypeRepo;
        this.bookingRoomtypeRepo = bookingRoomtypeRepo;
    }

    //Save Roomtype to Booking
    @Transactional
    public ResponseEntity<BookingRoomtypes> addRoomtypeToBooking(Long bookingId, Long seasonalRoomtypeId, BookingRoomtypes bookingRoomtype){
        Booking booking = bookingRepo.findById(bookingId)
            .orElseThrow(() -> new IllegalStateException("Booking id with " + bookingId + " not found"));
        SeasonalRoomtype seasonalRoomtype = seasonalRoomtypeRepo.findById(seasonalRoomtypeId)
            .orElseThrow(() -> new IllegalStateException("Seasonal ROomtype with id " + seasonalRoomtypeId + " not found"));

        bookingRoomtype.setBooking(booking);
        bookingRoomtype.setPrice(seasonalRoomtype.getPrice());
        bookingRoomtype.setSeasonalRoomtype(seasonalRoomtype);

        bookingRoomtypeRepo.save(bookingRoomtype);
        return ResponseEntity.ok(bookingRoomtype);
    
    }

    // //Get BookingROomtype by Bookingid
    // public List<BookingRoomtypes> getBookingRoomtypesByBookingId(Long bookingId){
    //     return bookingRoomtypeRepo.findRoomsByBookingId(bookingId);
    // }

    //Get all Booking ROomtypes
    public List<BookingRoomtypes> getBookingRoomtypes(){
        return bookingRoomtypeRepo.findAll();
    }


    




    
    
}
