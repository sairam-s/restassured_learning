import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import file.ResponseExtractor;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import org.junit.Before;
import org.junit.Test;

import file.ResponseExtractor;

public class AddXML {
	Properties prop = new Properties();
	@Before
	public void getProperties() throws IOException {
		FileInputStream fis = new FileInputStream(
				"E:\\Project\\restassured_learning\\RestAssuredDemo\\src\\file\\default.properties");
		prop.load(fis);

	}
	@Test
	public void add() throws IOException {
		String requestBody = GenerateStringFromResource("E:\\Project\\restassured_learning\\RestAssuredDemo\\src\\file\\AddXMLRequestBody.xml");
		String BODY = requestBody;

		RestAssured.baseURI = "https://maps.googleapis.com";
		Response res =  given()
				.queryParam("key", prop.getProperty("KEY"))
				.body(BODY).when().post("maps/api/place/add/xml").then()
				.assertThat().statusCode(200).and()
				.contentType(ContentType.XML).extract().response();

		XmlPath xp = ResponseExtractor.rawToXML(res);
		String status = xp.get("status").toString();
System.out.println(status);
	
	}
public static String GenerateStringFromResource(String path) throws IOException{
	return new String(Files.readAllBytes(Paths.get(path)));
	
}
}
