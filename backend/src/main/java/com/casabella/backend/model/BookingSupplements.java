package com.casabella.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


// @Data
@Entity
public class BookingSupplements {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingSupplementId;

    private int quantity;
    private Double pricePerUnit;

    // Booking - Supplements
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bookingId")
    private Booking booking;
    @ManyToOne
    @JoinColumn(name = "seasonalSupplementId")
    private SeasonalSupplement seasonalSupplement;


    //Setters Getters

    
    public BookingSupplements() {
    }
    public BookingSupplements(int quantity, Double pricePerUnit, Booking booking,
            SeasonalSupplement seasonalSupplement) {
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.booking = booking;
        this.seasonalSupplement = seasonalSupplement;
    }
    public BookingSupplements(int quantity, Double pricePerUnit) {
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }
    public Long getBookingSupplementId() {
        return bookingSupplementId;
    }
    public void setBookingSupplementId(Long bookingSupplementId) {
        this.bookingSupplementId = bookingSupplementId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Double getPricePerUnit() {
        return pricePerUnit;
    }
    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    public Booking getBooking() {
        return booking;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    public SeasonalSupplement getSeasonalSupplement() {
        return seasonalSupplement;
    }
    public void setSeasonalSupplement(SeasonalSupplement seasonalSupplement) {
        this.seasonalSupplement = seasonalSupplement;
    }
    


    

    
    

    
    
    

    


    
    

}
