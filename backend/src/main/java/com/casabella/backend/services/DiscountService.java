package com.casabella.backend.services;

import java.util.*;
import org.springframework.stereotype.Service;
import com.casabella.backend.model.Discount;
import com.casabella.backend.repository.DiscountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class DiscountService {

    private final DiscountRepository discountRepo;

    //Add Discount
    public Discount postDiscount(Discount discount){

        return discountRepo.save(discount);
    }

    //Get All Discountss
    public List<Discount> getAllDiscounts(){
        return discountRepo.findAll();
    }

    //Get Discounts by ID
    public Discount getDiscountById(Long id){
        return discountRepo.findById(id).orElse(null);
    }

    //Update Discounts
    public Discount updateDiscount(Long id, Discount discount){
        Optional<Discount> optionalDiscount = discountRepo.findById(id);
        if(optionalDiscount.isPresent()){
            Discount existingDiscount = optionalDiscount.get();

            existingDiscount.setDiscountType(discount.getDiscountType());
            existingDiscount.setDescription(discount.getDescription());
            existingDiscount.setDiscountPercentage(discount.getDiscountPercentage());
            existingDiscount.setDaysPriorToArrival(discount.getDaysPriorToArrival());

            return discountRepo.save(existingDiscount);
        }

        return null;
    }

    //Delete Discounts
    public void deleteDiscount(Long id) {
        if(!discountRepo.existsById(id)) {
            throw new EntityNotFoundException("Discounts with ID " + id + " is not found");
        }
    
        discountRepo.deleteById(id);
    }
    
}
