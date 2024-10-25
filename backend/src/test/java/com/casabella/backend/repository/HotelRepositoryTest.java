// package com.casabella.backend.repository;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.Optional;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// import com.casabella.backend.model.Hotel;


// @DataJpaTest
// public class HotelRepositoryTest {

//     @Autowired
//     private HotelRepository underTest;

//     @AfterEach
//     void tearDown(){
//         underTest.deleteAll();
//     }

//     @Test
//     void findHotelByNameTest(){

//         //given
//         Hotel hotel = new Hotel();
//         String testingName = "Galadari Hotel";
//         // Employee emp = new Employee("Pamali", testingName, "124", "Department");
//         hotel.setHotel_name(testingName);
//         underTest.save(hotel);

//         //when
//         Optional<Hotel> foundHotel = underTest.findHotelByName(testingName);

//         //then
//         assertTrue(foundHotel.isPresent(), "Hotel should be found");
//         assertEquals(testingName, foundHotel.get().getHotel_name(), "Hotel Name shoould match");
        
//     }

    
// }
