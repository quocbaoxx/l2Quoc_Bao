package com.exampledelivery.deliveryinfo.dto;

import com.exampledelivery.deliveryinfo.model.Partner;
import com.exampledelivery.deliveryinfo.model.WareHouse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogisticsInfoDTO {


    private Long id;
    private String provinceName;
    private String districtName;
    private String subdistrictName;
    private List<Partner> lmPartners = new ArrayList<>();
    private List<Partner> ffmPartners = new ArrayList<>();
    private List<WareHouse> warehouses = new ArrayList<>();




}
