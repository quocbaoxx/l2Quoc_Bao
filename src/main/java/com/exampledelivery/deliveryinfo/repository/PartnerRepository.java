package com.exampledelivery.deliveryinfo.repository;

import com.exampledelivery.deliveryinfo.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

@Query(value = "SELECT \n" +
        "    ffm.partner_id AS partner_id,\n" +
        "    ffm.partner_name AS partner_name,\n" +
        "    STRING_AGG(CAST(wh.warehouse_id AS VARCHAR), ',') AS warehouse_ids,\n" +
        "    STRING_AGG(wh.warehouse_name, ',') AS warehouse_names,\n" +
        "    STRING_AGG(CAST(lm.partner_id AS VARCHAR), ',') AS delivery_partner_ids,\n" +
        "    STRING_AGG(CAST(lm.pn_type AS VARCHAR), ',') AS pn_type,\n" +
        "    STRING_AGG(lm.partner_name, ',') AS delivery_partner_names\n" +
        "FROM \n" +
        "    cf_default_delivery cf\n" +
        "JOIN \n" +
        "    bp_partner ffm ON cf.ffm_partner_id = ffm.partner_id\n" +
        "JOIN \n" +
        "    bp_partner lm ON cf.lm_partner_id = lm.partner_id\n" +
        "JOIN \n" +
        "    bp_warehouse wh ON cf.warehouse_id = wh.warehouse_id\n" +
        "WHERE \n" +
        "    ffm.partner_id = :partnerId\n" +
        "GROUP BY \n" +
        "    ffm.partner_id, ffm.partner_name\n", nativeQuery = true)
List<Object[]> findLogisticsInfo1db(@Param("partnerId")Long ffmId);

}
