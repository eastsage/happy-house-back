package com.happyhome.mapper;

import com.happyhome.model.Review;
import com.happyhome.model.ReviewDto;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReviewMapper {
//    insert into user values ('ssafy','umjunsik','ujs@naver.com','ssafy');
//
//    insert into review values (1,'ssafy','11110000000001','집이 좁아요','코딱지만함',1,50,0,'2023.05.02');
//
//    select user_id, rv.aptcode, apartmentname, title, content, stars, likes, hates, created_date
//    from review rv, houseinfo hi
//    where rv.aptcode = hi.aptcode and rv.aptcode = '11110000000001';

    @Select("select id, rv.aptcode, apartmentname, title, content, stars, likes, hates, created_date from review rv, houseinfo hi, user us where rv.aptcode = hi.aptcode and rv.aptcode = #{aptCode} and us.no = rv.user_no")
    List<ReviewDto> showReview(String aptCode);

    @Insert("insert into review "
            + "values (#{review_no}, #{user_no}, #{aptcode}, #{title}, #{content}, "
            + "#{stars}, #{likes}, #{hates}, #{created_date})")
    int addReview(Review review);

    @Select("select user_id, title, content, stars, likes, hates, created_date from review "
            + " where aptCode = #{aptCode} ")
    List<ReviewDto> getReview(String aptCode);

}
