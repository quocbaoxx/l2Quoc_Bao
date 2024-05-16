package com.exampledelivery.deliveryinfo.service;

import com.exampledelivery.deliveryinfo.dto.FfmLogisticsInfoDTO;
import com.exampledelivery.deliveryinfo.dto.LogisticsInfoDTO;
import com.exampledelivery.deliveryinfo.dto.UpdateDeliveryDTO;
import com.exampledelivery.deliveryinfo.exception.DataResponse;

import java.util.List;

public interface DeliveryService {
    DataResponse<FfmLogisticsInfoDTO> findLogistics1db(Long ffmId);

    List<LogisticsInfoDTO> getleverGogistics(String  leverMapping);


    UpdateDeliveryDTO updatedilivery(String leverMapping, UpdateDeliveryDTO updateDelivery);
}
