package com.casabella.backend.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.casabella.backend.dto.AvailabilityProjection;
import com.casabella.backend.model.SeasonalRoomtype;

public interface SeasonalRoomtypeRepo extends JpaRepository<SeasonalRoomtype, Long>{


    @Query(value = """
    WITH SeasonsCovered AS (
        SELECT s.start_date, s.end_date, s.season_id as season_id
        FROM season s
        WHERE (s.start_date <= :bCheckinDate AND s.end_date >= :bCheckinDate)
        OR (s.start_date <= :bCheckoutDate AND s.end_date >= :bCheckoutDate)
    ),
    CheckinCovered AS (
        SELECT 1 as ifcheckindate_in_season
        FROM season s
        WHERE s.start_date <= :bCheckinDate  AND s.end_date >= :bCheckinDate
    ),
    CheckoutCovered AS (
        SELECT 1 as ifcheckoutdate_in_season
        FROM season s
        WHERE s.start_date <= :bCheckoutDate AND s.end_date >= :bCheckoutDate
    ),
    GapExists AS (
        SELECT 1
        FROM SeasonsCovered sc1
        WHERE sc1.end_date < :bCheckoutDate
        AND NOT EXISTS (
            SELECT 1
            FROM SeasonsCovered sc2
            WHERE sc2.start_date = sc1.end_date + INTERVAL 1 DAY
        )
    ),
    AvailableHotels AS (
        SELECT h.hotel_id
        FROM hotel h
        JOIN seasonal_roomtype sr ON h.hotel_id = sr.hotel_id
        JOIN roomtype rt ON rt.roomtype_id = sr.roomtype_id
        WHERE h.hotel_id = :hotelId
        GROUP BY h.hotel_id
        HAVING SUM((sr.noof_rooms - COALESCE((
        SELECT SUM(brt.no_of_rooms)
        FROM booking_roomtypes brt
        WHERE brt.seasonal_roomtype_id = sr.seasonal_roomtype_id
        AND brt.b_checkin_date < :bCheckoutDate
        AND brt.b_checkout_date > :bCheckinDate
    ), 0)) * sr.max_adults ) >= :guestCount
    )

    SELECT sr.seasonal_roomtype_id AS seasonalRoomtypeId, 
        sr.roomtype_id AS roomtypeId, 
        rt.roomtype_name AS roomtypeName, 
        sr.noof_rooms AS noofRooms, 
        sr.price AS price, 
        sr.max_adults AS maxAdults, 
        sr.noof_reserved_rooms AS noofReservedRooms, 
        sr.season_id AS seasonId, 
        s.markup_percentage AS markupPercentage, 
        sr.hotel_id AS hotelId, 
        c.contract_id AS contractId,
        sr.noof_rooms - COALESCE((SELECT SUM(brt.no_of_rooms)
                    FROM booking_roomtypes brt
                    WHERE brt.seasonal_roomtype_id = sr.seasonal_roomtype_id
                    AND brt.b_checkin_date < :bCheckoutDate
                    AND brt.b_checkout_date > :bCheckinDate), 0) AS availableRooms,
            COALESCE((SELECT SUM(brt.no_of_rooms)
                    FROM booking_roomtypes brt
                    WHERE brt.seasonal_roomtype_id = sr.seasonal_roomtype_id
                    AND brt.b_checkin_date < :bCheckoutDate
                    AND brt.b_checkout_date > :bCheckinDate), 0) AS bookedRooms
    FROM hotel h
    JOIN seasonal_roomtype sr ON h.hotel_id = sr.hotel_id
    JOIN season s ON s.season_id = sr.season_id
    JOIN contract c ON c.contract_id = s.contract_id
    JOIN roomtype rt ON rt.roomtype_id = sr.roomtype_id
    WHERE h.hotel_id IN (SELECT hotel_id FROM AvailableHotels)
    AND sr.season_id IN (SELECT season_id FROM SeasonsCovered) 
    HAVING availableRooms > 0
    AND EXISTS(SELECT 1 FROM CheckinCovered)
    AND EXISTS(SELECT 1 FROM CheckoutCovered)
    AND NOT EXISTS (SELECT 1 FROM GapExists);""", nativeQuery = true)
    List<AvailabilityProjection> findAvailableSeasonalRoomtypes(
        @Param("hotelId") Long hotelId,
        @Param("guestCount") int guestCount,
        @Param("bCheckinDate") Date checkinDate,
        @Param("bCheckoutDate") Date checkoutDate
    );
    
}