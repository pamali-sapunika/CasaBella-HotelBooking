package com.casabella.backend.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.casabella.backend.model.Roomtype;
import com.casabella.backend.repository.RoomtypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomtypeService {

    private final RoomtypeRepository roomtypeRepo;

    //Add roomtype
    public Roomtype postRoomtype(Roomtype roomtype){
        return roomtypeRepo.save(roomtype);
    }

    //Get All Roomtypes
    public List<Roomtype> getAllRoomtypes(){
        return roomtypeRepo.findAll();
    }

    //Get Roomtype by ID
    public Roomtype getRoomtypeById(Long id){
        return roomtypeRepo.findById(id).orElse(null);
    }

    //Update Roomtype
    public Roomtype updateRoomtype(Long id, Roomtype roomtype){
        Optional<Roomtype> optionalRoomType = roomtypeRepo.findById(id);
        if(optionalRoomType.isPresent()){
            Roomtype existingRoomType = optionalRoomType.get();

            existingRoomType.setRoomtypeName(roomtype.getRoomtypeName());

            return roomtypeRepo.save(existingRoomType);
        }

        return null;
    }


    //Delete Roomtype
    public void deleteRoomtype(Long id) {
    if(!roomtypeRepo.existsById(id)) {
        throw new EntityNotFoundException("Roomtype with ID " + id + " is not found");
    }

    roomtypeRepo.deleteById(id);
}


    
}
