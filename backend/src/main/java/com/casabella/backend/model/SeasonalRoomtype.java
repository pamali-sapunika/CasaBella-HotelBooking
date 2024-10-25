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
import jakarta.persistence.Table;


@Entity
// @Data
@Table(name = "seasonal_roomtype")
public class SeasonalRoomtype {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonalRoomtypeId;

    private double price;
    private int noofRooms;
    private int maxAdults;
    private int noofReservedRooms;


    //Season - Roomtype 
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "seasonId")
    private Season season;
    @ManyToOne
    @JoinColumn(name = "roomtypeId")
    private Roomtype roomtype;

    //Booking - Roomtype
    @JsonIgnore
    @OneToMany(mappedBy = "seasonalRoomtype")
    private Set<BookingRoomtypes> bookingRoomtypes = new HashSet<>();

    //Hotel - SeasonalRoomtypes
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotelId", referencedColumnName = "hotelId")
    private Hotel hotel;


    

    //Setters Getters
    public SeasonalRoomtype() {
    }

    public SeasonalRoomtype(double price, int noofRooms, int maxAdults, int noofReservedRooms, Season season, Roomtype roomtype) {
        this.price = price;
        this.noofRooms = noofRooms;
        this.maxAdults = maxAdults;
        this.noofReservedRooms = noofReservedRooms;
        this.season = season;
        this.roomtype = roomtype;
    }

    public SeasonalRoomtype(Long seasonalRoomtypeId, double price, int noofRooms, int maxAdults,
            int noofReservedRooms) {
        this.seasonalRoomtypeId = seasonalRoomtypeId;
        this.price = price;
        this.noofRooms = noofRooms;
        this.maxAdults = maxAdults;
        this.noofReservedRooms = noofReservedRooms;
    }

    public Long getSeasonalRoomtypeId() {
        return seasonalRoomtypeId;
    }

    public void setSeasonalRoomtypeId(Long seasonalRoomtypeId) {
        this.seasonalRoomtypeId = seasonalRoomtypeId;
    }

    public Set<BookingRoomtypes> getBookingRoomtypes() {
        return bookingRoomtypes;
    }

    public void setBookingRoomtypes(Set<BookingRoomtypes> bookingRoomtypes) {
        this.bookingRoomtypes = bookingRoomtypes;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNoofRooms() {
        return noofRooms;
    }

    public void setNoofRooms(int noofRooms) {
        this.noofRooms = noofRooms;
    }

    public int getMaxAdults() {
        return maxAdults;
    }

    public void setMaxAdults(int maxAdults) {
        this.maxAdults = maxAdults;
    }

    public int getNoofReservedRooms() {
        return noofReservedRooms;
    }

    public void setNoofReservedRooms(int noofReservedRooms) {
        this.noofReservedRooms = noofReservedRooms;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Roomtype getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(Roomtype roomtype) {
        this.roomtype = roomtype;
    }

    



    


    

    


    
}
