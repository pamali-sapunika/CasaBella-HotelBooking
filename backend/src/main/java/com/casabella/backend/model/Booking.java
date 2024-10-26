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


// @Data
@Entity
@Table(name = "booking")
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private Date checkinDate;
    private Date checkoutDate;
    private Date bookingDate;
    private int adultCount;
    private int childCount;
    private double totalAmount;
    private double discountAmount;
    private double netAmount;
    private double prepaymentAmount;
    private double balancedAmount;
    private String status;

    //Booking - User
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    //Booking - Discount
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discountId", referencedColumnName = "discountId")
    private Discount discount;

    //Contract - Booking
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contractId", referencedColumnName = "contractId")
    private Contract contract;

    //Booking - Roomtype
    @OneToMany(mappedBy = "booking")
    private Set<BookingRoomtypes> bookingRoomtypes = new HashSet<>();

    // Booking - Passenger
    // @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passengerId", referencedColumnName = "passengerId")
    private Passenger passenger;

    //Booking - Supplement
    @OneToMany(mappedBy = "booking")
    private Set<BookingSupplements> bookingSupplements = new HashSet<>();




    //Setters Getters
    //Booking - Contract
    public void assignContractBooking(Contract contract2) {
       this.contract = contract2;
    }

    //Booking - Discount
    public void assignDiscountBooking(Discount discount2) {
        this.discount = discount2;
    }

    //Booking - User
    public void assignUserBooking(User user2) {
        this.user = user2;
    }

    //Passenger Booking
    public void assignPassengertoBooking(Passenger passenger2) {
        this.passenger = passenger2;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public double getPrepaymentAmount() {
        return prepaymentAmount;
    }

    public void setPrepaymentAmount(double prepaymentAmount) {
        this.prepaymentAmount = prepaymentAmount;
    }

    public double getBalancedAmount() {
        return balancedAmount;
    }

    public void setBalancedAmount(double balancedAmount) {
        this.balancedAmount = balancedAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Set<BookingRoomtypes> getBookingRoomtypes() {
        return bookingRoomtypes;
    }

    public void setBookingRoomtypes(Set<BookingRoomtypes> bookingRoomtypes) {
        this.bookingRoomtypes = bookingRoomtypes;
    }

    public Set<BookingSupplements> getBookingSupplements() {
        return bookingSupplements;
    }

    public void setBookingSupplements(Set<BookingSupplements> bookingSupplements) {
        this.bookingSupplements = bookingSupplements;
    }

    public Booking(Date checkinDate, Date checkoutDate, Date bookingDate, int adultCount, int childCount,
            double totalAmount, double discountAmount, double netAmount, double prepaymentAmount, double balancedAmount,
            String status, User user) {
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.bookingDate = bookingDate;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.netAmount = netAmount;
        this.prepaymentAmount = prepaymentAmount;
        this.balancedAmount = balancedAmount;
        this.status = status;
        this.user = user;
    }

    public Booking() {
    }

    public Booking(Date checkinDate, Date checkoutDate, Date bookingDate, int adultCount, int childCount,
            double totalAmount, double discountAmount, double netAmount, double prepaymentAmount, double balancedAmount,
            String status, User user, Set<BookingRoomtypes> bookingRoomtypes,
            Set<BookingSupplements> bookingSupplements) {
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.bookingDate = bookingDate;
        this.adultCount = adultCount;
        this.childCount = childCount;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.netAmount = netAmount;
        this.prepaymentAmount = prepaymentAmount;
        this.balancedAmount = balancedAmount;
        this.status = status;
        this.user = user;
        this.bookingRoomtypes = bookingRoomtypes;
        this.bookingSupplements = bookingSupplements;
    }

    
    


    


    
}
