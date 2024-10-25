// package com.casabella.backend.services;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.ArgumentCaptor;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.transaction.IllegalTransactionStateException;
// import static org.assertj.core.api.Assertions.assertThatThrownBy;
// import com.casabella.backend.model.Hotel;
// import com.casabella.backend.repository.HotelRepository;

// import java.util.Optional;



// @ExtendWith(MockitoExtension.class)
// public class HotelServiceTest {

//     @Mock
//     private HotelRepository hotelRepo; 
    
//     @InjectMocks
//     private HotelService underTest;  

//     @BeforeEach
//     void setUp() {
//         underTest = new HotelService(hotelRepo); 
//     }


//     @Test
//     void postHotelTest(){

//         //given
//         Hotel hotel = new Hotel(
//             "GalaDari Hotel",         
//             "Colombo",           
//             "Colombo",             
//             "Sri Lanka",                   
//             "Galle Road, Sri Lanka",        
//             "galadarigmail.com", 
//             "070123456",           
//             "A luxury hotel", 
//             "Pamali Sapunika",              
//             5                        
//         );

//         //when
//         underTest.postHotel(hotel);
//         //then
//         ArgumentCaptor<Hotel> hotelArgumentCaptor = ArgumentCaptor.forClass(Hotel.class);
//         verify(hotelRepo).save(hotelArgumentCaptor.capture());   //capture thehotel object inside this save method
//         Hotel capturedHotel = hotelArgumentCaptor.getValue();
        
//         assertEquals(capturedHotel, hotel);    // hotel -> Hotel underTest receives. capturedHotel -> what original service captures

//     }


//     @Test
//     void getAllHotelsTest() {
//         // when
//         underTest.getAllHotels();  
//         // then
//         verify(hotelRepo).findAll();  //this repo was invoked using this method
//     }


//     @Test
//     void findHotelByNameSubMethodTest(){

//         //given
//         Hotel hotel = new Hotel();
//         String testingEmail= "Galadari Hotel";
//         hotel.setHotel_name(testingEmail);

//         //when
//         when(hotelRepo.findHotelByName(hotel.getHotel_name()))
//             .thenReturn(Optional.of(hotel));

//         //then
//         assertThatThrownBy(() -> underTest.postHotel(hotel))
//         .isInstanceOf(IllegalTransactionStateException.class)
//         .hasMessageContaining("Hotel name taken");

//     }
    

//     @Test
//     void getHotelByIdTest() {

//         // given
//         Long hotelId = 1L;
//         Hotel hotel = new Hotel(
//             "GalaDari Hotel",         
//             "Colombo",           
//             "Colombo",             
//             "Sri Lanka",                   
//             "Galle Road, Sri Lanka",        
//             "galadarigmail.com", 
//             "070123456",           
//             "A luxury hotel", 
//             "Pamali Sapunika",              
//             5                        
//         );
    
//         when(hotelRepo.findById(hotelId)).thenReturn(Optional.of(hotel));
//         // when
//         Hotel foundHotel = underTest.getHotelById(hotelId);
//         // then
//         assertEquals(hotel, foundHotel);
//     }
    



//     @Test
//     void updateHotelTest() {

//         // given
//         Long hotelId = 1L;
//         Hotel existingHotel = new Hotel(
//                 "GalaDari Hotel",         
//                 "Colombo",           
//                 "Colombo",             
//                 "Sri Lanka",                   
//                 "Galle Road, Sri Lanka",        
//                 "galadarigmail.com", 
//                 "070123456",           
//                 "A luxury hotel", 
//                 "Pamali Sapunika",              
//                 5                        
//             );

//         Hotel updatedHotel = new Hotel(
//                 "New Galadari Hotel",         
//                 "Colombo",           
//                 "Colombo",             
//                 "Sri Lanka",                   
//                 "204/5, Galle Road, Sri Lanka",        
//                 "galadarigmail.com", 
//                 "070123456",           
//                 "A luxury hotel updated details", 
//                 "Pamali Sapunika",              
//                 5                        
//             );

//         when(hotelRepo.findById(hotelId)).thenReturn(Optional.of(existingHotel));

//         // when
//         underTest.updateHotel(hotelId, updatedHotel);
//         // then
//         verify(hotelRepo).save(updatedHotel);
//     }



//     @Test
//     void deleteHotelTest() {
//         // given
//         Long hotelId = 1L;
//         Hotel hotel = new Hotel(
//             "Sunshine Hotel",         
//             "Los Angeles",           
//             "California",             
//             "USA",                   
//             "123 Sunset Blvd",        
//             "info@sunshinehotel.com", 
//             "123-456-7890",           
//             "A luxury hotel with excellent services.", 
//             "John Doe",              
//             5                        
//         );
    
//         when(hotelRepo.findById(hotelId)).thenReturn(Optional.of(hotel));
    
//         // when
//         underTest.deleteHotel(hotelId);
    
//         // then
//         verify(hotelRepo).delete(hotel);
//     }
    

    



    
// }
