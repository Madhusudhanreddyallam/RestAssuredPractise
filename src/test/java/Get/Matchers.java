package Get;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;


public class Matchers {
	@Test
	public void getVerifyJson() {
		RestAssured.baseURI = "https://gorest.co.in";
		given()
			.headers("Authorization","Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
			.queryParam("id", "5971272")
		.when()
			.get("/public/v2/users")
		.then().log().all()
			.contentType(ContentType.JSON)
			.body("$.size()", equalTo(1))
			.body("$.size()", not(equalTo(2)))
			.body("[0].name", containsString("Bhargavi"))
			.body("[0].name", startsWith("Bhargavi"))
			.body("[0].name", endsWith("JD"))
			.body("$.size()", greaterThan(0))
			.body("$.size()", lessThan(2));		
	}
}
