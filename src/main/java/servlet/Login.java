package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.User;
import dao.UserDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		ArrayList<String> errors = new ArrayList<String>();

		if (username == "") {
			errors.add("Username is invalid !");
		}
		if (password == "") {
			errors.add("Password id invalid !");
		}

		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			doGet(request, response);
		} else {
			UserDAO userDAO = new UserDAO();
			User user = userDAO.findByUsernameAndPassword(username, password);

			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getId());
				session.setAttribute("username", user.getUsername());
				response.sendRedirect(request.getContextPath() + "/home");
			} else {
				RequestDispatcher dispatcher = this.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/error.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
