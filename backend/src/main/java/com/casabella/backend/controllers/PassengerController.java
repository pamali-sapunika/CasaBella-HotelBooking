package com.casabella.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casabella.backend.model.Passenger;
import com.casabella.backend.services.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/passengers")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping("/")
    public ResponseEntity<Passenger> postPassenger(@Valid @RequestBody Passenger passenger){  
        return new ResponseEntity<>(passengerService.postPassenger(passenger), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id){
        Passenger passenger1 = passengerService.getPassengerById(id);
        if(passenger1 == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(passenger1);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable Long id, @RequestBody Passenger passenger){
        Passenger updatedPassenger = passengerService.updatePassenger(id, passenger);

        if(updatedPassenger == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            return ResponseEntity.ok(updatedPassenger);
        }
    }

}
