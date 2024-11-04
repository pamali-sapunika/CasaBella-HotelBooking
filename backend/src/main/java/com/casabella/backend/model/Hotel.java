package com.casabella.backend.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
// @Data
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;

    @NotBlank(message = "Name is mandatory")
    private String hotelName;
    private String city;
    private String state;
    private String country;
    private String location;

    @Email
    private String hotelEmail;
    private String hotelContact;
    private String description;
    private String hotelPerson;
    private Integer starRating;

    //Contract - Hotel Relationship  
    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private Set<Contract> contracts = new HashSet<>();

    //Hotel - SeasonalROomtypes
    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    private Set<SeasonalRoomtype> hotelSeasonalRooms = new HashSet<>();



    //Getters Setters
    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public String getHotelContact() {
        return hotelContact;
    }

    public void setHotelContact(String hotelContact) {
        this.hotelContact = hotelContact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHotelPerson() {
        return hotelPerson;
    }

    public void setHotelPerson(String hotelPerson) {
        this.hotelPerson = hotelPerson;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public Set<SeasonalRoomtype> getHotelSeasonalRooms() {
        return hotelSeasonalRooms;
    }

    public void setHotelSeasonalRooms(Set<SeasonalRoomtype> hotelSeasonalRooms) {
        this.hotelSeasonalRooms = hotelSeasonalRooms;
    }

    public Hotel(@NotBlank(message = "Name is mandatory") String hotelName, String city, String state, String country,
            String location, @Email String hotelEmail, String hotelContact, String description, String hotelPerson,
            Integer starRating, Set<Contract> contracts) {
        this.hotelName = hotelName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.location = location;
        this.hotelEmail = hotelEmail;
        this.hotelContact = hotelContact;
        this.description = description;
        this.hotelPerson = hotelPerson;
        this.starRating = starRating;
        this.contracts = contracts;
    }

    public Hotel(@NotBlank(message = "Name is mandatory") String hotelName, String city, String state, String country,
            String location, @Email String hotelEmail, String hotelContact, String description, String hotelPerson,
            Integer starRating) {
        this.hotelName = hotelName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.location = location;
        this.hotelEmail = hotelEmail;
        this.hotelContact = hotelContact;
        this.description = description;
        this.hotelPerson = hotelPerson;
        this.starRating = starRating;
    }

    public Hotel() {
    }

    public Set<Contract> getContracts(){
        return contracts;
    }

    


    // public void setContract(Contract contract){
    //     this.contracts = contract;
    // }

    

}
