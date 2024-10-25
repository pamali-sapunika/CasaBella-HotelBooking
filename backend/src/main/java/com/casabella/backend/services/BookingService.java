package com.casabella.backend.services;


import java.util.*;
import org.springframework.stereotype.Service;
import com.casabella.backend.model.Booking;
import com.casabella.backend.repository.BookingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepo;

    //Add Booking
    public Booking postBooking(Booking booking){
        return bookingRepo.save(booking);
    }

    //Get All Bookings
    public List<Booking> getAllBookings(){
        return bookingRepo.findAll();
    }

    //Get Booking by ID
    public Booking getBookingById(Long id){
        return bookingRepo.findById(id).orElse(null);
    }

    //Update Booking
    public Booking updateBooking(Long id, Booking booking) {
    Optional<Booking> optionalBooking = bookingRepo.findById(id);
    
    if (optionalBooking.isPresent()) {
        Booking existingBooking = optionalBooking.get();

        existingBooking.setCheckinDate(booking.getCheckinDate());
        existingBooking.setCheckoutDate(booking.getCheckoutDate());
        existingBooking.setBookingDate(booking.getBookingDate());
        existingBooking.setAdultCount(booking.getAdultCount());
        existingBooking.setChildCount(booking.getChildCount());
        existingBooking.setTotalAmount(booking.getTotalAmount());
        existingBooking.setDiscountAmount(booking.getDiscountAmount());
        existingBooking.setNetAmount(booking.getNetAmount());
        existingBooking.setPrepaymentAmount(booking.getPrepaymentAmount());
        existingBooking.setBalancedAmount(booking.getBalancedAmount());
        existingBooking.setStatus(booking.getStatus());

        return bookingRepo.save(existingBooking);
    }
    return null;
    }
}
