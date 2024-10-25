package com.casabella.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Data
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    private Date startDate;
    private Date endDate;
    private int cancellationDeadlineDays;
    private String cancellationDescription;
    private double cancellationFeePercentage;
    private double prepaymentFeePercenatage;
    private int balancedPaymentDays;
    private double balancedPaymentPercentage;


    //Contract - Season
    @OneToMany(mappedBy = "contract")
    private Set<Season> seasons= new HashSet<>();

     //Contract - Hotel 
     @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotelId", referencedColumnName = "hotelId")
    private Hotel hotel;

    //COntract - Discount
    @OneToMany(mappedBy = "contract")
    private Set<Discount> discounts = new HashSet<>();

    //Contract - Booking
    @JsonIgnore
    @OneToMany(mappedBy = "contract")
    private Set<Booking> bookings = new HashSet<>();


    //Methods
    public void assignHotel(Hotel hotel2) {
        this.hotel = hotel2;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCancellationDeadlineDays() {
        return cancellationDeadlineDays;
    }

    public void setCancellationDeadlineDays(int cancellationDeadlineDays) {
        this.cancellationDeadlineDays = cancellationDeadlineDays;
    }

    public String getCancellationDescription() {
        return cancellationDescription;
    }

    public void setCancellationDescription(String cancellationDescription) {
        this.cancellationDescription = cancellationDescription;
    }

    public double getCancellationFeePercentage() {
        return cancellationFeePercentage;
    }

    public void setCancellationFeePercentage(double cancellationFeePercentage) {
        this.cancellationFeePercentage = cancellationFeePercentage;
    }

    public double getPrepaymentFeePercenatage() {
        return prepaymentFeePercenatage;
    }

    public void setPrepaymentFeePercenatage(double prepaymentFeePercenatage) {
        this.prepaymentFeePercenatage = prepaymentFeePercenatage;
    }

    public int getBalancedPaymentDays() {
        return balancedPaymentDays;
    }

    public void setBalancedPaymentDays(int balancedPaymentDays) {
        this.balancedPaymentDays = balancedPaymentDays;
    }

    public double getBalancedPaymentPercentage() {
        return balancedPaymentPercentage;
    }

    public void setBalancedPaymentPercentage(double balancedPaymentPercentage) {
        this.balancedPaymentPercentage = balancedPaymentPercentage;
    }

    public Set<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(Set<Season> seasons) {
        this.seasons = seasons;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Contract(Date startDate, Date endDate, int cancellationDeadlineDays, String cancellationDescription,
            double cancellationFeePercentage, double prepaymentFeePercenatage, int balancedPaymentDays,
            double balancedPaymentPercentage, Set<Season> seasons, Hotel hotel, Set<Discount> discounts,
            Set<Booking> bookings) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancellationDeadlineDays = cancellationDeadlineDays;
        this.cancellationDescription = cancellationDescription;
        this.cancellationFeePercentage = cancellationFeePercentage;
        this.prepaymentFeePercenatage = prepaymentFeePercenatage;
        this.balancedPaymentDays = balancedPaymentDays;
        this.balancedPaymentPercentage = balancedPaymentPercentage;
        this.seasons = seasons;
        this.hotel = hotel;
        this.discounts = discounts;
        this.bookings = bookings;
    }

    public Contract(Date startDate, Date endDate, int cancellationDeadlineDays, String cancellationDescription,
            double cancellationFeePercentage, double prepaymentFeePercenatage, int balancedPaymentDays,
            double balancedPaymentPercentage) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancellationDeadlineDays = cancellationDeadlineDays;
        this.cancellationDescription = cancellationDescription;
        this.cancellationFeePercentage = cancellationFeePercentage;
        this.prepaymentFeePercenatage = prepaymentFeePercenatage;
        this.balancedPaymentDays = balancedPaymentDays;
        this.balancedPaymentPercentage = balancedPaymentPercentage;
    }

    public Contract() {
    }

    


    // //Setters Getters
    // public void addSeason(Season season) {
    //     seasons.add(season);
    //     season.setContract(this);
    // }
    

    
}
