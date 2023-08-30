package restAPI;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutReq {
	@Test
	public void test1() {

		HashMap<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("name", "Rohan");
		requestBody.put("salary", "10000");

		RestAssured.baseURI = "http://localhost:3000/employees";
		RequestSpecification request = RestAssured.given();

		Response response = request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(requestBody) //<<--Above value is used here
				.put("/19"); //make sure you give correct existing value here
		String body = response.getBody().asString();
		System.out.println("Response body is: " + body);
		System.out.println("Status codes are: " + response.statusCode());

		Assert.assertEquals(response.statusCode(), 200);
	}
}
