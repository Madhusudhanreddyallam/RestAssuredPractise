package Post;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


import static io.restassured.RestAssured.*;

import java.io.File;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class postCallSample {

	public void postCallAssert() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		String token = given()
				.contentType(ContentType.JSON)
				.body(new File("./src/test/Resources/Data/payload.json"))
			.when()
				.post("/auth")
			.then()
				.extract().path("token");

		System.out.println(token);
	}
	
	@Test
	public void postCallAssertResponse() {
		RestAssured.baseURI = "https://gorest.co.in/public";
		//If this method gives 422 error it means "payload1.json" file change the emailID
		int userId = given()
				.contentType(ContentType.JSON)
				.body(new File("./src/test/Resources/Data/payload1.json"))
				.header("Authorization", "Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
			.when()
				.post("/v2/users")
			.then()
				.statusCode(201)
				.body("name", equalTo("Madhu"))
				.extract().path("id");
		
		given()
		.header("Authorization", "Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
		.when()
		.get("/v2/users/" + userId)
		.then()
		.statusCode(200)
		.body("id", equalTo(userId));

	}

}
