package pojo;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.ComplexPojoEncap.Category;
import pojo.ComplexPojoEncap.Tags;

public class ComplexPostEncap {
	
	@Test
	public void post() {
		//Creating test data
		Category category = new Category(100, "dog");
		List<String> photoUrls = Arrays.asList("https://www.dog.com", "https://www.dog2.com");
		Tags tags = new Tags(10, "red");
		Tags tags1 = new Tags(20, "yellow");
		List<Tags> tagList = Arrays.asList(tags, tags1);
		//Inserting into Pojo
		ComplexPojoEncap cp = new ComplexPojoEncap(1, category, "Suma", photoUrls, tagList, "available");

		
		RestAssured.baseURI = "https://petstore.swagger.io";
		Response response = given()
				.contentType(ContentType.JSON)
				.body(cp)
				.when()
				.post("/v2/pet");
		
		response.prettyPrint();

		//de-serializing Json to pojo(Java Object)
		ObjectMapper mapper = new ObjectMapper();
		try {
			ComplexPojoEncap cpMapper = mapper.readValue(response.getBody().asString(), ComplexPojoEncap.class);
			//from request
			System.out.println(cp.getCategory().getId() + " " + cp.getCategory().getName());
			//from response 
			System.out.println(cpMapper.getCategory().getId() + " " + cpMapper.getCategory().getName());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
