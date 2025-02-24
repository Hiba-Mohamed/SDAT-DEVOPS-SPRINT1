 package com.keyin;

 import com.keyin.http.cli.HTTPRestCLIApplication;
 import com.keyin.http.client.RESTClient;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.mockito.junit.jupiter.MockitoExtension;


 import java.util.Collections;
 import java.util.List;

 import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.junit.jupiter.api.Assertions.assertNotNull;
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

 }
