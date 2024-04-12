package com.xyz.base.httprequests;

import io.restassured.specification.RequestSpecification;

public class BaseRequest {
	protected RequestSpecification spec;

	public BaseRequest(RequestSpecification spec) {
		super();
		this.spec = spec;
	}
	
}
