package com.app.repository.evaluation;

import com.app.entity.evaluation.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {
    @Query("SELECT a FROM Area a WHERE a.pinCode =:pinCode")
    List<Area> findByPinCode(@Param("pinCode") Integer pinCode);

    @Query("SELECT a FROM Area a WHERE a.pinCode =:pinCode")
    Optional<Area> existsByPinCode(@Param("pinCode") Integer pinCode);
}