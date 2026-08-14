package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtils;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;;

public class LoginAPITest {
	
	
	private UserCredentials userCreds;
	
	@BeforeMethod(description="Create the Payload for the Login API")
	public void setUp() {
		userCreds = new UserCredentials("iamfd", "password");
	}
	
	
	@Test(description="Verify if login api is working for FD user", groups= {"api", "regression", "smoke" })
	public void loginAPITest() throws IOException {

		given().spec(SpecUtils.requestSpec(userCreds))
		
		        .when().post("login")
		        .then().spec(SpecUtils.responseSpec_OK())
				.body("message", equalTo("Success"))
				.and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));

	}

}
