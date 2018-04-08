import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DemoOne {

	@Test
	public void Test() {

		RestAssured.baseURI = "https://maps.googleapis.com";
		given().param("location", "-33.8670522,151.1957362")
				.param("radius", "500")
				.param("key", "AIzaSyAFUkh5n2qrNpoRFHHiUuph_UqEoiETGUM")
				.when()
				.get("/maps/api/place/nearbysearch/json")
				.then()
				.assertThat()
				.statusCode(200)
				.and()
				.contentType(ContentType.JSON)
				.and()
				.body("results[0].name", equalTo("Sydney"))
				.and()
				.body("results[0].place_id",
						equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and()
				.header("Server", "pablo");

	}

}
