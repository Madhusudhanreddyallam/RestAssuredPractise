package Get;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.annotations.Test;
import io.restassured.RestAssured;


public class MatchersCollection {
	@Test (priority = 1)
	public void getVerifyJson() {
		RestAssured.baseURI = "https://gorest.co.in";
		given()
			.headers("Authorization","Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
		.when()
			.get("/public/v2/users")
		.then()
			.body("id", hasItem(5914072))
			.body("id", hasItems(5914078,5914077))			
			.body("name", hasItem("Smriti Chaturvedi"))	        
			.body("name", hasItems("Divya Bandopadhyay","Ghanshyam Varrier Jr."));	        
	}
	
	@Test (priority = 0)
	public void getListofValues() {

		RestAssured.baseURI = "https://gorest.co.in";

		List<String> names = given()
				.headers("Authorization","Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
				.when()
				.get("/public/v2/users")
				.then()
				.extract().path("name");
		System.out.println(names);	        

	}
}
