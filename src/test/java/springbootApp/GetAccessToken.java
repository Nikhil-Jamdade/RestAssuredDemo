package springbootApp;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAccessToken {

	public static void main(String[] args) {

		Response response = RestAssured.given()
				.baseUri("http://localhost:8088")
				.auth()
				.basic("rest-assured", "password")
				.contentType("application/x-www-form-urlencoded")
				.formParam("grant_type", "password")
				.formParam("username", "onlyfullstack")
				.formParam("password", "secret")
				.when()
				.post("/oauth/token");

		System.out.println("Response is: " + response.getBody().asString());

		JsonPath jpath = response.jsonPath();
		String token = jpath.get("access_token");

		System.out.println("Token is: " + token);
		
		//========Get students using oauth===========//
		
		response = RestAssured.given().baseUri("http://localhost:8088").auth().oauth2(token).when().get("/students");
		System.out.println("Get student: " + response.getBody().asString());	
		
		
		
		//============Map using headers directly============//
		
/*		HashMap<String, Object> headers = new HashMap<String, Object>();
		headers.put("Authorization", "Bearer " + token);
		
		response = RestAssured.given().baseUri("http://localhost:8088").headers(headers).when().get("/students");
		System.out.println("Get students: " + response.getBody().asString());	*/
	}
}
