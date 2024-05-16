package com.exampledelivery.deliveryinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateDeliveryDTO {

    private Long locationId;
    private Long whId;
    private String warehouseName;
    private Long partnerId;
    private String partnerName;

}
