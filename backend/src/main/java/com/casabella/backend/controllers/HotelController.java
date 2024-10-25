package com.casabella.backend.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.casabella.backend.model.Hotel;
import com.casabella.backend.services.HotelService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/hotels")
@Validated
public class HotelController {

    private final HotelService hotelService;

    
    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/search")
    public List<Hotel> searchHotels(
        @RequestParam int guestCount,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkinDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkoutDate){
            return hotelService.findRoomsAvailableHotels(guestCount, checkinDate, checkoutDate);
    }

    @PostMapping("/")
    public ResponseEntity<?> postHotel(@Valid @RequestBody Hotel hotel){  

        // if (!hotel.getHotel_email().contains("@")) {
        //     return new ResponseEntity<>("Email must contain '@' sign", HttpStatus.BAD_REQUEST);
        // }
        // if (!hotel.getHotel_email().contains(".")){
        //     return new ResponseEntity<>("Email missing '.' sign ", HttpStatus.BAD_REQUEST);
        // }
        // if(hotel.getHotel_contact().length() != 10){
        //     return new ResponseEntity<>("Phone number invalid", HttpStatus.BAD_REQUEST);
        // }
        // if (hotel.getStar_rating() < 1 || hotel.getStar_rating() > 5) {
        //     return new ResponseEntity<>("Star rating must be between 1 and 5", HttpStatus.BAD_REQUEST);
        // }

        return new ResponseEntity<>(hotelService.postHotel(hotel), HttpStatus.CREATED);
    }

    //All hotels
    @GetMapping("/")
    public ResponseEntity<List<Hotel>> getAllHotels(){
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    //Hotel by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable Long id){
        Hotel hotel1 = hotelService.getHotelById(id);
        if(hotel1 == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(hotel1);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel){
        Hotel updatedHotel = hotelService.updateHotel(id, hotel);

        if(updatedHotel == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            return ResponseEntity.ok(updatedHotel);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long id){
        try{
            hotelService.deleteHotel(id);
            return new ResponseEntity<>("hotel with ID " + id + " deleted successfully", HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public HotelService getHotelService() {
        return hotelService;
    }
}
