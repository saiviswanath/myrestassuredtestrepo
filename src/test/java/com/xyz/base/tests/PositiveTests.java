package com.xyz.base.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.xyz.base.httprequests.RestRequests;
import com.xyz.base.payloads.Booking;
import com.xyz.base.payloads.BookingId;
import com.xyz.base.responsevalidations.ResponseValidations;
import com.xyz.base.utils.Report;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

public class PositiveTests extends BaseTest {
	@Test
	public void getBookingsTestSuccess() {
		setParametersPerTestCase("TC01", "getBookingsTestSuccess","High");
		Report.infoStep("getBookingsTestSuccess");
		RestRequests reqs = new RestRequests(spec);
		Response resp = reqs.getBookings();
		Report.infoStep(resp.toString());
		List<BookingId> bookings = resp.as(new TypeRef<List<BookingId>>() {
		});
		ResponseValidations.validateSuccessStatusCode(resp.getStatusCode());
		ResponseValidations.validateBookingIdResponse(bookings);
		Report.passStep("getBookingsTestSuccess passed");
	}
	
	@Test
	public void getBookingsObjTestSuccess() {
		setParametersPerTestCase("TC02", "getBookingsObjTestSuccess","High");
		Report.infoStep("getBookingsObjTestSuccess");
		RestRequests reqs = new RestRequests(spec);
		Response resp = reqs.getBookingsById(200);
		Booking booking = resp.as(Booking.class);
		ResponseValidations.validateSuccessStatusCode(resp.getStatusCode());
		ResponseValidations.validateBookingsResponse(booking);
		Report.passStep("getBookingsObjTestSuccess passed");
	}
}
