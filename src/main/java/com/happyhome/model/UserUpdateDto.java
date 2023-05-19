package com.happyhome.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateDto {
    private String id;
    private String name;
    private String address;
    private String number;
}
