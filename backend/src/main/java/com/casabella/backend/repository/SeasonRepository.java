package com.casabella.backend.repository;

import org.springframework.stereotype.Repository;
import com.casabella.backend.model.Season;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long>{

    @Query("Select s from Season s where s.contract.contractId = :contractId")
    List<Season> findSeasonByContractId(
        @Param("contractId"
    )Long contractId);


    
}
