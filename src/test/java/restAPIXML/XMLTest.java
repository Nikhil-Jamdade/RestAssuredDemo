package restAPIXML;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;

public class XMLTest {

	@Test
	public void test1() {

		RestAssured.given().baseUri("https://chercher.tech/sample/api/books.xml").when().get().then().log().body()
				.statusCode(200);
	}

	@Test
	public void test2() {

		Response response = RestAssured.given().baseUri("https://chercher.tech/sample/api/books.xml").when().get();

		NodeChildrenImpl books = response.then().extract().path("bookstore.book.title");
		// For author: replace 'title' with 'author'
		System.out.println("All books: " + books.toString());
		//Separate output below
		System.out.println("First book: " + books.get(0).toString());
		System.out.println("Second book: " + books.get(1).toString());
		
		NodeChildrenImpl booksTag = response.then().extract().path("bookstore.book");
		
		for(int i=0;i<booksTag.size();i++) {
			System.out.println(booksTag.get(i).getAttribute("category"));
		}
	}
}
