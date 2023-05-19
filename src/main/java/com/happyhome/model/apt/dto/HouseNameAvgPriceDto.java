package com.happyhome.model.apt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseNameAvgPriceDto {
    private String aptName;
    private String avgPrice;
}
