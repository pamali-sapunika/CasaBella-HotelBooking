export interface AvailabilityDTO{
    seasonalRoomtypeId: number;
    roomtypeId: number;
    roomtypeName: string;
    noofRooms: number;
    price: number;
    maxAdults: number;
    noofReservedRooms: number;
    seasonId: number;
    markupPercentage: number;
    hotelId: number;
    contractId: number;
    availableRooms: number;

    selectedRooms?: number;        // Number of rooms selected by the user
    selectedGuestCount?: number;  
    seasonalSupplementIds: number[]; 
}