package pojo;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.ComplexPojo.Category;
import pojo.ComplexPojo.Tags;

public class ComplexPost {

	@Test
	public void post() {
		Category Category = new Category(100,"dog");
		List<String> photoUrls = Arrays.asList("https://www.dog.com" , "https://www.dog2.com");
		Tags Tags = new Tags (10 , "red");
		Tags Tags1 = new Tags (20 , "yellow");
		List<Tags> tags = Arrays.asList(Tags,Tags1);

			ComplexPojo cp = new ComplexPojo(1, Category, "Suma", photoUrls, tags, "avaiable");

//		//Creating test data
//		Category category = new Category.CategoryBuilder()
//				.id(200)
//				.name("cat")
//				.build();
//		
//		Tags tags = new Tags.TagsBuilder()
//				.id(101)
//				.name("redd")
//				.build();
//		
//		Tags tags1 = new Tags.TagsBuilder()
//				.id(200)
//				.name("yelloww")
//				.build();
//		//Inserting data into Pojo
//		ComplexPojo cp = new ComplexPojo.ComplexPojoBuilder()
//				.id(123)
//				.name("sumaa")
//				.status("availablee")
//				.category(category)
//				.photoUrls(Arrays.asList("https://www.dog.comm" , "https://www.dog2.comm"))
//				.tags(Arrays.asList(tags, tags1))
//				.build();

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
			ComplexPojo cpMapper = mapper.readValue(response.getBody().asString(), ComplexPojo.class);
			//from request
			System.out.println(cp.getCategory().getId() + " " + cp.getCategory().getName());
			//from response 
			System.out.println(cpMapper.getCategory().getId() + " " + cpMapper.getCategory().getName());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
