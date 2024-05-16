package com.exampledelivery.deliveryinfo.controller;


import com.exampledelivery.deliveryinfo.dto.FfmLogisticsInfoDTO;
import com.exampledelivery.deliveryinfo.dto.LogisticsInfoDTO;
import com.exampledelivery.deliveryinfo.dto.UpdateDeliveryDTO;
import com.exampledelivery.deliveryinfo.exception.DataResponse;
import com.exampledelivery.deliveryinfo.exception.ErrorMessages;
import com.exampledelivery.deliveryinfo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery")
public class DeliveryController {

    @Value("${location-type}")
    private String  lever;

    @Autowired
    private DeliveryService deliveryService ;

@GetMapping("/logistics/{ffmId}")
public DataResponse<FfmLogisticsInfoDTO> getLogistics(@PathVariable Long ffmId) {

        DataResponse<FfmLogisticsInfoDTO> response = deliveryService.findLogistics1db(ffmId);
        return  response;
}

@GetMapping("/lever/{leverMapping}")
    public DataResponse<List<LogisticsInfoDTO>> getlogitictLever(@PathVariable String leverMapping){
    if (lever.equals(leverMapping)){
        List<LogisticsInfoDTO> logisticsInfoDTOS = deliveryService.getleverGogistics(leverMapping);
        return DataResponse.ok(logisticsInfoDTOS);
    }else {
        return  DataResponse.build(null,ErrorMessages.FORBIDDEN);
    }

}

@PutMapping("/update")
    public DataResponse<UpdateDeliveryDTO> updateDelivery(@RequestBody UpdateDeliveryDTO updateDelivery){
           UpdateDeliveryDTO updateDeliveryDTO =deliveryService.updatedilivery(lever, updateDelivery);
           return  DataResponse.ok(updateDeliveryDTO);
}






}
