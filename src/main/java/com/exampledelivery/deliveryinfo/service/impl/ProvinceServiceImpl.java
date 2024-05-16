package com.exampledelivery.deliveryinfo.service.impl;


import com.exampledelivery.deliveryinfo.dto.LogisticsInfoDTO;
import com.exampledelivery.deliveryinfo.model.Partner;
import com.exampledelivery.deliveryinfo.model.WareHouse;
import com.exampledelivery.deliveryinfo.repository.ProvinceRepository;
import com.exampledelivery.deliveryinfo.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;


    @Override
    public List<LogisticsInfoDTO> findProvinceInfo() {
        List<Object[]> results  = provinceRepository.getLogisticsProvince();
        List<LogisticsInfoDTO> logisticsInfoDTOs = new ArrayList<>();
        for (Object[] result : results) {
            LogisticsInfoDTO logisticsInfoDTO = new LogisticsInfoDTO();


            Partner ffmPartner = new Partner();
            ffmPartner.setId(((Number) result[0]).longValue());
            ffmPartner.setName((String) result[1]);
            ffmPartner.setType(((Number) result[2]).intValue());
            logisticsInfoDTO.getFfmPartners().add(ffmPartner);

            Partner lmPartner = new Partner();
            lmPartner.setId(((Number) result[3]).longValue());
            lmPartner.setName((String) result[4]);
            lmPartner.setType(((Number) result[5]).intValue());
            logisticsInfoDTO.getLmPartners().add(lmPartner);

            WareHouse wareHouse = new WareHouse();
            wareHouse.setId(((Number) result[6]).longValue());
            wareHouse.setName((String) result[7]);
            logisticsInfoDTO.getWarehouses().add(wareHouse);

            logisticsInfoDTO.setId(((Number) result[8]).longValue());
            logisticsInfoDTO.setProvinceName((String) result[9]);

            logisticsInfoDTOs.add(logisticsInfoDTO);
        }

        return logisticsInfoDTOs;

    }
}
