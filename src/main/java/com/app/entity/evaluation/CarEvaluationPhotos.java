package com.app.entity.evaluation;

import jakarta.persistence.*;


@Entity
@Table(name = "car_evaluation_photos")
public class CarEvaluationPhotos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="photo_url", length = 10000)
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "car_detailed_evaluation_id")
    private CarDetailedEvaluation carDetailedEvaluation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}