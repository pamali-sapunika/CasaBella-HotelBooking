// package com.casabella.backend.controllers;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.eq;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Disabled;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import java.util.List;
// import java.util.Arrays;
// import com.casabella.backend.model.Hotel;
// import com.casabella.backend.services.HotelService;
// import com.fasterxml.jackson.databind.ObjectMapper;

// public class HotelControllerTest {


//     private MockMvc mockMvc;

//     @Mock 
//     HotelService hotelService;

//     @InjectMocks
//     HotelController hotelController;

//     @BeforeEach
//     void setUp(){
//         MockitoAnnotations.openMocks(this);
//         mockMvc = MockMvcBuilders.standaloneSetup(hotelController).build();
//     }

//     @Test
//     void postHotelControllerTest() throws Exception{

//         Hotel hotel = new Hotel(
//             "Galadari Hotel - Sri Lanka",         
//             "Colombo",           
//             "Colombo",             
//             "Sri Lanka",                   
//             "Galle Road, Sri Lanka",        
//             "galadari@gmail.com", 
//             "070123456",           
//             "A luxury hotel", 
//             "Pamali Sapunika",              
//             5                        
//         );
//         when(hotelService.postHotel(hotel)).thenReturn(hotel);
//         mockMvc.perform(post("/hotel/add")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(new ObjectMapper().writeValueAsString(hotel))).andExpect(status().isCreated());

//     }



//     @Test
//     void getAllHotelsControllerTest() throws Exception{

//         List<Hotel> employeeList = Arrays.asList(
//             new Hotel(
//             "GalaDari Hotel",         
//             "Colombo",           
//             "Colombo",             
//             "Sri Lanka",                   
//             "Galle Road, Sri Lanka",        
//             "galadari@gmail.com", 
//             "070123456",           
//             "A luxury hotel", 
//             "Pamali Sapunika",              
//             5                        
//             ),
//             new Hotel(
//                 "Hilton Hotel",         
//                 "Galle",           
//                 "Galle",             
//                 "Sri Lanka",                   
//                 "Galle Road, Galle, Sri Lanka",        
//                 "hilton@gmail.com", 
//                 "070123456",           
//                 "A luxury hotel at Galle", 
//                 "Pamali Sapunika",              
//                 4                       
//             )
//         );

//         when(hotelService.getAllHotels()).thenReturn(employeeList);
//         mockMvc.perform(get("/hotel/")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$[0].hotel_name").value("GalaDari Hotel"));

//     }


//     @Disabled
//     @Test
//     void getHotelByIdControllerTest(){

//     }


//     @Test
//     void updateHotelControllerTest() throws Exception{

//         Long hotelID = 1L;
//         Hotel existingHotel = new Hotel(
//             "GalaDari Hotel",         
//             "Colombo",           
//             "Colombo",             
//             "Sri Lanka",                   
//             "Galle Road, Sri Lanka",        
//             "galadari@gmail.com", 
//             "070123456",           
//             "A luxury hotel", 
//             "Pamali Sapunika",              
//             5                        
//             );
//         Hotel updatedHotel = new Hotel(
//             "GalaDari Hotel updated",         
//             "Colombo center",           
//             "Colombo",             
//             "Sri Lanka",                   
//             "Galle Road, Sri Lanka",        
//             "galadari@gmail.com", 
//             "070123456",           
//             "A luxury hotel", 
//             "Pamali Sapunika",              
//             5                        
//         );

//         // Mocking the service to return the updated employee when updateEmployee is called
//         when(hotelService.updateHotel(eq(hotelID), any(Hotel.class))).thenReturn(updatedHotel);

//         String updatedHotelJson = new ObjectMapper().writeValueAsString(updatedHotel);

//         mockMvc.perform(patch("/hotel/update/{id}", hotelID)
//         .contentType(MediaType.APPLICATION_JSON)
//         .content(updatedHotelJson))
//         .andExpect(status().isOk())
//         .andExpect(jsonPath("$.hotel_name").value("GalaDari Hotel updated"))
//         .andExpect(jsonPath("$.city").value("Colombo center"))
//         .andExpect(jsonPath("$.state").value("Colombo"))
//         .andExpect(jsonPath("$.country").value("Sri Lanka"))
//         .andExpect(jsonPath("$.location").value("Galle Road, Sri Lanka"))
//         .andExpect(jsonPath("$.hotel_email").value("galadari@gmail.com"))
//         .andExpect(jsonPath("$.hotel_contact").value("070123456"))
//         .andExpect(jsonPath("$.description").value("A luxury hotel"))
//         .andExpect(jsonPath("$.hotel_person").value("Pamali Sapunika"))
//         .andExpect(jsonPath("$.star_rating").value(5));

//         // Verify that the updateEmployee method was called with the correct ID and details
//         verify(hotelService).updateHotel(eq(hotelID), any(Hotel.class));

//     }


//     @Test
//     void deleteHotelControllerTest() throws Exception{

//         Long hotelID = 1L;

//         doNothing().when(hotelService).deleteHotel(hotelID);

//         mockMvc.perform(delete("/hotel/delete/{id}", hotelID)
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string("hotel with ID " + hotelID + " deleted successfully"));

//         verify(hotelService).deleteHotel(hotelID);

//     }
    
    
// }
