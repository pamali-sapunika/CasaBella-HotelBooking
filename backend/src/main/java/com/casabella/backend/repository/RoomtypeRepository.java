package com.casabella.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.casabella.backend.model.Roomtype;

@Repository
public interface RoomtypeRepository extends JpaRepository<Roomtype, Long>{

    // @Query("SELECT r FROM roomtype r WHERE LOWER(r.roomtype_name) = LOWER(:roomtype_name)")
    // Optional<Roomtype> findByRoomtypeName(@Param("roomtype_name") String roomtype_name);
    
}
