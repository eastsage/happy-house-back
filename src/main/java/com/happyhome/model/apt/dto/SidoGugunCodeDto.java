package com.happyhome.model.apt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SidoGugunCodeDto {

	private String sidoCode;
	private String sidoName;
	private String gugunCode;
	private String gugunName;

}
