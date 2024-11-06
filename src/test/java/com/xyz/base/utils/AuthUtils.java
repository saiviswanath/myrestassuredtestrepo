package com.xyz.base.utils;

import com.xyz.base.payloads.AuthResponse;
import com.xyz.base.payloads.Credentials;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthUtils {
	public static String getSSOJWT() {
		Credentials creds = new Credentials(FrameworkProperties.getFrameworkProperties().getProperty("auth-uname"),
				FrameworkProperties.getFrameworkProperties().getProperty("auth-pwd"));
		Response resp = RestAssured.given().contentType(ContentType.JSON).body(creds).post(
				FrameworkProperties.getFrameworkProperties().getProperty("auth-url"));
		AuthResponse authResp = resp.as(AuthResponse.class);
		return authResp.getToken();
	}
}
