package com.xyz.base.utils;

import com.aventstack.extentreports.Status;
import com.xyz.base.tests.BaseTest;

public class Report extends BaseTest {

	public static void passStep(String stepDesc) {
		try {
			getLogger().log(Status.PASS, stepDesc);
		} catch (Exception e) {
			getLogger().log(Status.FAIL, e.getMessage());
		}
	}

	public static void failStep(String stepDesc) {
		try {
			getLogger().log(Status.FAIL, stepDesc);
		} catch (Exception e) {
			getLogger().log(Status.FAIL, e.getMessage());
		}
	}

	public static void infoStep(String stepDesc) {
		try {
			getLogger().log(Status.INFO, stepDesc);
		} catch (Exception e) {
			getLogger().log(Status.FAIL, e.getMessage());
		}
	}

	public static void skipStep(String stepDesc) {
		try {
			getLogger().log(Status.SKIP, stepDesc);
		} catch (Exception e) {
			getLogger().log(Status.FAIL, e.getMessage());
		}
	}

	public static void warningStep(String stepDesc) {
		try {
			getLogger().log(Status.WARNING, stepDesc);
		} catch (Exception e) {
			getLogger().log(Status.FAIL, e.getMessage());
		}
	}

}
