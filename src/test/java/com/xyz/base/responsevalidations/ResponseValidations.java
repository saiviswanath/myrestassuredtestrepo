package com.xyz.base.responsevalidations;

import java.util.List;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.xyz.base.payloads.Booking;
import com.xyz.base.payloads.BookingId;
import com.xyz.base.payloads.BookingResponse;

public class ResponseValidations {
	
	public static void validateSuccessStatusCode(int statusCode) {
		Assert.assertEquals(statusCode, 200);
	}
	
	public static void validateBookingIdResponse(List<BookingId> bookingIds) {
		SoftAssert asset = new SoftAssert();
		asset.assertNotNull(bookingIds);
		asset.assertTrue(bookingIds.size() > 0);
		
		asset.assertAll();
	}

	public static void validateBookingsResponse(Booking booking) {
		SoftAssert asset = new SoftAssert();
		asset.assertNotNull(booking);
		asset.assertEquals(booking.getFirstname(), "Sai");
		asset.assertEquals(booking.getLastname(), "Palaparthi");
		asset.assertEquals(booking.getTotalprice(), 100);
		asset.assertTrue(booking.isDepositpaid());
		asset.assertEquals(booking.getAdditionalneeds(), "Breakfast");
		asset.assertEquals(booking.getBookingdates().getCheckin(), "2024-11-05");
		asset.assertEquals(booking.getBookingdates().getCheckout(), "2024-11-06");
		asset.assertAll();
	}
	
	public static void validateBookingsResponse(BookingResponse resp) {
		SoftAssert asset = new SoftAssert();
		asset.assertNotNull(resp);
		asset.assertAll();	
	}
	
	public static void validateUpdateBookingsResponse(Booking booking) {
		SoftAssert asset = new SoftAssert();
		asset.assertNotNull(booking);
		asset.assertEquals(booking.getFirstname(), "Viswanath");	
		asset.assertAll();	
	}
	
	
}
