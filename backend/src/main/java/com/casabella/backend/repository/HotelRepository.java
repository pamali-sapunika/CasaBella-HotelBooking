package com.casabella.backend.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.casabella.backend.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{

    //select * from hotel where email = ?
    @Query("SELECT h FROM Hotel h WHERE h.hotelName = ?1")
    Optional<Hotel> findHotelByName(String hotelName);

    @Query(value = """
    With SeasonsCovered as (
        select s.start_date, s.end_date
        from season s
        where (s.start_date <= :bCheckinDate and s.end_date >= :bCheckinDate)
        or (s.start_date <= :bCheckoutDate and s.end_date >= :bCheckoutDate)
    ),
    CheckinCovered as (
        SELECT 1 
        FROM season s
        WHERE s.start_date <= :bCheckinDate AND s.end_date >= :bCheckinDate
    ),
    CheckoutCovered as (
        SELECT 1
        FROM season s
        WHERE s.start_date <= :bCheckoutDate AND s.end_date >= :bCheckoutDate
    ),
    GapExists as (
        SELECT 1
        FROM SeasonsCovered sc1
        WHERE sc1.end_date < :bCheckoutDate
        AND NOT EXISTS (
            SELECT 1
            FROM SeasonsCovered sc2
            WHERE sc2.start_date = sc1.end_date + INTERVAL 1 DAY
            )
    ),
    AvailableHotels as (
        select h.hotel_id
        from hotel h
        join seasonal_roomtype sr ON h.hotel_id = sr.hotel_id
        join roomtype rt ON rt.roomtype_id = sr.roomtype_id
        group by h.hotel_id
        HAVING SUM((sr.noof_rooms - COALESCE((
            select SUM(brt.no_of_rooms)
            from booking_roomtypes brt
            where brt.seasonal_roomtype_id = sr.seasonal_roomtype_id
            and brt.b_checkin_date < :bCheckinDate 
            and brt.b_checkout_date > :bCheckoutDate  
        ), 0)) * sr.max_adults ) >= :guestCount 
    )

    SELECT h.*
    FROM hotel h
    WHERE h.hotel_id IN (SELECT hotel_id FROM AvailableHotels)
    AND EXISTS(SELECT 1 FROM CheckinCovered)
    AND EXISTS(SELECT 1 FROM CheckoutCovered)
    AND NOT EXISTS (SELECT 1 FROM GapExists)""", nativeQuery = true)
    List<Hotel> findRoomsAvailableHotels(
        @Param("guestCount") int guestCount,
        @Param("bCheckinDate") Date checkinDate,
        @Param("bCheckoutDate") Date checkoutDate
    );




}
