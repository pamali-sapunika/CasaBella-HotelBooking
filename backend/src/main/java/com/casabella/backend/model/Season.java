package com.casabella.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;

@Entity
// @Data
@Table(name = "season")
public class Season {

    @Id
    @Column(name = "season_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;

    private String seasonName;
    private Date startDate;
    private Date endDate;
    private double markupPercentage;

    //Contract - Season Relationship
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contractId", referencedColumnName = "contractId")
    private Contract contract;

    //Season - Roomtype
    @OneToMany(mappedBy = "season",cascade = CascadeType.ALL)
    private Set<SeasonalRoomtype> seasonalRoomtype = new HashSet<>();

    //Season - Supplements
    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private Set<SeasonalSupplement> seasonalSupplement = new HashSet<>();

    


    //Setters Getters
    public void assignContract(Contract contract2) {
        this.contract = contract2;
    }

    public Season(String seasonName, Date startDate, Date endDate, double markupPercentage, Contract contract) {
        this.seasonName = seasonName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.markupPercentage = markupPercentage;
        this.contract = contract;
    }

    public Season(String seasonName, Date startDate, Date endDate, double markupPercentage) {
        this.seasonName = seasonName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.markupPercentage = markupPercentage;
    }

    public Season() {
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
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

    public double getMarkupPercentage() {
        return markupPercentage;
    }

    public void setMarkupPercentage(double markupPercentage) {
        this.markupPercentage = markupPercentage;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Set<SeasonalRoomtype> getSeasonalRoomtype() {
        return seasonalRoomtype;
    }

    public void setSeasonalRoomtype(Set<SeasonalRoomtype> seasonalRoomtype) {
        this.seasonalRoomtype = seasonalRoomtype;
    }

    public Set<SeasonalSupplement> getSeasonalSupplement() {
        return seasonalSupplement;
    }

    public void setSeasonalSupplement(Set<SeasonalSupplement> seasonalSupplement) {
        this.seasonalSupplement = seasonalSupplement;
    }

}
