package com.happyhome.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewDto {
    String id;
    String aptCode;
    String apartmentName;
    String title;
    String content;
    String stars;
    String likes;
    String hates;
    String created_date;

}
