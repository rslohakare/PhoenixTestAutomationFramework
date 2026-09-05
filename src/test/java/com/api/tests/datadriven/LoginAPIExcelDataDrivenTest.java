package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;;

public class LoginAPIExcelDataDrivenTest {

	@Test(description = "Verify if login api is working for FD user", groups = { "api", "regression",
			"datadriven" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, 
			dataProvider = "LoginAPIExcelDataProvider")
	public void loginAPITest(UserCredentials userCredentials ) {

		given().spec(SpecUtils.requestSpec(userCredentials))

				.when().post("login").then().spec(SpecUtils.responseSpec_OK()).body("message", equalTo("Success")).and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));

	}

}
