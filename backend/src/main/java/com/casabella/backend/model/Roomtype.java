package com.casabella.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
// @Data
@Table(name = "roomtype")
public class Roomtype {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomtypeId;

    private String roomtypeName;

    //Season - ROomtype Relationship
    @JsonIgnore
    @OneToMany(mappedBy = "roomtype", cascade = CascadeType.ALL)
    private Set<SeasonalRoomtype> seasonalRoomtype = new HashSet<>();


    //Setters Getters
    public Long getRoomtypeId() {
        return roomtypeId;
    }

    public void setRoomtypeId(Long roomtypeId) {
        this.roomtypeId = roomtypeId;
    }

    public String getRoomtypeName() {
        return roomtypeName;
    }

    public void setRoomtypeName(String roomtypeName) {
        this.roomtypeName = roomtypeName;
    }

    public Set<SeasonalRoomtype> getSeasonalRoomtype() {
        return seasonalRoomtype;
    }

    public void setSeasonalRoomtype(Set<SeasonalRoomtype> seasonalRoomtype) {
        this.seasonalRoomtype = seasonalRoomtype;
    }

    public Roomtype(String roomtypeName, Set<SeasonalRoomtype> seasonalRoomtype) {
        this.roomtypeName = roomtypeName;
        this.seasonalRoomtype = seasonalRoomtype;
    }

    public Roomtype(String roomtypeName) {
        this.roomtypeName = roomtypeName;
    }

    public Roomtype() {
    }

    

    // //Getter for Relationship
    // public Set<Season> getSeasons(){
    //     return seasons;
    // }

    
    
    
}
