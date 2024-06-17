package com.example.course3.entities;

import jakarta.persistence.Entity;

@Entity
public class Flower extends Plant {
    String colour;

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
