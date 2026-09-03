package com.api.tests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayLoad;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITestWithFakeData {

	private CreateJobPayLoad createJobPayLoad;
	private final static String COUNTRY = "India";

	@BeforeMethod(description = "Creating creat job api request payload")
	public void setUp() {

		createJobPayLoad = FakerDataGenerator.generateFakeCreateJobData();
	}

	@Test(description = "Verify if the Create job API is giving correct Inwarranty job", groups = { "api", "regression",
			"datadriven" })
	public void createJobAPITest() {

		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayLoad)).when().post("/job/create").then()
				.spec(SpecUtils.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIRequestSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}

}
