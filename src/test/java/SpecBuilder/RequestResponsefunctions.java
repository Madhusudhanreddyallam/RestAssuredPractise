package SpecBuilder;

import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponsefunctions {
	
	public static RequestSpecification getCallconfig() {
		
		RequestSpecification request = new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
				.addQueryParam("id", "5971268")
				.build();
		return request;
	}
	
	public static ResponseSpecification getCallAssertions() {

		ResponseSpecification response = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectHeader("Content-Type", "application/json; charset=utf-8")
				.expectResponseTime(lessThan(3000L))
				.expectBody("$.size()" , equalTo(1))
				.expectStatusCode(200)
				.build();
		return response;
	}
	
	@Test
	public void getRequest() {
		given()
			.spec(getCallconfig())
		.when()
			.get("/public/v2/users")
		.then().log().all()
			.spec(getCallAssertions());

	}

}
