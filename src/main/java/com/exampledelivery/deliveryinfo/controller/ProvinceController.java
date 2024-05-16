package com.exampledelivery.deliveryinfo.controller;


import com.exampledelivery.deliveryinfo.dto.LogisticsInfoDTO;
import com.exampledelivery.deliveryinfo.exception.DataResponse;
import com.exampledelivery.deliveryinfo.exception.ErrorMessages;
import com.exampledelivery.deliveryinfo.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/province")
public class ProvinceController {

     @Autowired
     private ProvinceService provinceService;
     @Value("${location-type}")
     private String  lever;



     @GetMapping("/all")
     public DataResponse<List<LogisticsInfoDTO>> getProvinceLogistics(@RequestParam("lever") String leverParam) {
          if (lever.equals(leverParam)) {
               List<LogisticsInfoDTO> logisticsInfoDTOS = provinceService.findProvinceInfo();
               return DataResponse.ok(logisticsInfoDTOS);
          } else {
               return DataResponse.build(null,ErrorMessages.FORBIDDEN);
          }
     }

}
