package springbootApp;

//import java.util.List;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//This class may not run. It needs some alterations

public class GetAllEmp {

	public class GetAllEmployee {
		@Test
		public void test1() {
			RestAssured.baseURI = "http://localhost:8088/employees";
			RequestSpecification request = RestAssured.given();
			Response response = request.get();
			String body = response.getBody().asString();
			System.out.println("Response body is " + body);
			System.out.println("Response code is " + response.statusCode());
			System.out.println("Response header is " + response.getHeader("Content-Type"));

			// Print all the names of employees on the console
			/*
			 * JsonPath json = response.jsonPath(); 
			 * List<String> names = * json.get("firstName"); 
			 * for (String name : names) {
			 * System.out.println("The name is " + name);
			 * }
			 */
		}
	}
}
