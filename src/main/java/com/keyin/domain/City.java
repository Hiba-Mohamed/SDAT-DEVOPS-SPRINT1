package com.keyin.domain;

import java.util.List;

public class City {
    private long id;
    private String name;
    private String state;
    private int population;
    private List<Airport> airports;
    private List<Passenger> passengers;

    public City() {
    }

    public City(long id, String name, String state, int population, List<Airport> airports, List<Passenger> passengers) {
        this.id = id;
        this.name=  name;
        this.state = state;
        this.population = population;
        this.airports = airports;
        this.passengers = passengers;
    }

    //getters
    public long getId(){ return this.id; }

    public String getName(){ return this.name; }

    public String getState(){return this.state; }

    public int getPopulation(){ return this.population; }

    public List<Airport> getAirports() { return airports; }

    public List<Passenger> getPassengers() { return passengers; }

    //setters
    public void setId(long id){ this.id = id; }

    public void setName(String name){ this.name = name; }

    public void setState(String state){ this.state = state; }

    public void setPopulation(int population){ this.population = population; }

    public void setAirports(List<Airport> airports) { this.airports = airports; }

    public void setPassengers(List<Passenger> passengers) {this.passengers = passengers; }
}
