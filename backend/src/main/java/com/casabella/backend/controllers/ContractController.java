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
import com.casabella.backend.model.Hotel;
import com.casabella.backend.repository.ContractRepository;
import com.casabella.backend.repository.HotelRepository;
import com.casabella.backend.services.ContractService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @Autowired
    private final ContractRepository contractRepo;
    @Autowired
    private final HotelRepository hotelRepo;

    @PostMapping("/")
    public ResponseEntity<?> addContract(@Valid @RequestBody Contract contract){  
        return new ResponseEntity<>(contractService.postContract(contract), HttpStatus.CREATED);
    }

    // // Create a contract for a hotel
    // @PostMapping("/{hotelId}/contracts")
    // public Contract createContract(@PathVariable Long hotelId, @RequestBody Contract contract) {
    //     return hotelService.createContract(hotelId, contract);
    // }

    //Get COntracts by hotel id
    @GetMapping("/hotel/{hotelId}")
    public List<Contract> getContractsByHotelId(@PathVariable Long hotelId){
        return contractService.getContractsByHotelId(hotelId);
    }
    

    //Get All Contracts
    @GetMapping("/")
    public ResponseEntity<List<Contract>> getAllContracts(){
        return ResponseEntity.ok(contractService.getAllContracts());
    }


    //Get one contract
    @GetMapping("/{id}")
    public ResponseEntity<?> getContractById(@PathVariable Long id){
        Contract contract = contractService.getContractById(id);
        if(contract == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(contract);
    }


    // Update contract
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateContract(@PathVariable Long id, @RequestBody Contract contract){
        Contract updatedContract = contractService.updateContract(id, contract);

        if(updatedContract == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            return ResponseEntity.ok(updatedContract);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContract(@PathVariable Long id){
        try{
            contractService.deleteContract(id);
            return new ResponseEntity<>("Contract with ID " + id + " deleted successfully", HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //One to Many - Hotel
    @PutMapping("/{contractId}/hotels/{hotelId}")
    Contract assignHotelToContract(
        @PathVariable Long contractId, 
        @PathVariable Long hotelId
    ){
        Contract contract = contractRepo.findById(contractId).get();
        Hotel hotel = hotelRepo.findById(hotelId).get();
        // contract.assignHotel(hotel);
        contract.setHotel(hotel);
        return contractRepo.save(contract);
    }
}
