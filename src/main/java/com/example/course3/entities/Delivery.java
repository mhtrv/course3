package com.example.course3.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;
import java.util.List;

@NamedQuery(
    name = "Delivery.findByName",
    query = "Select d from Delivery d where d.name = :name"
)
@Entity
public class Delivery {
    @Id
    @GeneratedValue
    Long id;
    @Nationalized

    String name;
    @Column(name="address_full",length=500)
    String address;
    LocalDateTime deliveryTime;
    @Convert(converter = org.hibernate.type.YesNoConverter.class)
    Boolean completed;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "delivery", cascade = CascadeType.ALL)
    List<Plant> plants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
