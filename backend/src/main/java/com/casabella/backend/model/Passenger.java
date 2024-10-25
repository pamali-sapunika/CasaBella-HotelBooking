package com.casabella.backend.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


// @Data
@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long passengerId;

    private String passengerName;
    private String passengerEmail;
    private String passengerNic;


    //Booking Passenger
    @OneToMany(mappedBy = "passenger")
    private Set<Booking> bookings = new HashSet<>();
    

    //Setters Getters
    public String getPassengerName() {
        return passengerName;
    }
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
    public String getPassengerEmail() {
        return passengerEmail;
    }
    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }
    public String getPassengerNic() {
        return passengerNic;
    }
    public void setPassengerNic(String passengerNic) {
        this.passengerNic = passengerNic;
    }

    public Passenger(String passengerName, String passengerEmail, String passengerNic) {
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.passengerNic = passengerNic;
    }

    public Passenger() {
    }

    public Passenger(Long passengerId, String passengerName, String passengerEmail, String passengerNic) {
        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.passengerNic = passengerNic;
    }

    

    
    
    // //Booking - Passenger
    // @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "bookingId", referencedColumnName = "bookingId")
    // private Booking booking;


    

    

    
}
