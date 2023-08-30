package restAPI;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostReqWithJsonObj {

	@Test
	public void test1() {

		JSONObject jobj = new JSONObject();
		jobj.put("name", "Shu");
		jobj.put("salary", "30000");
		
		RestAssured.baseURI = "http://localhost:3000/employees";
		RequestSpecification request = RestAssured.given();
		
		Response response = request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(jobj.toString())  //<<--Json_obj value is called here inside body
				.post("/create");
		
		String body = response.getBody().asString();
		System.out.println("Response body is: " + body);
		System.out.println("Status codes are: " + response.statusCode());
		
		Assert.assertEquals(response.statusCode(), 201);
	}
}
