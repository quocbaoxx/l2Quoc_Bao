package com.exampledelivery.deliveryinfo.service.impl;

import com.exampledelivery.deliveryinfo.dto.LogisticsInfoDTO;
import com.exampledelivery.deliveryinfo.model.Partner;
import com.exampledelivery.deliveryinfo.model.WareHouse;
import com.exampledelivery.deliveryinfo.repository.SubdistrictRepository;
import com.exampledelivery.deliveryinfo.service.SubdistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubdistrictServiceImpl implements SubdistrictService {

    @Autowired
    private SubdistrictRepository subdistrictRepository;

    @Override
    public List<LogisticsInfoDTO> findSubdistrictInfo() {
        List<Object[]> results = subdistrictRepository.getLogisticSubdistrict();

        List<LogisticsInfoDTO> logisticsInfoDTOs = new ArrayList<>();
        for (Object[] result : results) {
            LogisticsInfoDTO logisticsInfoDTO = new LogisticsInfoDTO();


            Partner ffm =  new Partner();
            ffm.setId(((Number) result[0]).longValue());
            ffm.setName((String) result[1]);
            ffm.setType(((Number) result[2]).intValue());
            logisticsInfoDTO.getFfmPartners().add(ffm);

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
            logisticsInfoDTO.setDistrictName((String) result[10]);
            logisticsInfoDTO.setSubdistrictName((String)  result[11]);

            logisticsInfoDTOs.add(logisticsInfoDTO);
        }


        return logisticsInfoDTOs;
    }
}