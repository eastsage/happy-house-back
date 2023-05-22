package com.happyhome.model.apt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseSimpleInfoDto {
    private String aptCode;
    private String aptName;
    private String avgPrice;
    private String lat;
    private String lng;
}
