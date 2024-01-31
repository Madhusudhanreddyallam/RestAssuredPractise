package Get;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;

import java.util.List;

import org.testng.annotations.Test;

public class ComplexJsonParseIMP {

	@Test
	public void getSubarrayData() {
		RestAssured.baseURI = "http://ergast.com";
		
		JsonPath jsonPath = given()
								.pathParam("year", "2023")
							.when()
								.get("/api/f1/{year}/circuits.json")
								.jsonPath();
		
		String firstCircuitId = jsonPath.getString("MRData.CircuitTable.Circuits[0].circuitId");
		List<String> firstThreeCircuitIds = jsonPath.getList("MRData.CircuitTable.Circuits[0,1,2].circuitId");
		List<String> circuitIdsList = jsonPath.getList("MRData.CircuitTable.Circuits.circuitId");

		
		System.out.println(firstCircuitId + "\n");
		System.out.println(firstThreeCircuitIds+ "\n");
		System.out.println(circuitIdsList+ "\n");

	}

}
