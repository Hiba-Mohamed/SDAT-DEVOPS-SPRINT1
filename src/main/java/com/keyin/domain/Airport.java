package com.keyin.domain;


import java.util.List;
import java.util.Objects;

public class Airport {

    private long id;
    private String name;
    private String code;
    private City city;
    private List<Aircraft> aircraftList;

    public Airport() {
    }

    public Airport(String code) {
        this.code = code;
    }

    public Airport(long id, String name, String code, City city, List<Aircraft> aircraftList) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.city = city;
        this.aircraftList = aircraftList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Aircraft> getAircraftList() {
        return aircraftList;
    }

    public void setAircraftList(List<Aircraft> aircraftList) { this.aircraftList = aircraftList; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(code, airport.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
