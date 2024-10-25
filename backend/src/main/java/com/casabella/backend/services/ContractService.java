package com.casabella.backend.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casabella.backend.model.Contract;
import com.casabella.backend.model.Hotel;
import com.casabella.backend.repository.ContractRepository;
import com.casabella.backend.repository.HotelRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ContractService {

    @Autowired
    private final ContractRepository contractRepo;

    @Autowired
    private HotelRepository hotelRepo;



    // Add Contract
    public Contract postContract(Contract contract){
        return contractRepo.save(contract);
    }

    //Get All Contracts
    public List<Contract> getAllContracts(){
        return contractRepo.findAll();
    }

    // public Contract createContract(Long hotelId, Contract contract) {
    //     Hotel hotel = hotelRepo.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found"));
    //     contract.setHotel(hotel);
    //     return contractRepo.save(contract);
    // }


    //Get Contract by Hotel Id
    public List<Contract> getContractsByHotelId(Long hotelId) {
        Hotel hotel = hotelRepo.findById(hotelId)
                .orElseThrow(()-> new IllegalArgumentException("Hotel not found with Id: "+ hotelId));

        return contractRepo.findContractsByHotelId(hotelId);
    }

    //Get Contract by ID
    public Contract getContractById(Long id){
        return contractRepo.findById(id).orElse(null);
    }

    //Update Contract
    public Contract updateContract(Long id, Contract contract){
        Optional<Contract> optionalContract = contractRepo.findById(id);
        if(optionalContract.isPresent()){
            Contract exisitingContract = optionalContract.get();

            exisitingContract.setStartDate(contract.getStartDate());
            exisitingContract.setEndDate(contract.getEndDate());
            exisitingContract.setCancellationDeadlineDays(contract.getCancellationDeadlineDays());
            exisitingContract.setCancellationDescription(contract.getCancellationDescription());
            exisitingContract.setCancellationFeePercentage(contract.getCancellationFeePercentage());
            exisitingContract.setPrepaymentFeePercenatage(contract.getPrepaymentFeePercenatage());
            exisitingContract.setBalancedPaymentDays(contract.getBalancedPaymentDays());
            exisitingContract.setBalancedPaymentPercentage(contract.getBalancedPaymentPercentage());


            return contractRepo.save(exisitingContract);
        }

        return null;
    }

    //Delete Contract
    public void deleteContract(Long id){
        if(!contractRepo.existsById(id)){
            throw new EntityNotFoundException("Contract with ID new " + id + " is not found");
        }

        contractRepo.deleteById(id);
    }

    





    
}














    // Add Contract
    // @Transactional
    // public Contract postContract(ContractDTO contractDTO){
    //     Contract contract = new Contract();

    //     contract.setStartDate(contractDTO.getStartDate());
    //     contract.setEndDate(contractDTO.getEndDate());
    //     contract.setCancellation_deadline_days(contractDTO.getCancellation_deadline_days());
    //     contract.setCancellation_description(contractDTO.getCancellation_description());
    //     contract.setCancellation_fee_percentage(contractDTO.getCancellation_fee_percentage());
    //     contract.setPrepayment_fee_percenatage(contractDTO.getPrepayment_fee_percenatage());
    //     contract.setBalanced_payment_days(contractDTO.getBalanced_payment_days());
    //     contract.setBalanced_payment_percentage(contractDTO.getBalanced_payment_percentage());

    //     //Hotel for contract
    //     Hotel hotel = hotelRepo.findById(contractDTO.getHotelId()).orElseThrow(() -> new RuntimeException("Hotel not found"));
    //     contract.setHotel(hotel);

    //     //Seasons in the contract
    //     // Set<Season> seasons = new HashSet<>();
    //     for(SeasonDTO seasonDTO : contractDTO.getSeasons()){
    //         Season season = new Season();
    //         season.setSeason_name(seasonDTO.getSeason_name());
    //         season.setStart_date(seasonDTO.getEnd_date());
    //         season.setEnd_date(seasonDTO.getEnd_date());
    //         season.setMarkup_percentage(seasonDTO.getMarkup_percentage());

    //         //ROomtypes
    //         // Set<SeasonalRoomType> seasonalRoomTypes = new HashSet<>();
    //         for (RoomtypeDTO roomtypeDTO : seasonDTO.getRoomtypes()){
    //             // Roomtype roomtype = roomtypeRepo.findByRoomtypeName(roomtypeDTO.getRoomtype_name())
    //             //     .orElseGet(() -> {
    //             //         Roomtype newRoomtype = new Roomtype();
    //             //         newRoomtype.setRoomtype_name(roomtypeDTO.getRoomtype_name());
    //             //         return roomtypeRepo.save(newRoomtype);
    //             //     });
    //             Roomtype roomtype = new Roomtype();
    //             roomtype.setRoomtype_name(roomtypeDTO.getRoomtype_name());
    //             roomtypeRepo.save(roomtype);

    //             SeasonalRoomtype seasonalRoomtype = new SeasonalRoomtype();
    //             seasonalRoomtype.setRoomtype(roomtype);
    //             seasonalRoomtype.setSeason(season);
    //             seasonalRoomtype.setPrice(roomtypeDTO.getPrice());
    //             seasonalRoomtype.setNoof_rooms(roomtypeDTO.getNoof_reserved_rooms());
    //             seasonalRoomtype.setMaxAdults(roomtypeDTO.getMaxAdults());
    //             seasonalRoomtype.setNoof_reserved_rooms(roomtypeDTO.getNoof_rooms());

    //             season.addSeasonalRoomType(seasonalRoomtype);
                
    //         }


    //         //Suppliements
    //         for (SupplementDTO supplementDTO : seasonDTO.getSupplements()){
    //             // Supplement supplement = supplementRepo.findBySupplementName(supplementDTO.getSupplement_name())
    //             //     .orElseGet(() -> {
    //             //         Supplement newSupplement = new Supplement();
    //             //         newSupplement.setSupplement_name(supplementDTO.getSupplement_name());
    //             //         return supplementRepo.save(newSupplement);
    //             //     });
    //             Supplement supplement = new Supplement();
    //             supplement.setSupplement_name(supplementDTO.getSupplement_name());
    //             supplementRepo.save(supplement);
                
    //             SeasonalSupplement seasonalSupplement = new SeasonalSupplement();
    //             seasonalSupplement.setSeason(season);
    //             seasonalSupplement.setSupplement(supplement);
    //             seasonalSupplement.setPrice_per_unit(supplementDTO.getPrice_per_unit());

    //             season.addSeasonalSupplement(seasonalSupplement);
    //         }

    //         contract.addSeason(season);
    //     }

    //     return contractRepo.save(contract);
    // }