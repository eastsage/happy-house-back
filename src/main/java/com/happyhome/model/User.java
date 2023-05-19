package com.happyhome.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

	private int no;
	private String id;
	private String pass;
	private String name;
	private String address;
	private String number;
	private String role;

}
