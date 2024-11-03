package com.casabella.backend.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.casabella.backend.dto.AvailabilityProjection;
import com.casabella.backend.model.SeasonalRoomtype;

public interface SeasonalRoomtypeRepo extends JpaRepository<SeasonalRoomtype, Long>{


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
	where h.hotel_id = :hotelId
	group by h.hotel_id
	HAVING SUM((sr.noof_rooms - COALESCE((
		select SUM(brt.no_of_rooms)
		from booking_roomtypes brt
		where brt.seasonal_roomtype_id = sr.seasonal_roomtype_id
		and brt.b_checkin_date < :bCheckinDate 
		and brt.b_checkout_date > :bCheckoutDate  
	), 0)) * sr.max_adults ) >= :guestCount 
)

SELECT sr.seasonal_roomtype_id as seasonalRoomtypeId, sr.roomtype_id as roomtypeId, rt.roomtype_name as roomtypeName, sr.noof_rooms as noofRooms, sr.price as price, sr.max_adults as maxAdults, sr.noof_reserved_rooms as noofReservedRooms, sr.season_id as seasonId, s.markup_percentage as markupPercentage, sr.hotel_id as hotelId, c.contract_id as contractId
FROM hotel h
join seasonal_roomtype sr ON h.hotel_id = sr.hotel_id
join season s on s.season_id = sr.season_id
join contract c on c.contract_id = s.contract_id
join roomtype rt ON rt.roomtype_id = sr.roomtype_id
WHERE h.hotel_id IN (SELECT hotel_id FROM AvailableHotels)
AND EXISTS(SELECT 1 FROM CheckinCovered)
AND EXISTS(SELECT 1 FROM CheckoutCovered)
AND NOT EXISTS (SELECT 1 FROM GapExists)""", nativeQuery = true)
    List<AvailabilityProjection> findAvailableSeasonalRoomtypes(
        @Param("hotelId") Long hotelId,
        @Param("guestCount") int guestCount,
        @Param("bCheckinDate") Date checkinDate,
        @Param("bCheckoutDate") Date checkoutDate
    );

    // @Query(value = """
    // With SeasonsCovered as (
    //     select s.start_date, s.end_date
    //     from season s
    //     where (s.start_date <= :bCheckinDate and s.end_date >= :bCheckinDate)
    //     or (s.start_date <= :bCheckoutDate and s.end_date >= :bCheckoutDate)
    // ),
    // CheckinCovered as (
    //     SELECT 1 
    //     FROM season s
    //     WHERE s.start_date <= :bCheckinDate AND s.end_date >= :bCheckinDate
    // ),
    // CheckoutCovered as (
    //     SELECT 1
    //     FROM season s
    //     WHERE s.start_date <= :bCheckoutDate AND s.end_date >= :bCheckoutDate
    // ),
    // GapExists as (
    //     SELECT 1
    //     FROM SeasonsCovered sc1
    //     WHERE sc1.end_date < :bCheckoutDate
    //     AND NOT EXISTS (
    //         SELECT 1
    //         FROM SeasonsCovered sc2
    //         WHERE sc2.start_date = sc1.end_date + INTERVAL 1 DAY
    //         )
    // ),
    // AvailableHotels as (
    //     select h.hotel_id
    //     from hotel h
    //     join seasonal_roomtype sr ON h.hotel_id = sr.hotel_id
    //     join roomtype rt ON rt.roomtype_id = sr.roomtype_id
    //     where h.hotel_id = :hotelId
    //     group by h.hotel_id
    //     HAVING SUM((sr.noof_rooms - COALESCE((
    //         select SUM(brt.no_of_rooms)
    //         from booking_roomtypes brt
    //         where brt.seasonal_roomtype_id = sr.seasonal_roomtype_id
    //         and brt.b_checkin_date < :bCheckinDate 
    //         and brt.b_checkout_date > :bCheckoutDate  
    //     ), 0)) * sr.max_adults ) >= :guestCount 
    // )

    // SELECT sr.*
    // FROM hotel h
    // join seasonal_roomtype sr ON h.hotel_id = sr.hotel_id
    // join season s on s.season_id = sr.season_id
    // join contract c on c.contract_id = s.contract_id
    // join roomtype rt ON rt.roomtype_id = sr.roomtype_id
    // WHERE h.hotel_id IN (SELECT hotel_id FROM AvailableHotels)
    // AND EXISTS(SELECT 1 FROM CheckinCovered)
    // AND EXISTS(SELECT 1 FROM CheckoutCovered)
    // AND NOT EXISTS (SELECT 1 FROM GapExists)""", nativeQuery = true)
    // List<SeasonalRoomtype> findRoomsAvailableSeasonalRoomtypes(
    //     @Param("hotelId") Long hotelId,
    //     @Param("guestCount") int guestCount,
    //     @Param("bCheckinDate") Date checkinDate,
    //     @Param("bCheckoutDate") Date checkoutDate
    // );

    
}