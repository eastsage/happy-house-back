package com.happyhome.model.apt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AptDealAvgDetailDto {
    private long aptCode;
    private String aptName;
    private String address;
    private String name;
    private double price;
}
