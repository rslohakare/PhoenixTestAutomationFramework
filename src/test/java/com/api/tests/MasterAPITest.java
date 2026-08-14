package com.api.tests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {
	
	@Test(description="Verify if the Master API is giving correct response",groups={"api","regression","smoke"})
	public void masterAPITest() {
		
		given()
		.spec(SpecUtils.requestSpecWithAuth(Role.FD))
		      .when()
		      .post("/master")
		      .then()
		      .spec(SpecUtils.responseSpec_OK())
		      .body("message",Matchers.equalTo("Success"))
		      .body("data", Matchers.notNullValue())
		      .body("data", Matchers.hasKey("mst_oem"))
		      .body("data",Matchers.hasKey("mst_model"))
		      .body("$", Matchers.hasKey("message"))
		      .body("$", Matchers.hasKey("data"))
		      .body("data.mst_oem.size()",Matchers.equalTo(2))  //Check the size of the json Array with matchers
		      .body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
		      .body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))
		      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIReponseSchema.json"));
		      
	}
	
	@Test(description="Verify if the Master API is giving correct status code invalid token",groups={"api","negetive","regression","smoke"})
	public void invalidTokenMasterAPIRequest() {
		
		given()
		.spec(SpecUtils.requestSpec())
	      .when()
	      .post("/master")
	      .then()
	      .log().all()
	      .statusCode(401);
	}

}
