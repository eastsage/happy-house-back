package com.happyhome.configuration.jwt;

public interface JwtProperties {
	String SECRET = "UmJunSik_Is_Human'sName?"; // 우리 서버만 알고 있는 비밀값
	int EXPIRATION_TIME = 864000000; // 10일 (1/1000초)
//	String TOKEN_PREFIX = "Bearer ";
	String TOKEN_PREFIX = "";
	String HEADER_STRING = "access-token";
}
