package com.casabella.backend.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.casabella.backend.model.Booking;
import com.casabella.backend.model.Contract;
import com.casabella.backend.model.Discount;
import com.casabella.backend.model.Passenger;
import com.casabella.backend.model.User;
import com.casabella.backend.repository.BookingRepository;
import com.casabella.backend.repository.ContractRepository;
import com.casabella.backend.repository.DiscountRepository;
import com.casabella.backend.repository.PassengerRepository;
import com.casabella.backend.repository.UserRepository;
import com.casabella.backend.services.BookingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/bookings")
@RequiredArgsConstructor
public class BookingController {
    
    private final BookingService bookingService;

    private final BookingRepository bookingRepo;

    private final ContractRepository contractRepo;

    private final DiscountRepository discountRepo;

    private final UserRepository userRepo;

    private final PassengerRepository passengerRepo;


    //Add Booking
    @PostMapping("/")
    public ResponseEntity<?> addBooking(@Valid @RequestBody Booking booking){  

        return new ResponseEntity<>(bookingService.postBooking(booking), HttpStatus.CREATED);
    }
    

    //Get All Bookings
    @GetMapping("/")
    public ResponseEntity<List<Booking>> getAllBookings(){
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    //Get one Booking by bookingID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id){
        Booking booking = bookingService.getBookingById(id);
        if(booking == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(booking);
    }

    //Get one booking by UserID
    @GetMapping("/userBookings/{userId}")
    public ResponseEntity<List<Booking>> getBookingByUserId(@PathVariable Long userId){
        List<Booking> bookings = bookingService.getBookingByUserId(userId);
        return bookings.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(bookings);

        // if (bookings.isEmpty()) {
        //     return ResponseEntity.noContent().build();
        // } else {
        //     return ResponseEntity.ok(bookings);
        // }
    }


    //Update Booking
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody Booking booking){
        Booking updatedBooking = bookingService.updateBooking(id, booking);

        if(updatedBooking == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            return ResponseEntity.ok(updatedBooking);
        }
    }


    //Contract - Booking
    @PutMapping("/{bookingId}/contracts/{contractId}")
    Booking assignContractToBooking(
        @PathVariable Long bookingId,
        @PathVariable Long contractId
    ){
        Booking booking = bookingRepo.findById(bookingId).get();
        Contract contract = contractRepo.findById(contractId).get();

        booking.assignContractBooking(contract);
        return bookingRepo.save(booking);
    }

    //Discount - Booking
    @PutMapping("/{bookingId}/discounts/{discountId}")
    Booking assignDiscountToBooking(
        @PathVariable Long bookingId,
        @PathVariable Long discountId
    ){
        Booking booking = bookingRepo.findById(bookingId).get();
        Discount discount = discountRepo.findById(discountId).get();

        booking.assignDiscountBooking(discount);
        return bookingRepo.save(booking);
    }

    //Booking passenger
    @PutMapping("/{bookingId}/passenger/{passengerId}")
    Booking assignPassengerToBooking(
        @PathVariable Long bookingId,
        @PathVariable Long passengerId
    ){
        Booking booking = bookingRepo.findById(bookingId).get();
        Passenger passenger = passengerRepo.findById(passengerId).get();

        booking.assignPassengertoBooking(passenger);
        return bookingRepo.save(booking);
    }

    //User - Booking
    @PutMapping("/{bookingId}/users/{userId}")
    Booking assignUserToBooking(
        @PathVariable Long bookingId,
        @PathVariable Long userId
    ){
        Booking booking = bookingRepo.findById(bookingId).get();
        User user = userRepo.findById(userId).get();

        booking.assignUserBooking(user);
        return bookingRepo.save(booking);
    }


}
