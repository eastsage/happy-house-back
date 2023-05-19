package com.happyhome.service;

import com.happyhome.mapper.ReviewMapper;
import com.happyhome.model.ReviewDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    ReviewMapper rmapper;
    public List<ReviewDto> showReview(String aptCode) {
        return rmapper.showReview(aptCode);
    }
}
