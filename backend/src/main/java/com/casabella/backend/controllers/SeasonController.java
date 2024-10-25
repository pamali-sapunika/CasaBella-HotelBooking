package com.casabella.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casabella.backend.model.Contract;
import com.casabella.backend.model.Season;
import com.casabella.backend.repository.ContractRepository;
import com.casabella.backend.repository.SeasonRepository;
import com.casabella.backend.services.SeasonService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/seasons")
public class SeasonController {

    private final SeasonService seasonService;

    @Autowired
    private SeasonRepository seasonRepo;

    @Autowired
    private ContractRepository contractRepo;

    //Add season
    @PostMapping("/")
    public ResponseEntity<?> addSeason(@Valid @RequestBody Season season){  
        return new ResponseEntity<>(seasonService.postSeason(season), HttpStatus.CREATED);
    }

    //Get All seasons
    @GetMapping("/")
    public ResponseEntity<List<Season>> getAllSeasons(){
        return ResponseEntity.ok(seasonService.getAllSeasons());
    }


    //Get one Season
    @GetMapping("/{id}")
    public ResponseEntity<?> getSeasonById(@PathVariable Long id){
        Season season = seasonService.getSeasonById(id);
        if(season == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(season);
    }

    //Get Seasons by Contract ID
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<Season>> getSeasonsByContractId(@PathVariable Long contractId){
        List<Season> seasons  = seasonService.getSeasonsByContractId(contractId);
        return ResponseEntity.ok(seasons);
    }

    //Get COntracts by hotel id exmple to watch
    // @GetMapping("/hotel/{hotelId}")
    // public List<Contract> getContractsByHotelId(@PathVariable Long hotelId){
    //     return contractService.getContractsByHotelId(hotelId);
    // }

    //Update Season by ID
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateSeason(@PathVariable Long id, @RequestBody Season season){
        Season updatedSeason = seasonService.updateSeason(id, season);

        if(updatedSeason == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            return ResponseEntity.ok(updatedSeason);
        }
    }

    //Delete Season by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeason(@PathVariable Long id){
        try{
            seasonService.deleteSeason(id);
            return new ResponseEntity<>("Season with ID " + id + " deleted successfully", HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Contract - Seasons
    @PutMapping("/{seasonId}/contracts/{contractId}")
    Season assignContractToSeason(
        @PathVariable Long seasonId,
        @PathVariable Long contractId
    ){
        Season season = seasonRepo.findById(seasonId).get();
        Contract contract = contractRepo.findById(contractId).get();

        season.assignContract(contract);
        return seasonRepo.save(season);
    }


    
}
