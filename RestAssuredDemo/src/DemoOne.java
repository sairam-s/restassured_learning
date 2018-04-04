import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;


public class DemoOne {

	public static void main(String[] args) {
		
   RestAssured.baseURI = "https://maps.googleapis.com";
   given().
    param("location","-33.8670522,151.1957362").
    param("radius", "500").
    param("Key","AIzaSyAFUkh5n2qrNpoRFHHiUuph_UqEoiETGUM").
    when().
     get("/maps/api/place/nearbysearch/json").
    then().assertThat().statusCode(200);
     

	}

}
