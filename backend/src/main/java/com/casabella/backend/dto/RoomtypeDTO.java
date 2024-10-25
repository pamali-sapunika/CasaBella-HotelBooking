package com.casabella.backend.dto;

public class RoomtypeDTO {

    private String roomtype_name;
    private double price;
    private int noof_rooms;
    private int maxAdults;
    private int noof_reserved_rooms;

    //Helping methods
    public String getRoomtype_name() {
        return roomtype_name;
    }
    public void setRoomtype_name(String roomtype_name) {
        this.roomtype_name = roomtype_name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getNoof_rooms() {
        return noof_rooms;
    }
    public void setNoof_rooms(int noof_rooms) {
        this.noof_rooms = noof_rooms;
    }
    public int getMaxAdults() {
        return maxAdults;
    }
    public void setMaxAdults(int maxAdults) {
        this.maxAdults = maxAdults;
    }
    public int getNoof_reserved_rooms() {
        return noof_reserved_rooms;
    }
    public void setNoof_reserved_rooms(int noof_reserved_rooms) {
        this.noof_reserved_rooms = noof_reserved_rooms;
    }

    

    
    
    
    
}
