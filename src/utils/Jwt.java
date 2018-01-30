package utils;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import models.User;

public class Jwt {
	static String key = "KEN.16020705";
	static String[] CKeys = {"u", "p", "cat", "uat", "su"};
	public static String getToken (User user){
		try {
		    Algorithm algorithm = Algorithm.HMAC256(key);
		    String token = JWT.create()
		    	.withSubject(user.getID())
		    	.withClaim("u", user.getUsername())
		    	.withClaim("p", user.getPassword())
		    	.withClaim("cat", user.getCreateAt())
		    	.withClaim("uat", user.getUpdatedAt())
		    	.withClaim("su", user.isSuperuser())
		        .withIssuer("K.L.A.Y")
		        .sign(algorithm);
		    System.out.println(token);
		    return token;
		} catch (UnsupportedEncodingException e){
		    //UTF-8 encoding not supported
			e.printStackTrace();
		} catch (JWTCreationException e){
		    //Invalid Signing configuration / Couldn't convert Claims.
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean validateToken(String id, String token) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(key);
		    JWTVerifier verifier = JWT.require(algorithm)
	    		.withSubject(id)
		        .withIssuer("K.L.A.Y")
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);
		    for (String str : CKeys) {
		    	if(jwt.getClaim(str).isNull()) {
		    		return false;
		    	}
			}
		    return true;
		} catch (UnsupportedEncodingException exception){
		    //UTF-8 encoding not supported
			System.out.println(exception.getMessage());
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
			System.out.println(exception.getMessage());
		}
		return false;
	}
}
