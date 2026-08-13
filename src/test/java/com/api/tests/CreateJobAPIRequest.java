package com.api.tests;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import com.api.constant.Role;
import com.api.pojo.CreateJobPayLoad;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIRequest {

	@Test
	public void createJobAPITest() {

		Customer customer = new Customer("Rahul", "patil", "7666118566", "", "lohakare754@gmail.com", "");

		CustomerAddress customerAddress = new CustomerAddress("D 404", "Sai Palace", "Shegehalli", "Roll mall",
				"Bangaluru", "411039", "India", "KA");

		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "846833927245272",
				"846833927245272", "846833927245272", DateTimeUtil.getTimeWithDaysAgo(10), 1, 1);

		Problems problems = new Problems(1, "Battery issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);

		CreateJobPayLoad createJobPayload = new CreateJobPayLoad(0, 2, 1, 1, customer, customerAddress, customerProduct,
				problemsList);

		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then()
				.spec(SpecUtils.responseSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIRequestSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}

}
