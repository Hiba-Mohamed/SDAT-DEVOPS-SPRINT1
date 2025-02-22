package com.keyin.domain;

import java.util.List;

public class Passenger {
    private long id;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private City city;
    private List<Aircraft> aircraft;

    public Passenger(){
    }

    public Passenger(long id, String firstName, String lastName, int phoneNumber, City city, List<Aircraft> aircraft){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.aircraft = aircraft;
    }

    // getters
    public long getId(){ return this.id; }

    public String getFirstName(){ return this.firstName; }

    public String getLastName(){ return this.lastName; }

    public int getPhoneNumber(){ return  this.phoneNumber; }

    public City getCity() { return city; }

    public List<Aircraft> getAircraft() { return aircraft; }

    // setters
    public void setId(long id){ this.id = id; }

    public void setFirstName(String firstName){ this.firstName = firstName; }

    public void setLastName(String lastName){ this.lastName = lastName; }

    public void setPhoneNumber(int phoneNumber){ this.phoneNumber = phoneNumber;}

    public void setCity(City city) { this.city = city; }

    public void setAircraft(List<Aircraft> aircraft) { this.aircraft = aircraft; }
}
