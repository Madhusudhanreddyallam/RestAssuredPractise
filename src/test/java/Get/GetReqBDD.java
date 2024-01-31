package Get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import java.util.List;

import org.testng.annotations.Test;

public class GetReqBDD {
	
	@Test
	public void getApiWithQueryParam() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
						given().log().all()
			            	.headers("Authorization","Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
			            	.queryParam("limit", 5)
			            	.pathParam("code", "products")
						.when().log().all()
							.get("/{code}")
						.then().log().all()
							.assertThat()
							.contentType(ContentType.JSON)
							.statusCode(200)
							.header("Content-Type", "application/json; charset=utf-8")
							.time(lessThan(3000L))
							.statusLine(containsString("HTTP/1.1 200"))
							;
		
	}
	
	public void getApiValidateResponse() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response =		given()
						  			.queryParam("limit", 5)
						  		.when()
						  			.get("/products");
		
		JsonPath jsonpath = response.jsonPath();
		List<Integer> id = jsonpath.getList("id");
		List<Object> rate = jsonpath.getList("rating.rate");
		List<String> title = jsonpath.getList("title");
		
		System.out.println(id);
		System.out.println(rate);
		System.out.println(title);
		
	}
	
	
	public void getApiValidateResponse2() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		float response =		given()
						  			.queryParam("limit", 5)
						  		.when()
						  			.get("/products")
						  		.then()
						  			.extract()
						  			.path("[0].rating.rate");
		
		System.out.println(response);

		
	}
	


}
