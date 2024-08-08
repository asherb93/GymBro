package com.example.gymbro.Models;

import java.util.ArrayList;

public class AppUser {
    private String name;
    private int weight;

    public AppUser(){

    }

    public AppUser(String name, int weight){
        this.name = name;
        this.weight = weight;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
