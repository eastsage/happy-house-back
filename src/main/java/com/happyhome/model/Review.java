package com.happyhome.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {
    private int review_no;
    private int user_no;
    private long aptcode;
    private String title;
    private String content;
    private int stars;
    private int like;
    private int hate;
    private String create_date;
}
