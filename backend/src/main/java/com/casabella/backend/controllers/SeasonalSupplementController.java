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

import com.casabella.backend.dto.SeasonalSupplementDTO;
import com.casabella.backend.dto.SeasonalSupplementProjection;
import com.casabella.backend.model.SeasonalSupplement;
import com.casabella.backend.services.SeasonalSupplementService;

@RestController
@RequestMapping("/v1/seasonalsupplements")
public class SeasonalSupplementController {

    private final SeasonalSupplementService seasonalSupplementService;

    @Autowired
    public SeasonalSupplementController(SeasonalSupplementService seasonalSupplementService) {
        this.seasonalSupplementService = seasonalSupplementService;
    }

    //Add supplements to Season
    @PostMapping("/{supplementId}/seasons/{seasonId}")
    public ResponseEntity<SeasonalSupplement> addSupplementToSeason(
        @PathVariable Long supplementId,
        @PathVariable Long seasonId,
        @RequestBody Map<String, Object> requestBody
    ){
        double pricePerUnit = ((Number) requestBody.get("pricePerUnit")).doubleValue();
        

        SeasonalSupplement createdSeasonalSupplement = seasonalSupplementService.addSupplementToSeason(pricePerUnit, seasonId, supplementId );
        return ResponseEntity.ok(createdSeasonalSupplement);
    }

    @GetMapping
    public List<SeasonalSupplement> getSeasonalSupplements(){
        return seasonalSupplementService.getSeasonalSupplements();
    }

    //show seasonal supplement name 
    @GetMapping("/setSupplementName/{seasonId}")
    public ResponseEntity<List<SeasonalSupplementProjection>> getSeasonalSupplementsWithNames(@PathVariable Long seasonId) {
        List<SeasonalSupplementProjection> supplements = seasonalSupplementService.showAllWithSupplementName(seasonId);
        return ResponseEntity.ok(supplements);
    }

    @GetMapping("/{seasonalSupplementId}")
    public ResponseEntity<SeasonalSupplement> getSeasonalSupplementById(@PathVariable Long seasonalSupplementId){
        SeasonalSupplement seasonalSupplement = seasonalSupplementService.getSeasonalSupplementById(seasonalSupplementId);
        return ResponseEntity.ok(seasonalSupplement);
    }
    
}
