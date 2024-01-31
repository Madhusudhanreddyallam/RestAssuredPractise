package Schema;

import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class UpdateUserPatch {

	public String getRandomEmailId() {	
		return "madhu"+System.currentTimeMillis()+"@gmail.com";
	}

	@Test
	public void createUser() {
		RestAssured.baseURI = "https://gorest.co.in";
		//UserPojo up = new UserPojo("Madhu", getRandomEmailId(), "Male", "Active");

		//Line 24 is old way of writing line 27 is using lombok builder
		UserPojo user = new UserPojo.UserPojoBuilder()
				.name("Madhu")
				.email(getRandomEmailId())
				.gender("male")
				.status("Active")
				.build();

		Response response = given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization" , "Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
				.body(user)
				.when().log().all()
				.post("/public/v2/users");


		int id = response.jsonPath().get("id");

		given().log().all()
		.header("Authorization" , "Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
		.contentType(ContentType.JSON)
		.when().log().all()
		.get("/public/v2/users/"+id)
		.then().log().all()
		.body(matchesJsonSchemaInClasspath("schemavalidation.json"));

	}

}
