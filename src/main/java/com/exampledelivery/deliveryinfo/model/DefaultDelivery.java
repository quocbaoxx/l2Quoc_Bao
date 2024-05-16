package com.exampledelivery.deliveryinfo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cf_default_delivery")
public class DefaultDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "warehouse_id")
    private Long warehouse_id;

    @Column(name = "location_id")
    private Long location_id;

    @Column(name = "lm_partner_id")
    private Long lm_partner_id;

    @Column(name = "ffm_partner_id")
    private Long ffm_partner_id;

    @Column(name = "region")
    private String region;

}
