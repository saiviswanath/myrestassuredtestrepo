package com.xyz.base.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.xyz.base.httprequests.RestRequests;
import com.xyz.base.payloads.Booking;
import com.xyz.base.payloads.BookingDates;
import com.xyz.base.payloads.BookingId;
import com.xyz.base.payloads.BookingResponse;
import com.xyz.base.responsevalidations.ResponseValidations;
import com.xyz.base.utils.Report;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ValidatableResponse;

public class PositiveTests extends BaseTest {
	@Test
	public void getBookingsTestSuccess() {
		setParametersPerTestCase("TC01", "getBookingsTestSuccess","High");
		Report.infoStep("getBookingsTestSuccess");
		RestRequests reqs = new RestRequests(spec);
		ValidatableResponse resp = reqs.getBookings();
		Report.infoStep(resp.toString());
		List<BookingId> bookings = resp.extract().body().as(new TypeRef<List<BookingId>>() {
		});
		ResponseValidations.validateSuccessStatusCode(resp.extract().statusCode());
		ResponseValidations.validateBookingIdResponse(bookings);
		Report.passStep("getBookingsTestSuccess passed");
	}
	
	@Test
	public void getBookingsObjTestSuccess() {
		setParametersPerTestCase("TC02", "getBookingsObjTestSuccess","High");
		Report.infoStep("getBookingsObjTestSuccess");
		RestRequests reqs = new RestRequests(spec);
		ValidatableResponse vresp  = createBooking();
		ValidatableResponse resp = reqs.getBookingsById(vresp.extract().body().as(BookingResponse.class).getBookingid());
		Booking booking = resp.extract().body().as(Booking.class);
		ResponseValidations.validateSuccessStatusCode(resp.extract().statusCode());
		ResponseValidations.validateBookingsResponse(booking);
		Report.passStep("getBookingsObjTestSuccess passed");
	}
	
	@Test
	public void createBookingTestSuccess() {
		setParametersPerTestCase("TC03", "createBookingTestSuccess","High");
		Report.infoStep("createBookingTestSuccess");
		ValidatableResponse resp  = createBooking();
		BookingResponse response = resp.extract().body().as(BookingResponse.class);
		ResponseValidations.validateSuccessStatusCode(resp.extract().statusCode());
		ResponseValidations.validateBookingsResponse(response);
		Report.passStep("BookingId: " + response.getBookingid());
	}
	
	@Test
	public void updateBookingTestSuccess() {
		setParametersPerTestCase("TC04", "updateBookingTestSuccess","High");
		Report.infoStep("updateBookingTestSuccess");
		ValidatableResponse resp  = createBooking();
		BookingResponse response = resp.extract().body().as(BookingResponse.class);
		ResponseValidations.validateSuccessStatusCode(resp.extract().statusCode());
		ResponseValidations.validateBookingsResponse(response);
		
		RestRequests reqs = new RestRequests(spec);
		Booking booking = new Booking();
		booking.setFirstname("Viswanath");booking.setLastname("Palaparthi");
		booking.setTotalprice(100);booking.setDepositpaid(true);
		BookingDates dates = new BookingDates();
		dates.setCheckin("2024-11-05");dates.setCheckout("2024-11-06");
		booking.setBookingdates(dates);
		booking.setAdditionalneeds("Breakfast");
		ValidatableResponse updateResp = reqs.updateBooking(response.getBookingid(), booking);
		Booking upBookResp = updateResp.extract().body().as(Booking.class);
		ResponseValidations.validateSuccessStatusCode(updateResp.extract().statusCode());
		ResponseValidations.validateUpdateBookingsResponse(upBookResp);
	}
	
	
}
