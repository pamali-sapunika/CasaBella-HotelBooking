package com.casabella.backend.dto;

import java.util.*;

public class SeasonDTO {

    private String season_name;
    private Date start_date;
    private Date end_date;
    private double markup_percentage;

    private Set<RoomtypeDTO> roomtypes = new HashSet<>();

    private Set<SeasonalSupplementDTO> supplements = new HashSet<>();

    //Helping Methos
    public String getSeason_name() {
        return season_name;
    }

    public void setSeason_name(String season_name) {
        this.season_name = season_name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public double getMarkup_percentage() {
        return markup_percentage;
    }

    public void setMarkup_percentage(double markup_percentage) {
        this.markup_percentage = markup_percentage;
    }

    public Set<RoomtypeDTO> getRoomtypes() {
        return roomtypes;
    }

    public void setRoomtypes(Set<RoomtypeDTO> roomtypes) {
        this.roomtypes = roomtypes;
    }

    public Set<SeasonalSupplementDTO> getSupplements() {
        return supplements;
    }

    public void setSupplements(Set<SeasonalSupplementDTO> supplements) {
        this.supplements = supplements;
    }

   
    
}
