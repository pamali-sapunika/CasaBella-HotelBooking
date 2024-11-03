package com.casabella.backend.dto;

public interface AvailabilityProjection {

    Long getSeasonalRoomtypeId();
    Long getRoomtypeId();
    String getRoomtypeName();
    int getNoofRooms();
    double getPrice();
    int getMaxAdults();
    int getNoofReservedRooms();
    Long getSeasonId();
    double getMarkupPercentage();
    Long getHotelId();
    Long getContractId();
   

    
}

