package com.keyin.domain;

import java.util.List;

public class Aircraft {
    private long id;
    private String type;
    private String airlineName;
    private int numberOfPassengers;
    private List<Passenger> passengers;
    private List<Airport> airports;

    public Aircraft(){
    }
    public Aircraft(long id, String type, String airlineName, int numberOfPassengers, List<Passenger> passengers, List<Airport> airports){
        this.id = id;
        this.type = type;
        this.airlineName = airlineName;
        this.numberOfPassengers = numberOfPassengers;
        this.passengers=passengers;
        this.airports=airports;
    }
    //getters
    public long getId(){
        return this.id;
    }

    public String getType(){
        return this.type;
    }

    public String getAirlineName(){
        return this.airlineName;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public List<Passenger> getPassengers() { return passengers;}

    public List<Airport> getAirports() { return airports;}

    //setters
    public void setId(long id){
        this.id = id;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }

    public void setAirports(List<Airport> airports) { this.airports = airports; }
}
