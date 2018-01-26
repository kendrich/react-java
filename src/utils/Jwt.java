package utils;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import models.User;

public class Jwt {
	
	public static String getToken (User user){
		try {
		    Algorithm algorithm = Algorithm.HMAC256("KEN.16020705");
		    String token = JWT.create()
		    	.withSubject(user.getID())
		    	.withClaim("u", user.getUsername())
		    	.withClaim("p", user.getPassword())
		    	.withClaim("cat", user.getCreateAt())
		    	.withClaim("uat", user.getUpdatedAt())
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
}
