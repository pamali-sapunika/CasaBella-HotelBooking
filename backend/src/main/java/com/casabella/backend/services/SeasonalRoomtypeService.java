package com.casabella.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

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
