package restAPIBDD;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetReqBDD {

	@Test
	public void test1() {

		RestAssured.given().baseUri("http://localhost:3000/employees").when().get().then().log().body().statusCode(200)
				.body("[2].name", Matchers.equalTo("Nikhil"));
//value '2' was given because in the output Nikhil was present at 3rd position(0, 1, 2, 3, etc) 
	}

	@Test
	public void test2() {

		RestAssured.given().baseUri("http://localhost:3000/employees").when().get("/1").then().log().body()
				.statusCode(200).body("name", Matchers.equalTo("Pankaj"));
// first body() is used to print the body in console. Second for verifying the name in output.
	}

	@Test
	public void test3() {
		Response response = RestAssured.given().baseUri("http://localhost:3000/employees").when().get();

		Assert.assertEquals(response.statusCode(), 200);

		JsonPath json = response.jsonPath();
		List<String> names = json.get("name");
		List<Integer> ids = json.get("id");
		int empID = 1;

		for (int i = 0; i < ids.size(); i++) {
			if (ids.get(i) == empID) {
				
				System.out.println(names.get(i));
				Assert.assertEquals(names.get(i), "Pankaj");
			}
		}

	}

}
