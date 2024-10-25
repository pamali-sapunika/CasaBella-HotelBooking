package com.casabella.backend.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casabella.backend.model.Season;
import com.casabella.backend.model.SeasonalSupplement;
import com.casabella.backend.model.Supplement;
import com.casabella.backend.repository.SeasonRepository;
import com.casabella.backend.repository.SeasonalSupplementRepository;
import com.casabella.backend.repository.SupplementRepository;

@Service
public class SeasonalSupplementService {

    public final SeasonalSupplementRepository seasonalSupplementRepo;
    public final SeasonRepository seasonRepo;
    public final SupplementRepository supplementRepo;

    @Autowired
    public SeasonalSupplementService(SeasonalSupplementRepository seasonalSupplementRepo, SeasonRepository seasonRepo, SupplementRepository supplementRepo) {
        this.seasonalSupplementRepo = seasonalSupplementRepo;
        this.seasonRepo = seasonRepo;
        this.supplementRepo = supplementRepo;
    }

    //Add supplement to season
    public void addSupplementToSeason(double pricePerUnit, Long seasonId, Long supplementId){
        Supplement supplement = supplementRepo.findById(supplementId)
            .orElseThrow(() -> new IllegalStateException("Supplement with id" + supplementId + " not found"));
        Season season = seasonRepo.findById(seasonId)
            .orElseThrow(() -> new IllegalStateException("Season with id" + seasonId + " not found"));

        SeasonalSupplement seasonalSupplement = new SeasonalSupplement(pricePerUnit, season, supplement);
        seasonalSupplementRepo.save(seasonalSupplement);
    }

    //Get Seasonal Roomtype List
    public List<SeasonalSupplement> getSeasonalSupplements(){
        return seasonalSupplementRepo.findAll();
    }

    //Get SeasonalSupplement by id
    public SeasonalSupplement getSeasonalSupplementById(Long seasonalSupplementId){
        return seasonalSupplementRepo.findById(seasonalSupplementId)
            .orElseThrow(() -> new IllegalStateException("SeasonalSupplement with id" + seasonalSupplementId + " not found"));
    }


    


    
}
