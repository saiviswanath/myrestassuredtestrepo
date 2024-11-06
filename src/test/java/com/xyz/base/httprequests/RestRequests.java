package com.xyz.base.httprequests;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class RestRequests extends BaseRequest {

	public RestRequests(RequestSpecification spec) {
		super(spec);
	}
	
	public ValidatableResponse getBookings() {
		return RestAssured.given(spec).get("/booking").then().log().all();
	}
	
	public ValidatableResponse getBookingsById(int id) {
		spec.pathParam("id", id);
		spec.header(new Header("Accept", "application/json"));
		return RestAssured.given(spec).get("/booking/{id}").then().log().all();
	}

}
