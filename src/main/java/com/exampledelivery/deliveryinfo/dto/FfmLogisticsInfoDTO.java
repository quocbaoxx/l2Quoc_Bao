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
public class FfmLogisticsInfoDTO {

    private  Long id;
    private  String  ffmName;
    private List<Partner> partnerlm = new ArrayList<>();
    private  List<WareHouse> wareHouses = new ArrayList<>();

}
