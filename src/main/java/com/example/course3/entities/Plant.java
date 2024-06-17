package com.example.course3.entities;

import com.example.course3.Views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Plant {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    @JsonView(Views.Public.class)
    private String name;

    @Column(precision=12, scale=4)
    @JsonView(Views.Public.class)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="delivery_id")
    Delivery delivery;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
