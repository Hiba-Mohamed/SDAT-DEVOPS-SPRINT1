package com.keyin.domain;

public class Passengers {
    private long id;
    private String firstName;
    private String lastName;
    private int phoneNumber;

    public Passengers(){
    }

    public Passengers(long id, String firstName, String lastName, int phoneNumber){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    // getters
    public long getId(){ return this.id; }

    public String getFirstName(){ return this.firstName; }

    public String getLastName(){ return this.lastName; }

    public int getPhoneNumber(){ return  this.phoneNumber; }

    // setters
    public void setId(long id){ this.id = id; }

    public void setFirstName(String firstName){ this.firstName = firstName; }

    public void setLastName(String lastName){ this.lastName = lastName; }

    public void setPhoneNumber(int phoneNumber){ this.phoneNumber = phoneNumber;}
}
