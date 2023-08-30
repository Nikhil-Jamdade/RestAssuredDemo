package github;

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

//This class may not run. It needs few modifications. Better to use new token always. Current used one might be expired.
//Refer file "repodata.json" present near pom.xml for more info related to this class.

public class CreateRepo {

	@Test
	public void test1() throws IOException {

		byte[] dataFile = Files.readAllBytes(Paths.get("repodata.json"));

		RestAssured.baseURI = "https://api.github.com/user/repos";
		RequestSpecification request = RestAssured.given();

		Response response = request.auth().oauth2("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") //github personal token
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(dataFile) // <<--Above file value here
				.post();

		String body = response.getBody().asString();
		System.out.println("Response body is: " + body);
		System.out.println("Status code is: " + response.statusCode());

		Assert.assertEquals(response.statusCode(), 201);
	}
}
