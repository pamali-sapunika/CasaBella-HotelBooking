package com.casabella.backend.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
// @Data
public class Discount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;
    
    private String discountType;
    private String description;
    private String discountPercentage;
    private int daysPriorToArrival;

    //Booking - Discount
    @JsonIgnore
    @OneToMany(mappedBy = "discount")
    private Set<Booking> bookings = new HashSet<>();

    //Discount - Contract
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contractId", referencedColumnName = "contractId")
    private Contract contract;



    //Methods
    public void assignContract(Contract contract2) {
       this.contract = contract2;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getDaysPriorToArrival() {
        return daysPriorToArrival;
    }

    public void setDaysPriorToArrival(int daysPriorToArrival) {
        this.daysPriorToArrival = daysPriorToArrival;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Discount(String discountType, String description, String discountPercentage, int daysPriorToArrival,
            Set<Booking> bookings, Contract contract) {
        this.discountType = discountType;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.daysPriorToArrival = daysPriorToArrival;
        this.bookings = bookings;
        this.contract = contract;
    }

    public Discount(String discountType, String description, String discountPercentage, int daysPriorToArrival) {
        this.discountType = discountType;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.daysPriorToArrival = daysPriorToArrival;
    }

    public Discount() {
    }

}
