package com.casabella.backend.services;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;

import com.casabella.backend.model.Hotel;
import com.casabella.backend.repository.HotelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor 
public class HotelService {

    private final HotelRepository hotelRepo;

    //Add Hotel
    public Hotel postHotel(Hotel hotel){

        //Check for same hotel emails
        Optional<Hotel> hotelByName = hotelRepo.findHotelByName(hotel.getHotelName());
        if(hotelByName.isPresent()){
            throw new IllegalTransactionStateException("Hotel name taken");
        }

        return hotelRepo.save(hotel);
    }

    // Get All hotels which rooms are available
    public List<Hotel> findRoomsAvailableHotels(int guestCount, Date checkinDate, Date checkoutDate, String location){
        return hotelRepo.findRoomsAvailableHotels(guestCount, checkinDate, checkoutDate, location);
    }

    //Get All Hotels
    public List<Hotel> getAllHotels(){
        return hotelRepo.findAll();
    }

    //Get hotel by ID
    public Hotel getHotelById(Long id){
        return hotelRepo.findById(id).orElse(null);
    }

    //Update Hotel
    public Hotel updateHotel(Long id, Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepo.findById(id);
        if(optionalHotel.isPresent()){
            Hotel existingHotel = optionalHotel.get();

            existingHotel.setHotelName(hotel.getHotelName());
            existingHotel.setCity(hotel.getCity());
            existingHotel.setState(hotel.getState());
            existingHotel.setCountry(hotel.getCountry());
            existingHotel.setLocation(hotel.getLocation());
            existingHotel.setHotelEmail(hotel.getHotelEmail());
            existingHotel.setHotelContact(hotel.getHotelContact());
            existingHotel.setDescription(hotel.getDescription());
            existingHotel.setHotelPerson(hotel.getHotelPerson());
            existingHotel.setStarRating(hotel.getStarRating());
            existingHotel.setImageUrl(hotel.getImageUrl());

            return hotelRepo.save(existingHotel);
        }

        return null;
    }

    //Delete Hotel
    public void deleteHotel(Long id) {
        if(!hotelRepo.existsById(id)) {
            throw new EntityNotFoundException("Hotel with ID " + id + " is not found");
        }
    
        hotelRepo.deleteById(id);
    }
    
    
}
