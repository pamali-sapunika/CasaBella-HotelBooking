package com.casabella.backend.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.casabella.backend.model.Supplement;
import com.casabella.backend.repository.SupplementRepository;  
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplementService {

    private final SupplementRepository supplementRepo;

    // Add Supplement
    public Supplement postSupplement(Supplement supplement) {
        return supplementRepo.save(supplement);
    }

    // Get All Supplements
    public List<Supplement> getAllSupplements() {
        return supplementRepo.findAll();
    }

    // Get Supplement by ID
    public Supplement getSupplementById(Long id) {
        return supplementRepo.findById(id).orElse(null);
    }

    //Update Supplement
    public Supplement updateSupplement(Long id, Supplement supplement){
        Optional<Supplement> optionalSupplement = supplementRepo.findById(id);
        if(optionalSupplement.isPresent()){
            Supplement existingSupplement = optionalSupplement.get();

            existingSupplement.setSupplementName(supplement.getSupplementName());

            return supplementRepo.save(existingSupplement);
        }

        return null;
    }

    //Delete Supplement
    public void deleteSupplement(Long id) {
        if(!supplementRepo.existsById(id)) {
            throw new EntityNotFoundException("Supplement with ID " + id + " is not found");
        }
    
        supplementRepo.deleteById(id);
    }


}
