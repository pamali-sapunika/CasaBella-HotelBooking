package com.casabella.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.casabella.backend.dto.SeasonalSupplementProjection;
import com.casabella.backend.model.SeasonalSupplement;

public interface SeasonalSupplementRepository extends JpaRepository<SeasonalSupplement, Long>{

    @Query(value = "SELECT ss.seasonal_supplement_id AS seasonalSupplementId, " +
               "ss.season_id AS seasonId, ss.price_per_unit AS pricePerUnit, " +
               "s.supplement_name AS supplementName " +
               "FROM seasonal_supplement ss " +
               "JOIN supplement s ON s.supplement_id = ss.supplement_id " +
               "WHERE ss.season_id = :seasonId", 
       nativeQuery = true)
    List<SeasonalSupplementProjection> showAllWithSupplementName(@Param("seasonId") Long seasonId);

    // @Query("SELECT s FROM SeasonalSupplement s WHERE s.seasonId IN :seasonIds")
    // List<SeasonalSupplementDTO> findBySeasonIdIn(@Param("seasonIds") List<Long> seasonIds);
    
}
