import org.junit.Before;
import org.junit.Test;

import file.Resources;
import file.ResponseExtractor;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PlaceSearchAdvItems {
	Properties prop = new Properties();

	@Before
	public void getProperties() throws IOException {
		FileInputStream fis = new FileInputStream(
				"E:\\Project\\restassured_learning\\RestAssuredDemo\\src\\file\\default.properties");
		prop.load(fis);

	}

	@Test
	public void Test() {

		RestAssured.baseURI = prop.getProperty("HOST");
		Response res = given().param("location", "-33.8670522,151.1957362").param("radius", "500")
				.param("key", prop.getProperty("KEY")).when().get(Resources.getSearchJsonContext()).then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).log().all().and().body("results[0].name", equalTo("Sydney"))
				.and().header("Server", "scaffolding on HTTPServer2").extract().response();

		JsonPath jp = ResponseExtractor.rawToJson(res);
	int count = jp.get("results.size()");
	for(int i=0; i<count;i++){
		System.out.println(jp.getString("results["+i+"].place_id"));
	}
	}

}
