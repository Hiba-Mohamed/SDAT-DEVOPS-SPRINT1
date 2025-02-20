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
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

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

    // ✅ Set the correct API endpoint URL
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
            // Build the airport list from the response
            airports = buildAirportListFromResponse(response.body());
            System.out.println("Airports in City ID " + cityID + ":");
            // Print out the airports
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
        System.out.println("Logic not added Yet");
        }

    private void giveMePassengerIdIReturnAllAirportsTheyUsed(){
        System.out.println("Logic not added Yet");
        }
        
    private void exitApplication() {
        System.out.println("Exiting application...");
        System.exit(0);
    }


    public List<Airport> buildAirportListFromResponse(String response) throws JsonProcessingException {
        List<Airport> airports = new ArrayList<Airport>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        airports = mapper.readValue(response, new TypeReference<List<Airport>>(){});

        return airports;
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
