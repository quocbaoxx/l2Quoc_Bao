package com.exampledelivery.deliveryinfo.repository;


import com.exampledelivery.deliveryinfo.model.DefaultDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultDeliveryRepository  extends JpaRepository<DefaultDelivery, Long> {


    @Query(value = "SELECT * FROM cf_default_delivery WHERE location_id = :location_id", nativeQuery = true)
    DefaultDelivery findByLocationId(@Param("location_id") Long locationId);
}
