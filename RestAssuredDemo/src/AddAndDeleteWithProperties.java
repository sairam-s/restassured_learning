import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.Before;
import org.junit.Test;

public class AddAndDeleteWithProperties {
	Properties prop = new Properties();

	@Before
	public void getProperties() throws IOException {
		FileInputStream fis = new FileInputStream(
				"/Users/sairams/Project/RestProject/restassured_learning/RestAssuredDemo/src/file/default.properties");
		prop.load(fis);

	}

	@Test
	public void addAndDelete() {
		String BODY = "{"
				+ "\"location\": {"
				+ "\"lat\": -33.8669710,"
				+ "\"lng\": 151.1958750"
				+ "},"
				+ "\"accuracy\": 50,"
				+ "\"name\": \"Google Shoes!\","
				+ "\"phone_number\": \"(02) 9374 4000\","
				+ "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","
				+ "\"types\": [\"shoe_store\"],"
				+ "\"website\": \"http://www.google.com.au/\","
				+ "\"language\": \"en-AU\"" + "}";

		RestAssured.baseURI = prop.getProperty("HOST");
		;
		Response res = given()
				.queryParam("key", prop.getProperty("KEY"))
				.body(BODY).when().post("maps/api/place/add/json").then()
				.assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and()
				.body("status", equalTo("OK")).extract().response();
		String reponseString = res.asString();
		JsonPath jp = new JsonPath(reponseString);
		String placeId = jp.get("place_id");
		given().queryParam("key", "AIzaSyAFUkh5n2qrNpoRFHHiUuph_UqEoiETGUM")
				.body("{" + "\"place_id\":\"" + placeId + "\"" + "}").when()
				.post("/maps/api/place/delete/json").then().assertThat()
				.statusCode(200).contentType(ContentType.JSON).and()
				.body("status", equalTo("OK"));

	}

}
