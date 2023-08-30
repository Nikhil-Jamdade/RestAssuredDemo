package restAPI;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteReq {

	@Test
	public void test1() {

		RestAssured.baseURI = "http://localhost:3000/employees";
		RequestSpecification request = RestAssured.given();

		Response response = request.delete("/19"); //<<--give existing value here

		System.out.println("Status codes are: " + response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);

	}

}