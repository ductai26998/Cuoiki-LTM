package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String repassword = (String) request.getParameter("repassword");

		UserDAO userDAO = new UserDAO();
		boolean isUsernameExists = userDAO.isUsernameExists(username);

		ArrayList<String> errors = new ArrayList<String>();
		if (username == "") {
			errors.add("Username is invalid !");
		}
		if (isUsernameExists) {
			errors.add("Username is exist !");
		}
		if (password == "") {
			errors.add("Password id invalid !");
		}
		if (!(password.equals(repassword))) {
			errors.add("Password don't match !");
		}

		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			doGet(request, response);
		} else {
			userDAO.insertUser(username, password);
			response.sendRedirect("login");
		}
	}

}
