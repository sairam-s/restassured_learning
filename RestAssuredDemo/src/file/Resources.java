package file;

public class Resources {

	public static String getAddContext(){
		return ("maps/api/place/add/json");
	}
	
	public static String getDeleteContext(){
		return ("/maps/api/place/delete/json");
	}
	
	public static String getSearchJsonContext(){
		return("/maps/api/place/nearbysearch/json");
	}
	
	public static String getSearchXMLContext(){
		return("/maps/api/place/nearbysearch/xml");
	}
}
