package com.casabella.backend.dto;

import java.util.*;

public class ContractDTO {

    private Date startDate;
    private Date endDate;
    private int cancellation_deadline_days;
    private String cancellation_description;
    private double cancellation_fee_percentage;
    private double prepayment_fee_percenatage;
    private int balanced_payment_days;
    private double balanced_payment_percentage;

    private Long hotelId;

    //Season -Contract
    private Set<SeasonDTO> seasons = new HashSet<>();

    //Helping Methods
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

    public int getCancellation_deadline_days() {
        return cancellation_deadline_days;
    }

    public void setCancellation_deadline_days(int cancellation_deadline_days) {
        this.cancellation_deadline_days = cancellation_deadline_days;
    }

    public String getCancellation_description() {
        return cancellation_description;
    }

    public void setCancellation_description(String cancellation_description) {
        this.cancellation_description = cancellation_description;
    }

    public double getCancellation_fee_percentage() {
        return cancellation_fee_percentage;
    }

    public void setCancellation_fee_percentage(double cancellation_fee_percentage) {
        this.cancellation_fee_percentage = cancellation_fee_percentage;
    }

    public double getPrepayment_fee_percenatage() {
        return prepayment_fee_percenatage;
    }

    public void setPrepayment_fee_percenatage(double prepayment_fee_percenatage) {
        this.prepayment_fee_percenatage = prepayment_fee_percenatage;
    }

    public int getBalanced_payment_days() {
        return balanced_payment_days;
    }

    public void setBalanced_payment_days(int balanced_payment_days) {
        this.balanced_payment_days = balanced_payment_days;
    }

    public double getBalanced_payment_percentage() {
        return balanced_payment_percentage;
    }

    public void setBalanced_payment_percentage(double balanced_payment_percentage) {
        this.balanced_payment_percentage = balanced_payment_percentage;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Set<SeasonDTO> getSeasons() {
        return seasons;
    }

    public void setSeasons(Set<SeasonDTO> seasons) {
        this.seasons = seasons;
    }

    
    
}
