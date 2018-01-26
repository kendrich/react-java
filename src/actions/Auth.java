package actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DB;
import models.User;
import utils.Helper;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = DB.login(request.getParameter("u"), request.getParameter("p"));
		if(user.isLoggedIn()) {
			Helper.setAccessControlHeaders(response);
			response.getWriter().write(Jwt.getToken(user));
		}else {
			response.sendError(HttpServletResponse.SC_ACCEPTED);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doOptions(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Helper.setAccessControlHeaders(response);
		response.setStatus(HttpServletResponse.SC_OK);
	}
	

	
}
