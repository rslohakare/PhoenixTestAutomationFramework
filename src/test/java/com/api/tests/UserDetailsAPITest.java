package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	@Test(description="Verify if the Userdetails API response is shown correctly",groups={"api","regression","smoke"})
	public void userDetailsAPITest()  {
	
		given().spec(SpecUtils.requestSpecWithAuth(FD))
		       .when()
		       .get("userdetails")
		       .then()
		       .spec(SpecUtils.responseSpec_OK())
		       .and()
		       .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
		
	}

}
