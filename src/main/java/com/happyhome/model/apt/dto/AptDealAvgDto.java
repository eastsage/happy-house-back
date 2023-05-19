package com.happyhome.model.apt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AptDealAvgDto {
//    code, apartmentName, address, round(avg(dealAmount/area),2) price
    private long aptCode;
    private String apartmentName;
    private String address;
    private double price;
}
