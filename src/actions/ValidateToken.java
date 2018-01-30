package actions;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import utils.Helper;
import utils.Jwt;

/**
 * Servlet implementation class ValidateToken
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ValidateToken" })
public class ValidateToken extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Map<String, Map<String, String>> map= new Gson().fromJson(Helper.getJSON(request), Map.class);
			if(Jwt.validateToken(map.get("user").get("id"), map.get("user").get("token"))) {
				response.getWriter().write("valid");
			}else {
				response.getWriter().write("invalid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("invalid");
		}
	}

}
