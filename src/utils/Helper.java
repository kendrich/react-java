package utils;

import javax.servlet.http.HttpServletResponse;

public class Helper {
    public static String parameterBuilder(String key, String value) {
    	
    	return key + "="+value;
    }
    
	public static void setAccessControlHeaders(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Methods", "POST");
//		response.setHeader("Access-Control-Allow-Headers", "Authorization");
	}
}
