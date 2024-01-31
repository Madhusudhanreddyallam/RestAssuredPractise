package Get;

import java.util.List;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest {

	@Test
	public void getUserAPIBDD() {
		RestAssured.baseURI = "https://gorest.co.in";
		Response response = given()
				            	.headers("Authorization","Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba")
				            .when()
				            	.get("public/v2/users");
		
		JsonPath js = response.jsonPath();
		List<Integer> ProductIds = js.getList("id");
		System.out.println(response.getStatusLine());
		
		System.out.println(ProductIds);

	}

}
