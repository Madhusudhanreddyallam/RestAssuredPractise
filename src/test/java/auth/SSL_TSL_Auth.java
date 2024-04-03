package auth;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public class SSL_TSL_Auth{
	
	@BeforeMethod
	  public static void auth() { 
		  RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig()
	                        .trustStore("truststore", "abcd@1234")
	                        .trustStoreType("PKCS12")//or (JKS)
	                        .keyStore("badssl.com-client.p12", "badssl.com")
	                        .keystoreType("PKCS12") );  
	} 
	

	@Test
	public void testCase() {
	    Response response = RestAssured.given().log().all()
	            						.when().log().all()
	            						.get("https://client.badssl.com/");
	            

	    System.out.println(response.statusCode());
	}


}
