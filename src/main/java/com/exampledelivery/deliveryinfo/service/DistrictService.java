package com.exampledelivery.deliveryinfo.service;

import com.exampledelivery.deliveryinfo.dto.LogisticsInfoDTO;

import java.util.List;

public interface DistrictService {
    List<LogisticsInfoDTO> findDistrictInfo();
}
