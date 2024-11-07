package com.xyz.base.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.xyz.base.httprequests.RestRequests;
import com.xyz.base.payloads.Booking;
import com.xyz.base.payloads.BookingDates;
import com.xyz.base.utils.AuthUtils;
import com.xyz.base.utils.FrameworkProperties;
import com.xyz.base.utils.Utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
	public static final String REPORT_FOLDER_PATH = System.getProperty("user.dir") + "\\Reports\\";
	public static final String SCREENSHOT_FOLDER_PATH = REPORT_FOLDER_PATH + Utilities.dateFormat("T+0", "MM_dd_yyyy")
	+ "\\Screenshots\\";
	public static ExtentReports report;
	public static ExtentSparkReporter spark;
	
	public static ThreadLocal<ExtentTest> logger = new ThreadLocal<>();
	
	public static String reportName;
	
	public static int testCasePassed = 0, testCaseFailed = 0, testCaseExecuted = 0, testCaseSkipped = 0;
	public static HashMap<Integer, String> testCaseResult = new HashMap<Integer, String>(),
			testCaseName = new HashMap<Integer, String>(), testCaseId = new HashMap<Integer, String>();
	
	public static ThreadLocal<String> testName = new ThreadLocal<>();
	
	public static ExtentTest getLogger() {
		return logger.get();
	}
	
	public static String getTestName() {
		return testName.get();
	}
	
	public static AtomicInteger testCaseCount = new AtomicInteger(0);
	
	@BeforeSuite
	public void beforeSuite() {
		reportName = "LocalRun_";

		reportName = Utilities.dateFormat("T+0", "MM_dd_yyyy") + "\\" + reportName
				+ Utilities.dateFormat("T+0", "MM_dd_yyyy") + "_"
				+ Utilities.getTimeStamp("local").replace("-", "").replace(":", "");

		spark = new ExtentSparkReporter(REPORT_FOLDER_PATH + reportName + ".html");
		spark.config().setReportName("Automation Testing Report");
		report = new ExtentReports();
		report.attachReporter(spark);
		testCaseCount.get();
	}
	
	protected RequestSpecification spec;
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method) {
		testCaseCount.getAndIncrement();
		testName.set(method.getName());
		testCaseName.put(testCaseCount.get(), getTestName());
		System.out.println("========== Execution Started for ==========>> " + testName.get());
		
		spec = new RequestSpecBuilder().setBaseUri(FrameworkProperties.getFrameworkProperties().getProperty("base-url")).build();
		spec.header(new Header("sso_jwt", AuthUtils.getSSOJWT()));
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws InterruptedException {
		report.flush();

		String testCaseStatus = null;
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			testCasePassed++;
			testCaseStatus = "PASS";
			break;
		case ITestResult.FAILURE:
			testCaseFailed++;
			testCaseStatus = "FAIL";
			break;

		case ITestResult.SKIP:
			testCaseSkipped++;
			testCaseStatus = "SKIP";
			break;
		}
		testCaseResult.put(testCaseCount.get(), testCaseStatus);
		testCaseExecuted = testCasePassed + testCaseFailed;
	}
	
	@AfterSuite
	public void afterSuite() throws IOException {
		System.out.println(" Report Location " + REPORT_FOLDER_PATH + reportName + ".html");
			System.out.println("Test Cases Executed: " + testCaseExecuted);
			System.out.println("Test Cases Passed: " + testCasePassed);
			System.out.println("Test Cases Failed: " + testCaseFailed);
			System.out.println("Test Cases Skipped: " + testCaseSkipped);
	}
	
	public static void setParametersPerTestCase(String testcase_Id, String testDesc, String complexity) {

		String tcName = "TestCase # " + testCaseCount.get() + "---" + testcase_Id + "---" + testDesc;
		String tcDesc = tcName + "---" + complexity;
		logger.set(
				report.createTest(tcName, tcDesc).assignAuthor(System.getProperty("user.name")));
		testCaseId.put(testCaseCount.get(), testcase_Id);
		testCaseName.put(testCaseCount.get(), tcDesc);
		System.out.println(tcDesc);
	}
	
	protected ValidatableResponse createBooking() {
		RestRequests reqs = new RestRequests(spec);
		Booking booking = new Booking();
		booking.setFirstname("Sai");booking.setLastname("Palaparthi");
		booking.setTotalprice(100);booking.setDepositpaid(true);
		BookingDates dates = new BookingDates();
		dates.setCheckin("2024-11-05");dates.setCheckout("2024-11-06");
		booking.setBookingdates(dates);
		booking.setAdditionalneeds("Breakfast");
		return reqs.createBooking(booking);
	}
}
