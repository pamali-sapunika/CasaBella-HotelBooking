package com.casabella.backend.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
// @Data
public class Supplement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplementId;

    private String supplementName;

    //Season - Supplement Relationship
    @JsonIgnore
    @OneToMany(mappedBy = "supplement", cascade = CascadeType.ALL)
    private Set<SeasonalSupplement> seasonalSupplement = new HashSet<>();
    

    //Setters and Getters
    public Long getSupplementId() {
        return supplementId;
    }

    public void setSupplementId(Long supplementId) {
        this.supplementId = supplementId;
    }
    
    public String getSupplementName() {
        return supplementName;
    }

    public void setSupplementName(String supplementName) {
        this.supplementName = supplementName;
    }

    public Set<SeasonalSupplement> getSeasonalSupplement() {
        return seasonalSupplement;
    }

    public void setSeasonalSupplement(Set<SeasonalSupplement> seasonalSupplement) {
        this.seasonalSupplement = seasonalSupplement;
    }

    public Supplement(String supplementName) {
        this.supplementName = supplementName;
    }

    public Supplement() {
    }

    public Supplement(String supplementName, Set<SeasonalSupplement> seasonalSupplement) {
        this.supplementName = supplementName;
        this.seasonalSupplement = seasonalSupplement;
    }

    

    

    


    

    

    
}
