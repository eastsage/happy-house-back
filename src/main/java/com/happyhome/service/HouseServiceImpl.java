package com.happyhome.service;

import com.happyhome.mapper.AptMapper;
import com.happyhome.mapper.ReviewMapper;
import com.happyhome.model.ReviewDto;
import com.happyhome.model.apt.dto.AptDealAvgDetailDto;
import com.happyhome.model.apt.dto.AptDealAvgDto;
import com.happyhome.model.apt.dto.AptHouseDealGraphDto;
import com.happyhome.model.apt.dto.HouseInfoDto;
import com.happyhome.model.apt.dto.HouseSimpleInfoDto;
import com.happyhome.model.apt.dto.SidoGugunCodeDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    AptMapper aptmapper;

    @Autowired
    ReviewMapper reviewMapper;


    @Override
    public List<AptDealAvgDto> showDealByRegion(String address) {
        return aptmapper.showDealByRegion(address);
    }

    @Override
    public AptDealAvgDetailDto showDetail(String aptCode) {
        return aptmapper.showDetail(aptCode);
    }

    @Override
    public List<AptHouseDealGraphDto> getGraphData(String aptCode) {
        return aptmapper.getGraphData(aptCode);
    }

    @Override
    public List<SidoGugunCodeDto> getSido() throws Exception {
        return aptmapper.getSido();
    }

    @Override
    public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
        return aptmapper.getGugunInSido(sido);
    }

    @Override
    public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
        return aptmapper.getDongInGugun(gugun);
    }

    @Override
    public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
        return aptmapper.getAptInDong(dong);
    }

    @Override
    public List<HouseInfoDto> getAptByDongCode(String dong) throws Exception {
        return aptmapper.getAptByDongCode(dong);
    }

    @Override
    public List<HouseSimpleInfoDto> getAptNameAndAvgPrice(String dongCode) throws Exception {
        return aptmapper.getAptNameAndAvgPrice(dongCode);
    }

    @Override
    public List<ReviewDto> showReview(String aptCode) {
        return reviewMapper.showReview(aptCode);
    }
}
