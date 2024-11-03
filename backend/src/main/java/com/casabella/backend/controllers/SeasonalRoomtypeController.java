package com.casabella.backend.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casabella.backend.dto.AvailabilityDTO;
import com.casabella.backend.model.Hotel;
import com.casabella.backend.model.SeasonalRoomtype;
import com.casabella.backend.repository.HotelRepository;
import com.casabella.backend.repository.SeasonalRoomtypeRepo;
import com.casabella.backend.services.SeasonalRoomtypeService;

@RestController
@RequestMapping("/v1/seasonalroomtypes")
public class SeasonalRoomtypeController {

    @Autowired
    private SeasonalRoomtypeService seasonalRoomtypeService;

    @Autowired
    private SeasonalRoomtypeRepo seasonalRoomtypeRepo;

    @Autowired
    private HotelRepository hotelRepo;

    public SeasonalRoomtypeController(SeasonalRoomtypeService seasonalRoomtypeService) {
        this.seasonalRoomtypeService = seasonalRoomtypeService;
    }

    //Add Roomtype to Season
    @PostMapping("/{roomtypeId}/seasons/{seasonId}")
    public ResponseEntity<SeasonalRoomtype> addRoomtypeToSeason(
        @PathVariable Long seasonId,
        @PathVariable Long roomtypeId,
        @RequestBody Map<String, Object> requestBody
    ){
        double price = ((Number) requestBody.get("price")).doubleValue();
        int noofRooms = ((Number) requestBody.get("noofRooms")).intValue();
        int maxAdults = ((Number) requestBody.get("maxAdults")).intValue();
        int noofReservedRooms = ((Number) requestBody.get("noofReservedRooms")).intValue();

        SeasonalRoomtype createdSeasonalRoomtype = seasonalRoomtypeService.addRoomtypeToSeason(price, noofRooms, maxAdults, noofReservedRooms, seasonId, roomtypeId);
        return ResponseEntity.ok(createdSeasonalRoomtype);
    }

    //Display Availabilty
    @GetMapping("/availability")
    public List<AvailabilityDTO> showRoomtypes(
        @RequestParam Long hotelId,
        @RequestParam int guestCount,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkinDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkoutDate
    ){
        return seasonalRoomtypeService.findAvailableSeasonalRoomtypes(hotelId, guestCount, checkinDate, checkoutDate);
    }

    
    @GetMapping
    public List<SeasonalRoomtype> getSeasonalRoomtypes(){
        return seasonalRoomtypeService.getSeasonalRoomtype();
    }

    @GetMapping("/{seasonalRoomtypeId}")
    public ResponseEntity<SeasonalRoomtype> getSeasonalRoomtypeById(@PathVariable Long seasonalRoomtypeId){
        SeasonalRoomtype seasonalRoomtype = seasonalRoomtypeService.getSeasonalRoomtypeById(seasonalRoomtypeId);
        return ResponseEntity.ok(seasonalRoomtype);
    }

    //One to Many - Hotel
    @PutMapping("/{seasonalRoomtypeId}/hotels/{hotelId}")
    SeasonalRoomtype assignHotelToSeasonlaRoomtype(
        @PathVariable Long seasonalRoomtypeId, 
        @PathVariable Long hotelId
    ){
        SeasonalRoomtype seasonalRoomtype = seasonalRoomtypeRepo.findById(seasonalRoomtypeId).get();
        Hotel hotel = hotelRepo.findById(hotelId).get();
        // contract.assignHotel(hotel);
        seasonalRoomtype.setHotel(hotel);
        return seasonalRoomtypeRepo.save(seasonalRoomtype);
    }
    
}


















// @PostMapping("/seasons/{seasonId}/roomtypes/{roomtypeId}")
// public ResponseEntity<String> addRoomtypeToSeason(
//     @PathVariable Long seasonId,
//     @PathVariable Long roomtypeId,
//     @RequestParam double price,
//     @RequestParam int noofRooms,
//     @RequestParam int maxAdults,
//     @RequestParam int noofReservedRooms
// ){
//     seasonalRoomtypeService.addRoomtypeToSeason(seasonId, roomtypeId, price, noofRooms, maxAdults, noofReservedRooms);
//     return new ResponseEntity<>("Roomtype added to Season sucessfully", HttpStatus.OK);
// }
