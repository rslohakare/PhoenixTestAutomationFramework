package com.api.tests;

import static com.api.constant.Role.FD;
import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class CountAPITest {
	
	@Test
	public void verifyCountAPIResponse() {
		
		given().baseUri(getProperty("BASE_URI"))
		       .and()
		       .header("Authorization",getToken(FD))
		       .log().uri()
		       .log().method()
		       .log().headers()
		       .when()
		       .get("/dashboard/count")
		       .then()
		       .log().all()
		       .statusCode(200)
		       .body("message", Matchers.equalTo("Success"))
		       .time(Matchers.lessThan(1000L))
		       .body("data", Matchers.notNullValue())
		       .body("data.size()", Matchers.equalTo(3))
		       .body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
		       .body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
		       .body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
		       .body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema_FD.json"));
			
	}
	
	@Test
	public void countAPITest_MissingAuthToken() {
		
		given().baseUri(getProperty("BASE_URI"))
		       .and()
		       .log().uri()
		       .log().method()
		       .log().headers()
		       .when()
		       .get("/dashboard/count")
		       .then()
		       .log().all()
		       .statusCode(401);
		       
		
		
		
	}

}
