package com.casabella.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.casabella.backend.model.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long>{

    @Query("Select c from Contract c where c.hotel.hotelId = :hotelId")
    List<Contract> findContractsByHotelId(Long hotelId);

} 
