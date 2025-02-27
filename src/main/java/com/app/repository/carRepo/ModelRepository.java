package com.app.repository.carRepo;

import com.app.entity.cars.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query("SELECT m FROM Model m WHERE m.modelName =:modelName")
    Optional<Model> findByModelName(@Param("modelName") String modelName);
}