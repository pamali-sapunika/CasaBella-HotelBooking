package com.casabella.backend.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.casabella.backend.model.Contract;
import com.casabella.backend.model.Season;
import com.casabella.backend.repository.ContractRepository;
import com.casabella.backend.repository.SeasonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeasonService {

    private final SeasonRepository seasonRepo;
    private final ContractRepository contractRepo;


    //Add Season
    public Season postSeason(Season season){
        return seasonRepo.save(season);
    }

    //Get All Seasons
    public List<Season> getAllSeasons(){
        return seasonRepo.findAll();
    }

    //Get Season by ID
    public Season getSeasonById(Long id){
        return seasonRepo.findById(id).orElse(null);
    }

    //Get Seasons by contract ID
    public List<Season> getSeasonsByContractId(Long contractId){
        Contract contract = contractRepo.findById(contractId)
            .orElseThrow(() -> new IllegalArgumentException("Contract id not found" + contractId));

        return seasonRepo.findSeasonByContractId(contractId);
    }

    //Update Season
    public Season updateSeason(Long id, Season season){
        Optional<Season> optionalSeason = seasonRepo.findById(id);
        if(optionalSeason.isPresent()){
            Season existingSeason = optionalSeason.get();

            existingSeason.setSeasonName(season.getSeasonName());
            existingSeason.setStartDate(season.getStartDate());
            existingSeason.setEndDate(season.getEndDate());
            existingSeason.setMarkupPercentage(season.getMarkupPercentage());

            return seasonRepo.save(existingSeason);
        }

        return null;
    }

    //Delete Season
    public void deleteSeason(Long id) {
        if(!seasonRepo.existsById(id)) {
            throw new EntityNotFoundException("Season with ID " + id + " is not found");
        }
    
        seasonRepo.deleteById(id);
    }


}
