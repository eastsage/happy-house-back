package com.happyhome.model.apt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseInfoDto {

    private int aptCode;
    private String aptName;
    private String dongCode;
    private String dongName;
    private String sidoName;
    private String gugunName;
    private int buildYear;
    private String jibun;
    private String lat;
    private String lng;
    private String img;
    private String recentPrice;

}
