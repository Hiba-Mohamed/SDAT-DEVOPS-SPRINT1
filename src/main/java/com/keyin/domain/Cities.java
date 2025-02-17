package com.keyin.domain;

public class Cities {
    private int id;
    private String name;
    private String state;
    private int population;

    public Cities() {
    }

    public Cities(int id, String name, String state, int population) {
        this.id = id;
        this.name=  name;
        this.state = state;
        this.population = population;
    }

    //getters
    public int getId(){ return this.id; }

    public String getName(){ return this.name; }

    public String getState(){return this.state; }

    public int getPopulation(){ return this.population; }

    //setters
    public void setId(int id){ this.id = id; }

    public void setName(String name){ this.name = name; }

    public void setState(String state){ this.state = state; }

    public void setPopulation(int population){ this.population = population; }
}
