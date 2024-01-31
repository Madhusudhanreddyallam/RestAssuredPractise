package Delete;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class DeleteUser {

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
       //Create User
		Response response = given()
				.contentType(ContentType.JSON)
				.header("Authorization" , "Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
				.body(user)
				.when()
				.post("/public/v2/users");
		
		
		int id = response.jsonPath().get("id");
		response.prettyPrint();
		
		//delete user
		Response deleteResponse = given()
				.header("Authorization" , "Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
				.when()
				.delete("/public/v2/users/"+id);

		System.out.println(deleteResponse.statusCode());
		
		//get user not found
		Response getResponse = given()
				.header("Authorization" , "Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
				.when()
				.get("/public/v2/users/"+id);
		
		getResponse.prettyPrint();
		System.out.println(getResponse.statusCode());
	}

}
