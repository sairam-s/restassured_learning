import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class GoogleSearchApi {

	@Test
	public void Test() {
		
		RestAssured.baseURI = "https://www.googleapis.com";
		given().param("q", "Test")
		.param("alt","json")
				.param("key", "AIzaSyAFUkh5n2qrNpoRFHHiUuph_UqEoiETGUM")
				.when()
				.get("/customsearch/v1")
				.then()
				.assertThat()
				.statusCode(200)
				.and()
				.contentType(ContentType.JSON);
	}

}
