package com.exampledelivery.deliveryinfo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bp_warehouse")
@NoArgsConstructor
@AllArgsConstructor
public class WareHouse  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long id;

    @Column(name = "warehouse_name")
    private String name;


//    @Column(name = "partner_id")
//    private Long partner_id;


}

