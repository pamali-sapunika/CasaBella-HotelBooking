package com.casabella.backend.dto;

public class AvailabilityDTO {
    private Long seasonalRoomtypeId;
    private Long roomtypeId;
    private String roomtypeName;
    private int noofRooms;
    private double price;
    private int maxAdults;
    private int noofReservedRooms;
    private Long seasonId;
    private double markupPercentage;
    private Long hotelId;
    private Long contractId;
    private int availableRooms;
    
    
    
    public AvailabilityDTO(Long seasonalRoomtypeId, Long roomtypeId, String roomtypeName, int noofRooms, double price,
            int maxAdults, int noofReservedRooms, Long seasonId, double markupPercentage, Long hotelId, Long contractId,
            int availableRooms) {
        this.seasonalRoomtypeId = seasonalRoomtypeId;
        this.roomtypeId = roomtypeId;
        this.roomtypeName = roomtypeName;
        this.noofRooms = noofRooms;
        this.price = price;
        this.maxAdults = maxAdults;
        this.noofReservedRooms = noofReservedRooms;
        this.seasonId = seasonId;
        this.markupPercentage = markupPercentage;
        this.hotelId = hotelId;
        this.contractId = contractId;
        this.availableRooms = availableRooms;
    }
    public int getNoofReservedRooms() {
        return noofReservedRooms;
    }
    public void setNoofReservedRooms(int noofReservedRooms) {
        this.noofReservedRooms = noofReservedRooms;
    }
    public Long getSeasonId() {
        return seasonId;
    }
    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }
    public double getMarkupPercentage() {
        return markupPercentage;
    }
    public void setMarkupPercentage(double markupPercentage) {
        this.markupPercentage = markupPercentage;
    }
    public Long getHotelId() {
        return hotelId;
    }
    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
    public Long getContractId() {
        return contractId;
    }
    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }
    public String getRoomtypeName() {
        return roomtypeName;
    }
    public void setRoomtypeName(String roomtypeName) {
        this.roomtypeName = roomtypeName;
    }
    public int getNoofRooms() {
        return noofRooms;
    }
    public void setNoofRooms(int noofRooms) {
        this.noofRooms = noofRooms;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Long getSeasonalRoomtypeId() {
        return seasonalRoomtypeId;
    }
    public void setSeasonalRoomtypeId(Long seasonalRoomtypeId) {
        this.seasonalRoomtypeId = seasonalRoomtypeId;
    }
    public int getMaxAdults() {
        return maxAdults;
    }
    public void setMaxAdults(int maxAdults) {
        this.maxAdults = maxAdults;
    }
    public Long getRoomtypeId() {
        return roomtypeId;
    }
    public void setRoomtypeId(Long roomtypeId) {
        this.roomtypeId = roomtypeId;
    }
    public int getavailableRooms() {
        return availableRooms;
    }
    public void setavailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }
    
    
    
}
