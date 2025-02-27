package com.app.entity.evaluation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car_detailed_evaluation")
public class CarDetailedEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "kms", nullable = false)
    private Integer kms;

    @Column(name = "year_df_manufacturing", nullable = false)
    private Integer yearOfManufacturing;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKms() {
        return kms;
    }

    public void setKms(Integer kms) {
        this.kms = kms;
    }

    public Integer getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(Integer yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }
}