package com.keyin.domain;

public class Aircraft {
    private long id;
    private String type;
    private String airlineName;
    private int numberOfPassengers;

    public Aircraft(){
    }
    public Aircraft(long id, String type, String airlineName, int numberOfPassengers){
        this.id = id;
        this.type = type;
        this.airlineName = airlineName;
        this.numberOfPassengers = numberOfPassengers;
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
}
