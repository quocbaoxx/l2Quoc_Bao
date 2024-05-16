package com.exampledelivery.deliveryinfo.repository;

import com.exampledelivery.deliveryinfo.model.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHouse, Long> {
}
