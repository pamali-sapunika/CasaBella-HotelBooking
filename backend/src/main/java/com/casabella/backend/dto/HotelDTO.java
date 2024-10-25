package com.casabella.backend.dto;

//website url, amenities
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class HotelDTO {

    private Long hotelId; 

    @NotBlank(message = "Name is mandatory")
    private String hotel_name;
    private String city;
    private String state;
    private String country;
    private String location;

    @Email
    private String hotel_email;
    private String hotel_contact;
    private String description;
    private String hotel_person;
    private Integer star_rating;


    //Getters Setters
    public Long getHotelId() {
        return hotelId;
    }
    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
    
    public String getHotel_name() {
        return hotel_name;
    }
    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
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
    public String getHotel_email() {
        return hotel_email;
    }
    public void setHotel_email(String hotel_email) {
        this.hotel_email = hotel_email;
    }
    public String getHotel_contact() {
        return hotel_contact;
    }
    public void setHotel_contact(String hotel_contact) {
        this.hotel_contact = hotel_contact;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getHotel_person() {
        return hotel_person;
    }
    public void setHotel_person(String hotel_person) {
        this.hotel_person = hotel_person;
    }
    public Integer getStar_rating() {
        return star_rating;
    }
    public void setStar_rating(Integer star_rating) {
        this.star_rating = star_rating;
    }


     
    
}
