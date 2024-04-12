package com.xyz.base.responsevalidations;

import java.util.List;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.xyz.base.payloads.Booking;
import com.xyz.base.payloads.BookingId;

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
		asset.assertEquals(booking.getFirstname(), "John");
		asset.assertEquals(booking.getLastname(), "Smith");
		asset.assertEquals(booking.getTotalprice(), 111);
		asset.assertTrue(booking.isDepositpaid());
		asset.assertEquals(booking.getAdditionalneeds(), "Breakfast");
		asset.assertEquals(booking.getBookingdates().getCheckin(), "2018-01-01");
		asset.assertEquals(booking.getBookingdates().getCheckout(), "2019-01-01");
		asset.assertAll();
	}
	
	
}
