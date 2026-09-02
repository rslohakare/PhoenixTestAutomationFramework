package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.pojo.CreateJobPayLoad;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {

	private final static String COUNTRY = "India";

	public static void main(String[] args) {

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

		CreateJobPayLoad payload = new CreateJobPayLoad(0, 2, 1, 1, customer, customerAddress, customerProduct,
				problemList);

		System.out.println(payload);
	}

}
