package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayLoad;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtils;

public class CreateJobAPIRequest {

	@Test
	public void createJobAPITest() {

		Customer customer = new Customer("Rahul", "patil", "7666118566", "", "lohakare754@gmail.com", "");

		CustomerAddress customerAddress = new CustomerAddress("D 404", "Sai Palace", "Shegehalli", "Roll mall",
				"Bangaluru", "411039", "India", "KA");

		CustomerProduct customerProduct = new CustomerProduct("2026-06-01T00:00:00.000Z", "546833927245271",
				"146833927245271", "146833927245271", "2026-06-01T00:00:00.000Z", 1, 1);

		Problems problems = new Problems(1, "Battery issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0] = problems;

		CreateJobPayLoad createJobPayload = new CreateJobPayLoad(0, 2, 1, 1, customer, customerAddress, customerProduct,
				problemsArray);

		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then()
				.spec(SpecUtils.responseSpec_OK());

	}

}
