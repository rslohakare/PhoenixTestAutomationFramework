
package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayLoad;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIDataDrivenTest {

	@Test(description = "Verify if the Create job API is giving correct Inwarranty job", groups = { "api", "regression",
			"datadriven" },dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "CreateJobAPIDataProvider")
	public void createJobAPITest(CreateJobPayLoad createJobPayLoad) {

		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayLoad)).when().post("/job/create").then()
				.spec(SpecUtils.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIRequestSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}

}
