package actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import dao.DB;
import models.User;
import utils.Jwt;

/**
 * Servlet implementation class Auth
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/auth" })
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	        String json = "";
	        if(br != null){
	            json = br.readLine();
	        }
			Map<String, Map<String, String>> map = new Gson().fromJson(json, Map.class);
			String username = map.get("credentials").get("username");
			String password = map.get("credentials").get("password");
			
			User userAction = DB.login(username, password);
			if(userAction.isLoggedIn()) {
				User user = new User();
				user.setID(userAction.getID());
				user.setToken(Jwt.getToken(userAction));
				user.setLoggedIn(true);
				response.getWriter().write(new Gson().toJson(user));
			}else {
				response.sendError(400, "Invalid credentials");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(400, "Invalid credentials");
		}
	}
}
