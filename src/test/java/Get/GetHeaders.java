package Get;

import java.util.List;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetHeaders {

	@Test
	public void getUserAPIHeaders() {

		RestAssured.baseURI = "https://gorest.co.in";

		RequestSpecification request = RestAssured.given();	
		
		request.queryParam("", "");
		request.header("Authorization","Bearer 6442e439f9a859bb160324bafeb5ad9963f063684860e0ac280b561d3bcdf7ba");

		Response response = request.get("public/v2/users");	

		String headerValue = response.header("Content-Type");
		System.out.println(headerValue);
		
	
		List<Header> headers = response.headers().asList();
		for(Header header : headers) {
			System.out.println(header.getName() + " = " + header.getValue());
		}
	}

}
