package com.casabella.backend.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casabella.backend.model.Supplement;
import com.casabella.backend.services.SupplementService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/supplements")
public class SupplementController {

    private final SupplementService supplementService;

    // Add one supplement
    @PostMapping("/")
    public ResponseEntity<?> addSupplement(@Valid @RequestBody Supplement supplement) {  
        return new ResponseEntity<>(supplementService.postSupplement(supplement), HttpStatus.CREATED);
    }

    // Get All Supplements
    @GetMapping("/")
    public ResponseEntity<List<Supplement>> getAllSupplements() {
        return ResponseEntity.ok(supplementService.getAllSupplements());
    }

    // Get one Supplement by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplementById(@PathVariable Long id) {
        Supplement supplement = supplementService.getSupplementById(id);
        if (supplement == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(supplement);
    }

    //Update Supplement by id
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateSupplement(@PathVariable Long id, @RequestBody Supplement supplement){
        Supplement updatedSupplement = supplementService.updateSupplement(id, supplement);

        if(updatedSupplement == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            return ResponseEntity.ok(updatedSupplement);
        }
    }

    //Delete supplement by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplement(@PathVariable Long id){
        try{
            supplementService.deleteSupplement(id);
            return new ResponseEntity<>("Supplement with ID " + id + " deleted successfully", HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
