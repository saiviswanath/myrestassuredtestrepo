package com.xyz.base.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.xyz.base.httprequests.RestRequests;
import com.xyz.base.payloads.Booking;
import com.xyz.base.payloads.BookingId;
import com.xyz.base.responsevalidations.ResponseValidations;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

public class PositiveTests extends BaseTest {
	@Test
	public void getBookingsTestSuccess() {
		RestRequests reqs = new RestRequests(spec);
		Response resp = reqs.getBookings();
		System.out.println("Test1");
		List<BookingId> bookings = resp.as(new TypeRef<List<BookingId>>() {
		});
		ResponseValidations.validateSuccessStatusCode(resp.getStatusCode());
		ResponseValidations.validateBookingIdResponse(bookings);
		
	}
	
	@Test
	public void getBookingsObjTestSuccess() {
		RestRequests reqs = new RestRequests(spec);
		Response resp = reqs.getBookingsById(200);
		Booking booking = resp.as(Booking.class);
		ResponseValidations.validateSuccessStatusCode(resp.getStatusCode());
		ResponseValidations.validateBookingsResponse(booking);
	}
}
