package restAPI;

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
//import java.nio.file.Paths;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostReqWithFile {

	@Test
	public void test1() throws IOException {

		byte[] dataFile = Files.readAllBytes(Paths.get("data.json"));
		
		RestAssured.baseURI = "http://localhost:3000/employees";
		RequestSpecification request = RestAssured.given();
		
		Response response = request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(dataFile)  //<<--File value is called here inside body
				.post("/create");
		
		String body = response.getBody().asString();
		System.out.println("Response body is: " + body);
		System.out.println("Status codes are: " + response.statusCode());
		
		Assert.assertEquals(response.statusCode(), 201);
	}
}
