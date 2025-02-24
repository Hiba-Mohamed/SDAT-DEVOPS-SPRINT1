 package com.keyin;

 import com.keyin.http.cli.HTTPRestCLIApplication;
 import com.keyin.http.client.RESTClient;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.mockito.junit.jupiter.MockitoExtension;


 import java.util.Collections;
 import java.util.List;

 import static org.junit.jupiter.api.Assertions.*;
 import static org.mockito.Mockito.*;

 @ExtendWith(MockitoExtension.class)
 public class HTTPRestCLIApplicationTest {
     private HTTPRestCLIApplication cliApp;
     private RESTClient mockRestClient;

     @BeforeEach
     public void setUp() {
         cliApp = new HTTPRestCLIApplication();
         mockRestClient = mock(RESTClient.class);
         cliApp.setRestClient(mockRestClient);
     }

     @Test
     public void testGiveMeCityIdIReturnAllItsAirports() {
         when(mockRestClient.giveMeCityIdIReturnAllItsAirports(1)).thenReturn(Collections.emptyList());

         List<RESTClient.AirportDisplay> result = cliApp.giveMeCityIdIReturnAllItsAirports(1);

         verify(mockRestClient, times(1)).giveMeCityIdIReturnAllItsAirports(1);
         assertNotNull(result);
         assertEquals(0, result.size());
     }

     @Test
     public void testGiveMePassengerIdIReturnAllAircraftsTheyTravelledOn() {
         when(mockRestClient.giveMePassengerIdIReturnAllAircraftsTheyTravelledOn(2)).thenReturn(List.of("Boeing 747"));

         List<String> result = cliApp.giveMePassengerIdIReturnAllAircraftsTheyTravelledOn(2);

         verify(mockRestClient, times(1)).giveMePassengerIdIReturnAllAircraftsTheyTravelledOn(2);
         assertNotNull(result);
         assertEquals(1, result.size());
         assertEquals("Boeing 747", result.get(0));
     }

     @Test
     public void testGiveMeAircraftIdIReturnAllAirports() {
         when(mockRestClient.giveMeAircraftIdIReturnAllAirportsItCanUse(3)).thenReturn(List.of("Airport1", "Airport2"));

         List<String> result = cliApp.getRestClient().giveMeAircraftIdIReturnAllAirportsItCanUse(3);

         verify(mockRestClient, times(1)).giveMeAircraftIdIReturnAllAirportsItCanUse(3);
         assertNotNull(result);
         assertEquals(2, result.size());
         assertTrue(result.contains("Airport1"));
         assertTrue(result.contains("Airport2"));
     }

     @Test
     void testGiveMePassengerIdIReturnAllAirportsTheyUsed() {
         when(mockRestClient.giveMePassengerIdIReturnAllAirportsTheyHaveUsed(4)).thenReturn(List.of("Airport1", "Airport2"));

         List<String> result = cliApp.giveMePassengerIdIReturnAllAirportsTheyUsed(4);

         verify(mockRestClient, times(1)).giveMePassengerIdIReturnAllAirportsTheyHaveUsed(4);
         assertNotNull(result);
         assertEquals(2, result.size());
         assertTrue(result.contains("Airport1"));
         assertTrue(result.contains("Airport2"));
     }
 }
