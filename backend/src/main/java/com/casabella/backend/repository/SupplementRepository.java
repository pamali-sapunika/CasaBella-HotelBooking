package com.casabella.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.casabella.backend.model.Supplement;

public interface SupplementRepository extends JpaRepository<Supplement, Long>{

    // @Query("SELECT s FROM supplement s WHERE LOWER(s.supplement_name) = LOWER(:supplement_name)")
    // Optional<Supplement> findBySupplementName(String supplement_name);

    
}