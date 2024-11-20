package com.casabella.backend.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casabella.backend.model.BookingSupplements;
import com.casabella.backend.services.BookingSupplementService;

@RestController
@RequestMapping("/v1/bookingsupplements")
public class BookingSupplementController {

    private final BookingSupplementService bookingSupplementService;

    @Autowired
    public BookingSupplementController(BookingSupplementService bookingSupplementService) {
        this.bookingSupplementService = bookingSupplementService;
    }

    //Get all Booking Supplements
    @GetMapping
    public List<BookingSupplements> getBookingSupplements(){
        return bookingSupplementService.getBookingSupplements();
    }

    // //Get Booking Supplements by Booking id
    // @GetMapping("/bookingSupplementsOf/{bookingId}")
    // public List<BookingSupplements> getBookingSupplementsByBookingId(@PathVariable Long bookingId){
    //     return bookingSupplementService.getBookingSupplementsByBookingId(bookingId);
    // }

    //Add Booking supplement
    @PostMapping("/{bookingId}/seasonalSupplement/{seasonalSupplementId}")
    public ResponseEntity<BookingSupplements> addSupplementToBooking(
        @PathVariable Long bookingId,
        @PathVariable Long seasonalSupplementId,
        @RequestBody BookingSupplements bookingSupplement
    ){
        return bookingSupplementService.addSupplementToBooking(bookingId, seasonalSupplementId, bookingSupplement);
    }

    
    
}
