package com.casabella.backend.controllers;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.casabella.backend.model.Discount;
import com.casabella.backend.repository.ContractRepository;
import com.casabella.backend.repository.DiscountRepository;
import com.casabella.backend.services.DiscountService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/discounts")
@RequiredArgsConstructor
@Validated
public class DiscountController {

    private final DiscountService discountService;

    private final DiscountRepository discountRepo;

    private final ContractRepository contractRepo;

    @PostMapping("/")
    public ResponseEntity<?> postDiscount(@Valid @RequestBody Discount discount){  
        return new ResponseEntity<>(discountService.postDiscount(discount), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Discount>> getAllDiscounts(){
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

    @GetMapping("/hotelDiscounts/{hotelId}")
    public ResponseEntity<List<Discount>> getDiscountsByHotelId(@PathVariable Long hotelId) {
        List<Discount> discounts = discountService.getDiscountsByHotelId(hotelId);
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiscountById(@PathVariable Long id){
        Discount discount1 = discountService.getDiscountById(id);
        if(discount1 == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(discount1);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateDiscount(@PathVariable Long id, @RequestBody Discount discount){
        Discount updatedDiscount = discountService.updateDiscount(id, discount);

        if(updatedDiscount == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            return ResponseEntity.ok(updatedDiscount);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiscount(@PathVariable Long id){
        try{
            discountService.deleteDiscount(id);
            return new ResponseEntity<>("Discount with ID " + id + " deleted successfully", HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //COntarct - Discount
    @PutMapping("/{discountId}/contracts/{contractId}")
    Discount assignContractToDiscount(
        @PathVariable Long discountId,
        @PathVariable Long contractId
    ){
        Discount discount = discountRepo.findById(discountId).get();
        Contract contract = contractRepo.findById(contractId).get();

        discount.assignContract(contract);
        return discountRepo.save(discount);
    } 
}
