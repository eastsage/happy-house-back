package com.happyhome.model.apt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AptHouseDealGraphDto {
    private String dealAmount;
    private int dealYear;
    private int dealMonth;
    private String area;
    private String cancelDealType;
    private Long aptCode;
}
