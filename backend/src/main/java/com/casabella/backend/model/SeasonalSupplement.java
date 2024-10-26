package com.casabella.backend.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
// @Data
// @Table(name = "seasonal_supplement")
public class SeasonalSupplement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seasonalSupplementId;

    private double pricePerUnit;

    // Season - Supplement
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "seasonId")
    private Season season;
    @ManyToOne
    @JoinColumn(name = "supplementId")
    private Supplement supplement;

    //Booking - Supplement
    @JsonIgnore
    @OneToMany(mappedBy = "seasonalSupplement")
    private Set<BookingSupplements> bookingSupplements = new HashSet<>();


    //Setters Getters
    public long getSeasonalSupplementId() {
        return seasonalSupplementId;
    }

    public void setSeasonalSupplementId(long seasonalSupplementId) {
        this.seasonalSupplementId = seasonalSupplementId;
    }
    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Supplement getSupplement() {
        return supplement;
    }

    public void setSupplement(Supplement supplement) {
        this.supplement = supplement;
    }

    public Set<BookingSupplements> getBookingSupplements() {
        return bookingSupplements;
    }

    public void setBookingSupplements(Set<BookingSupplements> bookingSupplements) {
        this.bookingSupplements = bookingSupplements;
    }

    public SeasonalSupplement() {
    }

    public SeasonalSupplement(double pricePerUnit, Season season, Supplement supplement,
            Set<BookingSupplements> bookingSupplements) {
        this.pricePerUnit = pricePerUnit;
        this.season = season;
        this.supplement = supplement;
        this.bookingSupplements = bookingSupplements;
    }

    public SeasonalSupplement(double pricePerUnit, Season season, Supplement supplement) {
        this.pricePerUnit = pricePerUnit;
        this.season = season;
        this.supplement = supplement;
    }

    

    

    
    
    
}
