package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_Status;
import com.api.pojo.CreateJobPayLoad;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtils;
import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest2 {

	private CreateJobPayLoad createJobPayLoad;
	private final static String COUNTRY = "India";

	@BeforeMethod(description = "Creating creat job api request payload")
	public void setUp() {

		// Create fake CreateJobAPI Request Payload
		// I Want to create a fake Customer Object!!

		Faker faker = new Faker(new Locale("en-IND"));

		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobileNumber = faker.numerify("70########");
		String altMobileNumber = faker.numerify("70########");
		String emailAddress = faker.internet().emailAddress();
		String altEmailAddress = faker.internet().emailAddress();
		Customer customer = new Customer(fname, lname, mobileNumber, altMobileNumber, emailAddress, altEmailAddress);

		System.out.println(customer);

		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pinCode = faker.numerify("#####");
		String state = faker.address().state();

		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area,
				pinCode, COUNTRY, state);

		System.out.println(customerAddress);

		// Customer product fake object creation

		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber = faker.numerify("###############");
		String popUrl = faker.internet().url();

		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
				popUrl, 1, 1);

		System.out.println(customerProduct);

		String fakeRemark = faker.lorem().sentence(5);

		// i want to generate a random number between 1 to 27
		Random random = new Random();
		int probelmId = random.nextInt((26) + 1);
		Problems problem = new Problems(probelmId, fakeRemark);

		System.out.println(problem);

		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problem);

		createJobPayLoad = new CreateJobPayLoad(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
	}

	@Test(description = "Verify if the Create job API is giving correct Inwarranty job", groups = { "api", "regression",
			"datadriven" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "CreateJobAPIDataProvider")
	public void createJobAPITest(CreateJobPayLoad createJobPayLoad) {

		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayLoad))
		        .when().post("/job/create").then()
				.spec(SpecUtils.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIRequestSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}

}
