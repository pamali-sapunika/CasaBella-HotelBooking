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

import com.casabella.backend.model.BookingRoomtypes;
import com.casabella.backend.services.BookingRoomtypeService;

@RestController
@RequestMapping("/v1/bookingroomtypes")
public class BookingRoomtypeController {

    
    public final BookingRoomtypeService bookingRoomtypeService;

    @Autowired
    public BookingRoomtypeController(BookingRoomtypeService bookingRoomtypeService) {
        this.bookingRoomtypeService = bookingRoomtypeService;
    }

    //Get all BookingRoomtypes
    @GetMapping
    public List<BookingRoomtypes> getBookingRoomtypes(){
        return bookingRoomtypeService.getBookingRoomtypes();
    }

    // //get Booking roomtypes by booking id
    // @GetMapping("/bookingRoomsOf/{bookingId}")
    // public List<BookingRoomtypes> getBookingRoomtypesByBookingId(@PathVariable Long bookingId){
    //     return bookingRoomtypeService.getBookingRoomtypesByBookingId(bookingId);
    // }

    
    //Add Booking roomtypes
    @PostMapping("/{bookingId}/seasonalRoomtype/{seasonalRoomtypeId}")
    public ResponseEntity<BookingRoomtypes> addRoomtypeToBooking(
        @PathVariable Long bookingId,
        @PathVariable Long seasonalRoomtypeId,
        @RequestBody BookingRoomtypes bookingRoomtype
    ){
        return bookingRoomtypeService.addRoomtypeToBooking(bookingId, seasonalRoomtypeId, bookingRoomtype);
    }

    


    
}
