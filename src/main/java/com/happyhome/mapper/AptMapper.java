package com.happyhome.mapper;

import com.happyhome.model.ReviewDto;
import com.happyhome.model.apt.dto.AptDealAvgDetailDto;
import com.happyhome.model.apt.dto.AptDealAvgDto;
import com.happyhome.model.apt.dto.AptHouseDealGraphDto;
import com.happyhome.model.apt.dto.HouseInfoDto;
import com.happyhome.model.apt.dto.HouseSimpleInfoDto;
import com.happyhome.model.apt.dto.SidoGugunCodeDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AptMapper {
    @Select("select hi.aptcode, hi.aptName, address, round(avg(dealAmount/area),2) price "
            + "from houseinfo hi left join sigungudongcode dc on hi.dongcode = dc.dongcode left join housedeal hd on hi.aptcode = hd.aptcode where address like #{address} group by hi.aptcode")
    @ResultType(AptDealAvgDto.class)
    List<AptDealAvgDto> showDealByRegion(String address);

//    @Select("select hi.aptcode, hi.aptName, dc.address, ss.name, round(avg(dealAmount/area),2) price " +
//            "from houseinfo hi " +
//            "left join sigungudongcode dc on hi.dongcode = dc.dongcode " +
//            "left join housedeal hd on hi.aptcode = hd.aptcode " +
//            "left join screeningstation ss on dc.sidogugun = ss.sidogugun " +
//            "where hi.aptcode = #{aptcode} group by hi.aptcode, ss.name")

    @Select("SELECT hi.aptcode, hi.aptName, dc.address, ss.name, ROUND(avg(dealAmount/area), 2) AS price "
            + "FROM houseinfo hi "
            + "LEFT JOIN sigungudongcode dc ON hi.dongcode = dc.dongcode "
            + "LEFT JOIN housedeal hd ON hi.aptcode = hd.aptcode "
            + "LEFT JOIN ( "
            + "SELECT sidogugun, MIN(name) AS name FROM screeningstation GROUP BY sidogugun ) ss "
            + "ON dc.sidogugun = ss.sidogugun "
            + "WHERE hi.aptcode = #{aptcode} "
            + "GROUP BY hi.aptcode, hi.aptName, dc.address, ss.name")
    AptDealAvgDetailDto showDetail(String aptCode);

    @Select("select dealAmount, dealYear, dealMonth, area, cancelDealType, aptCode "
            + "from housedeal "
            + "where aptCode = #{aptCode}")
    List<AptHouseDealGraphDto> getGraphData(String aptCode);

    @Select("select left(sidoCode,2) sidoCode, sidoName"
            + " from sidocode"
            + " order by sidoCode")
    List<SidoGugunCodeDto> getSido() throws Exception;

    @Select("select left(gugunCode,5) gugunCode, gugunName "
            + "from guguncode "
            + "where left(gugunCode,2) = #{sido} order by gugunCode")
    List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;

    @Select("select distinct dongName, dongCode "
            + "from houseinfo "
            + "where left(dongCode, 5) = #{gugun} "
            + "order by dongName")
    List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;

    @Select("select h.aptCode, h.aptName, h.buildyear, h.dongCode, h.dongName, h.jibun, h.lat, h.lng, si.sidoname, gu.gugunname, "
            + "(select dealAmount from housedeal where aptCode = h.aptCode and no = (select max(no) from housedeal where aptCode = h.aptCode)) recentPrice "
            + "from houseinfo h "
            + "left join sidocode si "
            + "on left(h.dongcode,2) = left(si.sidocode,2) "
            + "left join guguncode gu "
            + "on left(h.dongcode,5) = left(gu.guguncode,5) "
            + "where dongCode = #{dong}  "
            + "order by aptName")
    List<HouseInfoDto> getAptInDong(String dong) throws Exception;

    @Select("select aptName from houseinfo where houseinfo.dongCode = #{dong}")
    List<HouseInfoDto> getAptByDongCode(String dong) throws Exception;

    @Select("select hi.aptCode, hi.aptName, round(avg(hd.dealAmount),2) avgPrice, lat, lng "
            + "from houseinfo hi, housedeal hd "
            + "where dongcode = #{dongCode} and hi.aptCode = hd.aptCode "
            + "group by hd.aptCode limit 6")
    List<HouseSimpleInfoDto> getAptNameAndAvgPrice(String dongCode) throws Exception;


}
