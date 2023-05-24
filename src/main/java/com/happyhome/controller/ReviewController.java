package com.happyhome.controller;

import com.happyhome.model.ReviewDto;
import com.happyhome.service.ReviewServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/review")
@Slf4j
@Api("Apt test")
public class ReviewController {

    @Autowired
    ReviewServiceImpl reviewService;

    @GetMapping("/detailApt/review/{aptCode}")
    public ResponseEntity review(@PathVariable String aptCode) {

        List<ReviewDto> reviewList = reviewService.getReview(aptCode);
        if (reviewList == null || reviewList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(reviewList);
        }
    }
}
