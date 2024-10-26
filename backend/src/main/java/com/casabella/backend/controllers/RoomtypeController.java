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
import com.casabella.backend.model.Roomtype;
import com.casabella.backend.model.Season;
import com.casabella.backend.services.RoomtypeService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/roomtypes")
public class RoomtypeController {
    
    private final RoomtypeService roomtypeService;

    //Add one roomtype
    @PostMapping("/")
    public ResponseEntity<?> addRoomtype(@Valid @RequestBody Roomtype roomtype){  
        return new ResponseEntity<>(roomtypeService.postRoomtype(roomtype), HttpStatus.CREATED);
    }

     //Get All Roomtypes
    @GetMapping("/")
    public ResponseEntity<List<Roomtype>> getAllRoomtypes(){
        return ResponseEntity.ok(roomtypeService.getAllRoomtypes());
    }

    //Get one Roomtype
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomtypeById(@PathVariable Long id){
        Roomtype roomtype = roomtypeService.getRoomtypeById(id);
        if(roomtype == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(roomtype);
    }

    //Update Roomtype
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRoomtype(@PathVariable Long id, @RequestBody Roomtype roomtype){
        Roomtype updatedRoomtype = roomtypeService.updateRoomtype(id, roomtype);

        if(updatedRoomtype == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            return ResponseEntity.ok(updatedRoomtype);
        }
    }

    //Delete Roomtype
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoomtype(@PathVariable Long id){
        try{
            roomtypeService.deleteRoomtype(id);
            return new ResponseEntity<>("Roomtype with ID " + id + " deleted successfully", HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
