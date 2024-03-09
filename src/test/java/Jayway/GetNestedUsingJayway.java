package Jayway;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetNestedUsingJayway {

	@Test
	public void test() {
		RestAssured.baseURI = "https://ergast.com";

		Response response = when()
				.get("/api/f1/2023/circuits.json");
		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
		
		List<Map<String, Object>> data = JsonPath.read(jsonResponse,
				"$.MRData.CircuitTable.Circuits[?(@.Location.country == 'Australia' )].Location[\"lat\",\"locality\",\"country\"]");

		List<Map<String, Object>> data2 = JsonPath.read(jsonResponse,
				"$.MRData.CircuitTable.Circuits[0,1,2].Location[\"lat\",\"locality\",\"country\"]");
		
		for(Map<String , Object> map : data) {
			String lat = (String) map.get("lat");
			String country = (String) map.get("country");
			String locality = (String) map.get("locality");
			System.out.println(lat + " " + country + " " + locality);
		}
		
		for(Map<String , Object> map : data2) {
			String lat = (String) map.get("lat");
			String country = (String) map.get("country");
			String locality = (String) map.get("locality");
			System.out.println(lat + " " + country + " " + locality);
		}
	}

}
