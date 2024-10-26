package com.casabella.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
// @Data
public class BookingItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BookingItemsId;

    private String ItemName;
    private Double pricePerUnit;
    private Double quantity;
    private Double totalAmount;

    


    //Setters Getters
    public Long getBookingItemsId() {
        return BookingItemsId;
    }
    public void setBookingItemsId(Long bookingItemsId) {
        BookingItemsId = bookingItemsId;
    }
    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }
    public Double getPricePerUnit() {
        return pricePerUnit;
    }
    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public BookingItem(String itemName, Double pricePerUnit, Double quantity, Double totalAmount) {
        ItemName = itemName;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }
    public BookingItem() {
    }
    
    


    
}
