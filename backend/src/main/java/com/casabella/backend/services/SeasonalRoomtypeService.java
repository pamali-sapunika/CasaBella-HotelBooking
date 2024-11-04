package com.casabella.backend.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.casabella.backend.dto.AvailabilityDTO;
import com.casabella.backend.dto.AvailabilityProjection;
import com.casabella.backend.model.Roomtype;
import com.casabella.backend.model.Season;
import com.casabella.backend.model.SeasonalRoomtype;
import com.casabella.backend.repository.RoomtypeRepository;
import com.casabella.backend.repository.SeasonRepository;
import com.casabella.backend.repository.SeasonalRoomtypeRepo;

@Service
public class SeasonalRoomtypeService {

    private final SeasonRepository seasonRepo;
    private final RoomtypeRepository roomtypeRepo;
    private final SeasonalRoomtypeRepo seasonalRoomtypeRepo;

    public SeasonalRoomtypeService(SeasonalRoomtypeRepo seasonalRoomtypeRepo, RoomtypeRepository roomtypeRepo, SeasonRepository seasonRepo){
        this.seasonalRoomtypeRepo = seasonalRoomtypeRepo;
        this.roomtypeRepo = roomtypeRepo;
        this.seasonRepo = seasonRepo;
    }

    //Add Roomtype to Season
    public SeasonalRoomtype addRoomtypeToSeason(double price, int noofRooms, int maxAdults, int noofReservedRooms, Long seasonId, Long roomtypeId  ){
        Season season = seasonRepo.findById(seasonId)
            .orElseThrow(() -> new IllegalStateException("Season with id " + seasonId + " not found"));
        Roomtype roomtype = roomtypeRepo.findById(roomtypeId)
            .orElseThrow(() -> new IllegalStateException("Roomtype with id " + roomtypeId + " not found"));

        SeasonalRoomtype seasonalRoomtype = new SeasonalRoomtype(price, noofRooms, maxAdults, noofReservedRooms, season, roomtype);
        return seasonalRoomtypeRepo.save(seasonalRoomtype);
    }

    //Display Availabilty
    public List<AvailabilityDTO> findAvailableSeasonalRoomtypes(Long hotelId, int guestCount, Date checkinDate, Date checkoutDate){
        List<AvailabilityProjection> projections = seasonalRoomtypeRepo.findAvailableSeasonalRoomtypes(hotelId, guestCount, checkinDate, checkoutDate);
        return projections.stream()
            .map(p -> new AvailabilityDTO(p.getSeasonalRoomtypeId(),p.getRoomtypeId(), p.getRoomtypeName(), p.getNoofRooms(), p.getPrice(), p.getMaxAdults(), p.getNoofReservedRooms(), p.getSeasonId(), p.getMarkupPercentage(), p.getHotelId(), p.getContractId(), p.getBookedRooms()))
            .collect(Collectors.toList());
    }
    

    //Get seasonal roomtypes
    public List<SeasonalRoomtype> getSeasonalRoomtype(){
        return seasonalRoomtypeRepo.findAll();
    }

    //Get seasonal roomtype by id
    public SeasonalRoomtype getSeasonalRoomtypeById(Long seasonalRoomtypeId){
        return seasonalRoomtypeRepo.findById(seasonalRoomtypeId)
            .orElseThrow(() -> new IllegalStateException("Seasonal Roomtype with id" + seasonalRoomtypeId + " not found"));
    }
    
}
