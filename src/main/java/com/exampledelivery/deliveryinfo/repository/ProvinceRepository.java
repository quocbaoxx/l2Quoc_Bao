package com.exampledelivery.deliveryinfo.repository;

import com.exampledelivery.deliveryinfo.dto.LogisticsInfoDTO;
import com.exampledelivery.deliveryinfo.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {


    @Query(value = "SELECT \n" +
            "    ffm.partner_id AS ffm_partner_id, \n" +
            "    ffm.partner_name AS ffm_partner_name, \n" +
            "\tffm.pn_type AS ffm_pn_type,\n" +
            "    lm.partner_id AS lm_partner_id, \n" +
            "    lm.partner_name AS lm_partner_name, \n" +
            "\tlm.pn_type AS lm_pn_type,\n" +
            "    wh.warehouse_id AS warehouse_id, \n" +
            "    wh.warehouse_name AS warehouse_name,\n" +
            "\t prov.province_id AS province_id, \n" +
            "    prov.province_name AS province_name\n" +
            "FROM \n" +
            "    lc_province prov \n" +
            "JOIN \n" +
            "    cf_default_delivery cf ON prov.province_id = cf.location_id \n" +
            "JOIN \n" +
            "    bp_partner ffm ON cf.ffm_partner_id = ffm.partner_id \n" +
            "JOIN \n" +
            "    bp_partner lm ON cf.lm_partner_id = lm.partner_id \n" +
            "JOIN \n" +
            "    bp_warehouse wh ON cf.warehouse_id = wh.warehouse_id", nativeQuery = true)
    List<Object[]> getLogisticsProvince();

//    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM lc_province WHERE province_id = :provinceId", nativeQuery = true)
//    boolean existsById(@Param("provinceId") Long provinceId);
}
