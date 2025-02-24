package com.keyin.http.client;

import com.fasterxml.jackson.annotation.JsonProperty;
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
import java.util.Collections;
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


public List<AirportDisplay> giveMeCityIdIReturnAllItsAirports(int cityID) {
    String apiUrl = "http://localhost:8080/airportsByCity/" + cityID;
List<AirportDisplay> allAirportDisplays = new ArrayList<>();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

    try {
        HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) { 
            System.out.println("Airports in City " +cityID+ ": ");
            System.out.println("");

             allAirportDisplays = buildAirportDisplayListFromResponse(response.body()); 

        } else {
            System.out.println("Error Status Code: " + response.statusCode());
        }


    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }
    return allAirportDisplays;
}


    public void giveMeAircraftIdIReturnAllAirportsItCanUse(int aircraftID){
            String apiUrl = "http://localhost:8080/" + aircraftID + "/aircraftsAirportsList";

    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

    try {
        HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) { 
            System.out.println("Aircraft " + aircraftID+" Can Use Airports Below: ");
            System.out.println("");

            List<AirportDisplay> allAirportDisplays = buildAirportDisplayListFromResponse(response.body()); 

        } else {
            System.out.println("Error Status Code: " + response.statusCode());
        }


    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }
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
            "\"cityId\": " + cityidRandom + "\n" +
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
                "  \"cityId\": " + number + "\n" +
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
                "  \"numberOfPassengers\": " + number * 10 + "\n" +
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

    public void addPassengersToAircraft(int number) {
        int allowedNumberToMultiply = 1 + (int)(Math.random() * 3);
        String apiUrl = "http://localhost:8080/updateAircraftPassengersList/" + number; 
        String jsonBody = "[" +
                (1 * allowedNumberToMultiply) + "," +
                (2 * allowedNumberToMultiply) + "," +
                (3 * allowedNumberToMultiply) + "," +
                (4 * allowedNumberToMultiply) + "," +
                (5 * allowedNumberToMultiply) + "," +
                (6 * allowedNumberToMultiply) + "," +
                (7 * allowedNumberToMultiply) + "," +
                (8 * allowedNumberToMultiply) + "," +
                (9 * allowedNumberToMultiply) + "," +
                (10 * allowedNumberToMultiply) +
                "]";
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .header("Content-Type", "application/json") 
            .PUT(BodyPublishers.ofString(jsonBody)) 
            .build();

        try {
            // Sending HTTP request
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Handling response
            if (response.statusCode() == 200) { 
                System.out.println("PassengerList added successfully for Aircraft: " + response.body());
            } else {
                System.out.println("Error: " + response.statusCode() + " - " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

        public void addAirportsToAircraft(int number) {
        String apiUrl = "http://localhost:8080/updateAircraftAirportList/" + number; 
        int secondAirportNumberWithinRange = 1 + (int)(Math.random() * 10);
        String jsonBody = "[" +
                (1 * number) + "," +
                secondAirportNumberWithinRange +
                "]";
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .header("Content-Type", "application/json") 
            .PUT(BodyPublishers.ofString(jsonBody)) 
            .build();

        try {
            // Sending HTTP request
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Handling response
            if (response.statusCode() == 200) { 
                System.out.println("AirportList added successfully for Aircraft: " + response.body());
            } else {
                System.out.println("Error: " + response.statusCode() + " - " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void InsertSampleData(){
        for(int i=1;i<11;i++){
            createCity(i);
        }

        for(int i=1;i<11;i++){
           createAirport(i);
        }

        for(int i=1;i<11;i++){
            createAircraft(i);
        }
        for(int i=1;i<31;i++){
           createPassenger(i);
        }

        for (int i=1;i<11;i++){
            addPassengersToAircraft(i);
        }

        for (int i=1;i<11;i++){
            addAirportsToAircraft(i);
        }
    }

//public void giveMePassengerIdIReturnAllAircraftsTheyTravelledOn(long passengerId) {
//
//    String apiUrl = "http://localhost:8080/" + passengerId + "/aircraftsPassengerTravelledOn";
//
//    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();
//
//    try {
//        HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
//
//        if (response.statusCode() == 200) {
//            System.out.println("Passenger " + passengerId +" Travelled on Airraft Below: ");
//            System.out.println("");
//
//            List<AircraftDisplay> allAircraftDisplays = buildAircraftDisplayListFromResponse(response.body());
//
//        } else {
//            System.out.println("Error Status Code: " + response.statusCode());
//        }
//
//
//    } catch (IOException | InterruptedException e) {
//        e.printStackTrace();
//    }
//
//}

    public List<String> giveMePassengerIdIReturnAllAircraftsTheyTravelledOn(long passengerId) {
        String apiUrl = "http://localhost:8080/" + passengerId + "/aircraftsPassengerTravelledOn";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

        try {
            HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Passenger " + passengerId + " travelled on aircrafts below: ");
                System.out.println("");

                List<AircraftDisplay> allAircraftDisplays = buildAircraftDisplayListFromResponse(response.body());

                List<String> aircraftNames = new ArrayList<>();
                for (AircraftDisplay aircraft : allAircraftDisplays) {
                    aircraftNames.add(aircraft.getAirlineName());
                }
                return aircraftNames;  // Return the list
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
                return Collections.emptyList();  // Return an empty list in case of error
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return Collections.emptyList();  // Return an empty list in case of exception
        }
    }


public void giveMePassengerIdIReturnAllAirportsTheyHaveUsed(long passengerId){
    String apiUrl = "http://localhost:8080/getAirportByPassengerId/" + passengerId;

    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

    try {
        HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) { 
            System.out.println("Passenger  " + passengerId +" Have used airports below: ");
            System.out.println("");

            List<AirportDisplay> allAirportDisplays = buildAirportDisplayListFromResponse(response.body()); 

        } else {
            System.out.println("Error Status Code: " + response.statusCode());
        }


    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }
}

public static class AircraftDisplay {  // Add static keyword here
    @JsonProperty("craftId")
    private long craftId;
    
    @JsonProperty("type")
    private String type;
    
    @JsonProperty("airlineName")
    private String airlineName;

    // Getters and Setters
    public long getCraftId() {
        return craftId;
    }

    public void setCraftId(long craftId) {
        this.craftId = craftId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
               "craftId=" + craftId +
               ", type='" + type + '\'' +
               ", airlineName='" + airlineName + '\'' +
               '}';
    }
}


public static class AirportDisplay {  
    @JsonProperty("airportId")
    private long airportId;
    
    @JsonProperty("airportName")
    private String airportName;
    
    @JsonProperty("airportCode")
    private String airportCode;
    
    @JsonProperty("airportCity")
    private String airportCity;

    public long getAirportId() {
        return airportId;
    }

    public void setAirportId(long airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportCity() {
        return airportCity;
    }

    public void setAirportCity(String airportCity) {
        this.airportCity = airportCity;
    }

    @Override
    public String toString() {
        return "Airport{" +
               "airportId=" + airportId +
               ", airportName='" + airportName + '\'' +
               ", airportCode='" + airportCode + '\'' +
               ", airportCity='" + airportCity + '\'' +
               '}';
    }
}


    public List<Airport> buildAirportListFromResponse(String response) throws JsonProcessingException {
        List<Airport> airports = new ArrayList<Airport>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        airports = mapper.readValue(response, new TypeReference<List<Airport>>(){});

        return airports;
    }

    public List<AircraftDisplay> buildAircraftDisplayListFromResponse(String response) throws JsonProcessingException {
        List<AircraftDisplay> aircrafts = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        aircrafts = mapper.readValue(response, new TypeReference<List<AircraftDisplay>>() {});

        for (AircraftDisplay aircraft : aircrafts) {
            System.out.println(aircraft); 
        }

        return aircrafts;
    }

        public List<AirportDisplay> buildAirportDisplayListFromResponse(String response) throws JsonProcessingException {
        List<AirportDisplay> airports = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        airports = mapper.readValue(response, new TypeReference<List<AirportDisplay>>() {});

        for (AirportDisplay airport : airports) {
            System.out.println(airport); 
        }

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
