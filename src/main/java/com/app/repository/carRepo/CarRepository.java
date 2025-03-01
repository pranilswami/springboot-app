package com.app.repository.carRepo;

import com.app.entity.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT c FROM Car c JOIN c.brand b JOIN c.transmission t JOIN c.fuelType f JOIN c.model m "+"WHERE b.brandName =:param OR t.type=:param OR f.fuelType=:param OR m.modelName=:param")
    List<Car> findCarsByParam(@Param("param") String param);

    @Query("SELECT c FROM Car c WHERE c.year.year >= :year")
    List<Car> findCarsByParam(@Param("year") int year);

    @Query("SELECT c FROM Car c WHERE c.carStatus.status = :status")
    List<Car> findCarIdsByStatus(@Param("status") String status);

    @Query("SELECT c FROM Car c WHERE c.brand.brandName IN :brands AND c.carStatus.id = :status_id")
    List<Car> findByBrandNameIN(@Param("brands") List<String> brands,@Param("status_id") Long status_id);

}