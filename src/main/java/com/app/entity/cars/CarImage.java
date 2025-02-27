package com.app.entity.cars;

import jakarta.persistence.*;

@Entity
@Table(name = "car_image")
public class CarImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Car getCar() {
//        return car;
//    }

    public void setCar(Car car) {
        this.car = car;
    }

//    public String getUrl() {
//        return url;
//    }

    public void setUrl(String url) {
        this.url = url;
    }
}