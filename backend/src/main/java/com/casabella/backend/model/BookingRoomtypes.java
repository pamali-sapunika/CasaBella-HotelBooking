package com.casabella.backend.model;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
// @Data
public class BookingRoomtypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingRoomtypeId;

    private int noOfRooms;
    private Double price;
    private Date bCheckinDate;
    private Date bCheckoutDate;
    private int guestCount;


    //Booking - Roomtypes
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bookingId")
    private Booking booking;
    @ManyToOne
    @JoinColumn(name = "seasonalRoomtypeId")
    private SeasonalRoomtype seasonalRoomtype;


    //Setters Getters
    public Long getBookingRoomtypeId() {
        return bookingRoomtypeId;
    }
    public void setBookingRoomtypeId(Long bookingRoomtypeId) {
        this.bookingRoomtypeId = bookingRoomtypeId;
    }
    public int getNoOfRooms() {
        return noOfRooms;
    }
    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public int getGuestCount() {
        return guestCount;
    }
    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }
    public Date getbCheckinDate() {
        return bCheckinDate;
    }
    public void setbCheckinDate(Date bCheckinDate) {
        this.bCheckinDate = bCheckinDate;
    }
    public Date getbCheckoutDate() {
        return bCheckoutDate;
    }
    public void setbCheckoutDate(Date bCheckoutDate) {
        this.bCheckoutDate = bCheckoutDate;
    }
    public int getGuestount() {
        return guestCount;
    }
    public void setGuestount(int guestount) {
        this.guestCount = guestount;
    }
    public Booking getBooking() {
        return booking;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    public SeasonalRoomtype getSeasonalRoomtype() {
        return seasonalRoomtype;
    }
    public void setSeasonalRoomtype(SeasonalRoomtype seasonalRoomtype) {
        this.seasonalRoomtype = seasonalRoomtype;
    }
    public BookingRoomtypes(int noOfRooms, Double price, Date bCheckinDate, Date bCheckoutDate, int guestCount) {
        this.noOfRooms = noOfRooms;
        this.price = price;
        this.bCheckinDate = bCheckinDate;
        this.bCheckoutDate = bCheckoutDate;
        this.guestCount = guestCount;
    }
    public BookingRoomtypes(int noOfRooms, Double price, Date bCheckinDate, Date bCheckoutDate, int guestCount,
            Booking booking, SeasonalRoomtype seasonalRoomtype) {
        this.noOfRooms = noOfRooms;
        this.price = price;
        this.bCheckinDate = bCheckinDate;
        this.bCheckoutDate = bCheckoutDate;
        this.guestCount = guestCount;
        this.booking = booking;
        this.seasonalRoomtype = seasonalRoomtype;
    }
    public BookingRoomtypes() {
    }
    
    
    


    
    


    
}
