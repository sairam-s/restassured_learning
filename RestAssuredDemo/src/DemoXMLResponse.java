import org.junit.Before;
import org.junit.Test;

import file.Resources;
import file.ResponseExtractor;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DemoXMLResponse {
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
		Response res = given().param("location", "-33.8670522,151.1957362")
				.param("radius", "500")
				.param("key", prop.getProperty("KEY"))
				.when()
				.get(Resources.getSearchXMLContext())
				.then().log().all().and()
				.assertThat()
				.statusCode(200).extract().response();

XmlPath xp = ResponseExtractor.rawToXML(res);
System.out.println(xp.get("PlaceSearchResponse.status").toString());
}

}
