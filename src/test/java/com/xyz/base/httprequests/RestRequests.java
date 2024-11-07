package com.xyz.base.httprequests;

import com.xyz.base.payloads.Booking;
import com.xyz.base.utils.AuthUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
	
	public ValidatableResponse createBooking(Booking booking) {
		return RestAssured.given(spec).contentType(ContentType.JSON).body(booking).post("/booking").then().log().all();
	}
	
	public ValidatableResponse updateBooking(int id, Booking booking) {
		spec.pathParam("id", id);
		spec.header(new Header("Accept", "application/json"));
		spec.header(new Header("Cookie", "token=" + AuthUtils.getSSOJWT()));
		return RestAssured.given(spec).contentType(ContentType.JSON).body(booking).put("/booking/{id}").then().log().all();
	}

}
