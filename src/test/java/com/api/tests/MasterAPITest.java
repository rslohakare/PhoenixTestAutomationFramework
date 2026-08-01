package com.api.tests;

import org.codehaus.groovy.runtime.callsite.PogoGetPropertySite;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() throws IOException {
		
		given().baseUri(getProperty("BASE_URI"))
		      .and()
		      .header("Authorization",getToken(Role.FD))
		      .and()
		      .contentType("")
		      .log().all()
		      .when()
		      .post("/master")
		      .then()
		      .log().all()
		      .statusCode(200)
		      .time(Matchers.lessThan(1000L))
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
	
	@Test
	public void invalidTokenMasterAPIRequest() throws IOException {
		
		given().baseUri(getProperty("BASE_URI"))
	      .and()
	      .header("Authorization","")
	      .and()
	      .contentType("")
	      .log().all()
	      .when()
	      .post("/master")
	      .then()
	      .log().all()
	      .statusCode(401);
	}

}
