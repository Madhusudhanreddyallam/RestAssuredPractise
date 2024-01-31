package Patch;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

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

		Response response = given()
				.contentType(ContentType.JSON)
				.header("Authorization" , "Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
				.body(user)
				.when()
				.post("/public/v2/users");
		
		
		int id = response.jsonPath().get("id");
		
		//updating name using setter
		user.setName("MadhusudhanReddy");
		Response getResponse = given()
				.header("Authorization" , "Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
				.contentType(ContentType.JSON)
				.body(user)
				.when()
				.patch("/public/v2/users/"+id);

		ObjectMapper mapper =  new ObjectMapper();
		try {
			UserPojo pojo = mapper.readValue(getResponse.getBody().asString(), UserPojo.class);
			/*
			 * Getters and setters were not generated due to jar issue
			 * https://stackoverflow.com/questions/11803948/lombok-is-not-generating-getter-and-setter
			 * Refer to above solution and fixed it,  refer to 20 solution
			 */
			System.out.println(user.getName()+ " "+ pojo.getName());
			Assert.assertEquals(user.getName(), pojo.getName());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
