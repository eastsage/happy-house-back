package com.happyhome.service;

import com.happyhome.mapper.ReviewMapper;
import com.happyhome.model.ReviewDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl {

    @Autowired
    ReviewMapper reviewMapper;
    public List<ReviewDto> showReview(String aptCode) {
        return reviewMapper.showReview(aptCode);
    }

    public List<ReviewDto> getReview(String aptCode){
        return reviewMapper.getReview(aptCode);
    }
}
