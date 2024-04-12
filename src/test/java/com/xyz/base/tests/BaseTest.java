package com.xyz.base.tests;

import org.testng.annotations.BeforeMethod;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
	protected RequestSpecification spec;
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
	}
}
