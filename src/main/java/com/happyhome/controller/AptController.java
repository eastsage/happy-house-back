package com.happyhome.controller;

import com.happyhome.model.ReviewDto;
import com.happyhome.model.apt.dto.AptDealAvgDetailDto;
import com.happyhome.model.apt.dto.AptDealAvgDto;
import com.happyhome.model.apt.dto.AptHouseDealGraphDto;
import com.happyhome.model.apt.dto.HouseInfoDto;
import com.happyhome.model.apt.dto.HouseSimpleInfoDto;
import com.happyhome.model.apt.dto.SidoGugunCodeDto;
import com.happyhome.service.HouseService;
import com.happyhome.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
@RequestMapping("/map")
@Slf4j
@Api("Apt test")
public class AptController {

    @Autowired
    HouseService houseService;

    @Autowired
    ReviewService reviewService;

    @ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
    @GetMapping("/sido")
    public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
        return new ResponseEntity<List<SidoGugunCodeDto>>(houseService.getSido(), HttpStatus.OK);
    }

    @ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
    @GetMapping("/gugun")
    public ResponseEntity<List<SidoGugunCodeDto>> gugun(
            @RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
        return new ResponseEntity<List<SidoGugunCodeDto>>(houseService.getGugunInSido(sido), HttpStatus.OK);
    }

    @GetMapping("/house-list")
    public ResponseEntity<List<HouseSimpleInfoDto>> houseList(@RequestParam(required = false) String dong)
            throws Exception {
        log.info("welcome controller");
        List<HouseSimpleInfoDto> houseSimpleInfoDatas = houseService.getAptNameAndAvgPrice(dong);
        log.info("size = {}", houseSimpleInfoDatas.size());
        for (HouseSimpleInfoDto data : houseSimpleInfoDatas) {
            log.info("aptName = {}", data.getAptName());
            log.info("avgPrice = {}", data.getAvgPrice());
        }
        return ResponseEntity.ok().body(houseSimpleInfoDatas);
    }

    @GetMapping("/dong")
    public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
        return new ResponseEntity<List<HouseInfoDto>>(houseService.getDongInGugun(gugun), HttpStatus.OK);
    }

    @GetMapping("/apt")
    public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
        return new ResponseEntity<List<HouseInfoDto>>(houseService.getAptInDong(dong), HttpStatus.OK);
    }

    @GetMapping("/")
    @ApiOperation(notes = "서버 연결 상태를 체크합니다.", value = "hi")
    public ResponseEntity<?> home() {
        return ResponseEntity.ok().body("hi");
    }

    @GetMapping("/list")
    @ApiOperation(notes = "시도/구군/동에 해당하는 아파트 정보를 조회합니다.", value = "Apt information")
    public List<AptDealAvgDto> apt(@RequestParam("sido") String sido,
                                   @RequestParam(value = "gugun", required = false) String gugun,
                                   @RequestParam(value = "dong", required = false) String dong) {
        StringBuilder sb = new StringBuilder();
        if (sido != null) {
            sb.append(sido);
            if (gugun != null) {
                sb.append(" ").append(gugun);
                if (dong != null) {
                    sb.append(" ").append(dong);
                }
            }
        }
        sb.append("%");
        System.out.println(sb.toString());
        List<AptDealAvgDto> aptDealAvgDtos = houseService.showDealByRegion(sb.toString());
        return aptDealAvgDtos;
    }

    @GetMapping("/detailApt/{aptCode}")
    @ApiOperation(notes = "해당 아파트에서 가장 가까운 선별진료소와 해당 동의 평당 시세를 보여줍니다.", value = "Apt Detail")
    public AptDealAvgDetailDto showDetail(@PathVariable String aptCode) {
        return houseService.showDetail(aptCode);
    }

    @GetMapping("/detailApt/graph/{aptCode}")
    public ResponseEntity graph(@PathVariable String aptCode) {

        List<AptHouseDealGraphDto> graphDataList = houseService.getGraphData(aptCode);
        if (graphDataList == null || graphDataList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(graphDataList);
        }
    }


    @GetMapping("/detailReview")
    @ApiOperation(notes = "해당 아파트의 리뷰를 보여줍니다.", value = "Apt Review")
    public List<ReviewDto> showReview(@RequestParam("aptCode") String aptCode) {
        List<ReviewDto> list = reviewService.showReview(aptCode);
        return list;
    }


}
