package com.exampledelivery.deliveryinfo.service.impl;

import com.exampledelivery.deliveryinfo.dto.FfmLogisticsInfoDTO;
import com.exampledelivery.deliveryinfo.dto.LogisticsInfoDTO;
import com.exampledelivery.deliveryinfo.dto.UpdateDeliveryDTO;
import com.exampledelivery.deliveryinfo.exception.DataResponse;
import com.exampledelivery.deliveryinfo.exception.ErrorMessages;
import com.exampledelivery.deliveryinfo.export.ExcelExporter;
import com.exampledelivery.deliveryinfo.model.DefaultDelivery;
import com.exampledelivery.deliveryinfo.model.Partner;
import com.exampledelivery.deliveryinfo.model.WareHouse;
import com.exampledelivery.deliveryinfo.repository.*;
import com.exampledelivery.deliveryinfo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ExcelExporter excelExporter;

    @Autowired
    private SubdistrictRepository subdistrictRepository;
    @Autowired
    private DefaultDeliveryRepository defaultDeliveryRepository;
    @Autowired
    private  WareHouseRepository wareHouseRepository;



    @Override
    public UpdateDeliveryDTO updatedilivery(String leverMapping, UpdateDeliveryDTO updateDelivery) {

        Long locationIdDTO = updateDelivery.getLocationId();
        int locationLv ;
        if (provinceRepository.existsById(locationIdDTO)){
            locationLv = 1;
        }else  if (districtRepository.existsById(locationIdDTO)){
            locationLv = 2;
        }else if (subdistrictRepository.existsById(locationIdDTO)){
            locationLv = 3;
        }else {
            throw  new IllegalArgumentException(ErrorMessages.INVALID_VALUE.getMessage());
        }


        if (locationLv >= Integer.parseInt(leverMapping)){
            DefaultDelivery locationId = defaultDeliveryRepository.findByLocationId(updateDelivery.getLocationId());
            if (locationId == null){
                throw  new IllegalArgumentException(ErrorMessages.INVALID_VALUE.getMessage());
            }
            WareHouse wareHouse = new WareHouse();
            wareHouse.setId(updateDelivery.getWhId());
            wareHouse.setName(updateDelivery.getWarehouseName());
            wareHouseRepository.save(wareHouse);

            Partner partner = new Partner();
            partner.setId(updateDelivery.getPartnerId());
            partner.setName(updateDelivery.getPartnerName());
            partnerRepository.save(partner);

            return updateDelivery;

        } else {
            throw new IllegalArgumentException(ErrorMessages.FORBIDDEN.getMessage());}


    }


    @Override
    public List<LogisticsInfoDTO> getleverGogistics(String  leverMapping) {

        List<Object[]> queryResults;
        switch (leverMapping) {
            case "1":
                queryResults = provinceRepository.getLogisticsProvince();
                break;
            case "2":
                queryResults = districtRepository.getLogisticsSubDistrict();
                break;
            case "3":
                queryResults = subdistrictRepository.getLogisticSubdistrict();
                break;
            default:
                throw new IllegalArgumentException(ErrorMessages.INVALID_VALUE.getMessage());
        }

        List<LogisticsInfoDTO> logisticsInfoList = mapQueryResultsToDTO(queryResults);

        String outputPath = "D:/export/output.xlsx";
        try {
            excelExporter.exportToExcel(logisticsInfoList, outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logisticsInfoList;

    }



    private List<LogisticsInfoDTO> mapQueryResultsToDTO(List<Object[]> queryResults) {

        List<LogisticsInfoDTO> logisticsInfoList = new ArrayList<>();

        for (Object[] result : queryResults) {
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
            if (result.length>10) {
                logisticsInfoDTO.setDistrictName((String) result[10]);
            }
            if (result.length>11) {
                logisticsInfoDTO.setSubdistrictName((String) result[11]);
            }
            logisticsInfoList.add(logisticsInfoDTO);

        }
        return  logisticsInfoList;



    }


    @Override
    public DataResponse<FfmLogisticsInfoDTO> findLogistics1db(Long ffmId) {
        List<Object[]> results = partnerRepository.findLogisticsInfo1db(ffmId);


        if (results.isEmpty()) {
          return DataResponse.build(null, ErrorMessages.NOT_FOUND);
        }

        Object[] result = results.get(0);

        FfmLogisticsInfoDTO ffmLogisticsInfoDTO = new FfmLogisticsInfoDTO();
        ffmLogisticsInfoDTO.setId(((Number) result[0]).longValue());
        ffmLogisticsInfoDTO.setFfmName((String) result[1]);

        List<Partner> partnerLms = buildPartners((String) result[4], (String) result[6], (String) result[5]);
        ffmLogisticsInfoDTO.setPartnerlm(partnerLms);

        List<WareHouse> wareHouses = buildWareHouses((String) result[2], (String) result[3]);
        ffmLogisticsInfoDTO.setWareHouses(wareHouses);

        return DataResponse.ok(ffmLogisticsInfoDTO);
    }


    private List<Partner> buildPartners(String idsString, String namesString, String type) {
        String[] ids = idsString.split(",");
        String[] names = namesString.split(",");
        String[] types = type.split(",");
        List<Partner> partners = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {
            Partner partner = new Partner();
            partner.setId(Long.parseLong(ids[i]));
            partner.setName(names[i]);
            partner.setType(Integer.parseInt(types[i]));
            partners.add(partner);
        }

        return partners;
    }

    private List<WareHouse> buildWareHouses(String idsString, String namesString) {
        String[] ids = idsString.split(",");
        String[] names = namesString.split(",");
        List<WareHouse> warehouses = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {
            WareHouse warehouse = new WareHouse();
            warehouse.setId(Long.parseLong(ids[i]));
            warehouse.setName(names[i]);
            warehouses.add(warehouse);
        }

        return warehouses;
    }
}
