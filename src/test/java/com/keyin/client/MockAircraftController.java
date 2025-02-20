package com.keyin.client;

import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;
import com.keyin.domain.City;
import com.keyin.domain.Passenger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/mock-api")
public class MockAircraftController {

    @GetMapping("/aircraft")
    public List<Aircraft> getMockAircraft() {
        City toronto = new City("Toronto");
        City newYork = new City("New York");

        Airport pearson = new Airport("YYZ", "Toronto Pearson International Airport", toronto, null);
        Airport jfk = new Airport("JFK", "John F. Kennedy International Airport", newYork, null);

        Passenger passenger1 = new Passenger("John", "Doe", "1234567");
        Passenger passenger2 = new Passenger("Jane", "Smith", "1209645");

        Aircraft boeing747 = new Aircraft("Boeing 747", "Air Canada", 300,
                Arrays.asList(passenger1, passenger2), Arrays.asList(pearson, jfk));

        return Arrays.asList(boeing747);
    }
}
