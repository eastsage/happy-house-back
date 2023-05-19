package com.happyhome.service;

import com.happyhome.model.ReviewDto;
import com.happyhome.model.apt.dto.AptDealAvgDetailDto;
import com.happyhome.model.apt.dto.AptDealAvgDto;
import com.happyhome.model.apt.dto.AptHouseDealGraphDto;
import com.happyhome.model.apt.dto.HouseInfoDto;
import com.happyhome.model.apt.dto.HouseNameAvgPriceDto;
import com.happyhome.model.apt.dto.SidoGugunCodeDto;
import java.util.List;

public interface HouseService {


    List<AptDealAvgDto> showDealByRegion(String address);

    AptDealAvgDetailDto showDetail(String aptCode);

    List<ReviewDto> showReview(String aptCode);

    List<AptHouseDealGraphDto> getGraphData(String aptCode);

    List<SidoGugunCodeDto> getSido() throws Exception;

    List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;

    List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;

    List<HouseInfoDto> getAptInDong(String dong) throws Exception;

    List<HouseInfoDto> getAptByDongCode(String dong) throws Exception;

    List<HouseNameAvgPriceDto> getAptNameAndAvgPrice(String dongCode) throws Exception;
}
