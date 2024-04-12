package com.xyz.base.httprequests;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestRequests extends BaseRequest {

	public RestRequests(RequestSpecification spec) {
		super(spec);
	}
	
	public Response getBookings() {
		return RestAssured.given(spec).get("/booking");
	}
	
	public Response getBookingsById(int id) {
		spec.pathParam("id", id);
		spec.header(new Header("Accept", "application/json"));
		return RestAssured.given(spec).get("/booking/{id}");
	}

}
