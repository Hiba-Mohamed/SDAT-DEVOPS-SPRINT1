package com.keyin.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.domain.Airport;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.keyin.domain.Aircraft;
import com.keyin.domain.City;
import com.keyin.domain.Passenger;

public class RESTClient {
    private String serverURL;
    private HttpClient client;

    public String getResponseFromHTTPRequest() {
        String responseBody = "";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL)).build();

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()!=200) {
                System.out.println("Status Code: " + response.statusCode());
            }

            responseBody = response.body();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return responseBody;
    }


public List<Airport> getAllAirports() {
    List<Airport> airports = new ArrayList<>();

    String apiUrl = "http://localhost:8080/airports";

    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

    try {
        HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            List<Airport> allAirportsArray = buildAirportListFromResponse(response.body()); 
            System.out.println(allAirportsArray);
        } else {
            System.out.println("Error Status Code: " + response.statusCode());
        }

        airports = buildAirportListFromResponse(response.body());

    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }

    return airports;
}


public List<Airport> giveMeCityIdIReturnAllItsAirports(int cityID) {
    List<Airport> airports = new ArrayList<>();
    String apiUrl = "http://localhost:8080/airportsByCity/" + cityID;

    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

    try {
        HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            airports = buildAirportListFromResponse(response.body());
            System.out.println("Airports in City ID " + cityID + ":");
            for (Airport airport : airports) {
                System.out.println(airport.getName() + " - " + airport.getCode());
            }
        } else {
            System.out.println("Error Status Code: " + response.statusCode());
        }
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }

    return airports;
}


    private void giveMeAircraftIdIReturnAllAirportsItCanUse(){
        
    //     System.out.println("Logic not added Yet");
    //     }

    // public List<Aircraft> giveMePassengerIdIReturnAllAircraftsTheyTravelledOn(int passengerID){
    //     List<Aircraft> aicrafts = new ArrayList<>();
    //         String apiUrl = "http://localhost:8080/aicraftsByCity/" + cityID;

    //         HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

    //         try {
    //             HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());

    //             if (response.statusCode() == 200) {
    //                 // Build the aicraft list from the response
    //                 aicrafts = buildAircraftListFromResponse(response.body());
    //                 System.out.println("Aicrafts in City ID " + cityID + ":");
    //                 // Print out the aicrafts
    //                 for (Aircraft aicraft : aircrafts) {
    //                     System.out.println(aicraft.getName() + " - " + aicraft.getCode());
    //                 }
    //             } else {
    //                 System.out.println("Error Status Code: " + response.statusCode());
    //             }
    //         } catch (IOException | InterruptedException e) {
    //             e.printStackTrace();
    //         }

    //         return aicrafts;
        }
        
    private void exitApplication() {
        System.out.println("Exiting application...");
        System.exit(0);
    }

    public void insertPassengerDataToDatabase(){

    }

    

    public void createCity(int number) {
        String apiUrl = "http://localhost:8080/city"; 

        String jsonBody = "{\n" +
            "  \"code\": \"Code" + number + "\",\n" +
            "  \"name\": \"City" + number + "\",\n" +
            "  \"state\": \"State" + number + "\",\n" +
            "  \"population\": " + (number * 1000) + ",\n" +  
            "  \"airports\": [],\n" +
            "  \"passengers\": []\n" +
        "}";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .header("Content-Type", "application/json") 
            .POST(BodyPublishers.ofString(jsonBody)) 
            .build();

        try {
            // Sending HTTP request
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Handling response
            if (response.statusCode() == 200) { 
                System.out.println("City created successfully: " + response.body());
            } else {
                System.out.println("Error: " + response.statusCode() + " - " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createPassenger(int number) {
        String apiUrl = "http://localhost:8080/passenger"; 
        int cityidRandom = 1 + (int)(Math.random() * 10);

        String jsonBody = "{\n" +
            "\"firstName\": \"Name" + number + "\",\n" +
            "\"lastName\": \"Last" + number + "\",\n" +
            "\"phoneNumber\": " + number + ",\n" +
            "\"cityID\": " + cityidRandom + "\n" +
        "}";


        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .header("Content-Type", "application/json") 
            .POST(BodyPublishers.ofString(jsonBody)) 
            .build();

        try {
            // Sending HTTP request
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Handling response
            if (response.statusCode() == 200) {
                System.out.println("Passenger created successfully: " + response.body());
            } else {
                System.out.println("Error: " + response.statusCode() + " - " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void createAirport(int number) {
        String apiUrl = "http://localhost:8080/airport"; 

        String jsonBody = "{\n" +
                "  \"code\": \"AAA" + number + "\",\n" +
                "  \"name\": \"Airport " + number + "\",\n" +
                "  \"cityID\": " + number + "\n" +
            "}";


        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .header("Content-Type", "application/json") 
            .POST(BodyPublishers.ofString(jsonBody)) 
            .build();

        try {
            // Sending HTTP request
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Handling response
            if (response.statusCode() == 200) { 
                System.out.println("Airport created successfully: " + response.body());
            } else {
                System.out.println("Error: " + response.statusCode() + " - " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

        public void createAircraft(int number) {
        String apiUrl = "http://localhost:8080/aircraft"; 

        String jsonBody = "{\n" +
                "  \"type\": \"Plane" + number + "\",\n" +
                "  \"airlineName\": \"Aircraft " + number + "\",\n" +
                "  \"numberOfPasseners\": " + number * 10 + "\n" +
            "}";


        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .header("Content-Type", "application/json") 
            .POST(BodyPublishers.ofString(jsonBody)) 
            .build();

        try {
            // Sending HTTP request
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Handling response
            if (response.statusCode() == 200) { 
                System.out.println("Aircraft created successfully: " + response.body());
            } else {
                System.out.println("Error: " + response.statusCode() + " - " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void InsertSampleData(){
       




   

    }


    public List<Airport> buildAirportListFromResponse(String response) throws JsonProcessingException {
        List<Airport> airports = new ArrayList<Airport>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        airports = mapper.readValue(response, new TypeReference<List<Airport>>(){});

        return airports;
    }

    
    public List<Aircraft> buildAircraftListFromResponse(String response) throws JsonProcessingException {
        List<Aircraft> aircrafts = new ArrayList<Aircraft>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        aircrafts = mapper.readValue(response, new TypeReference<List<Aircraft>>(){});
        return aircrafts;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public HttpClient getClient() {
        if (client == null) {
            client  = HttpClient.newHttpClient();
        }

        return client;
    }
}
