package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.FileItem;
import dao.FileDAO;
import utils.FileUpdateUtil;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
@MultipartConfig(maxFileSize = 1024 * 1024 * 100, // 100 MB
		maxRequestSize = 1024 * 1024 * 100) // 100 MB
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Home() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = null;
		String username = null;
		if (session.getAttribute("userId") != null) {
			userId = session.getAttribute("userId").toString();
			username = session.getAttribute("username").toString();
		}

		FileDAO fileDAO = new FileDAO();
		ArrayList<FileItem> files = fileDAO.getAllFileOfMe(userId);

		request.setAttribute("files", files);
		request.setAttribute("username", username);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String userId = null;
		if (session.getAttribute("userId") != null) {
			userId = session.getAttribute("userId").toString();
			ArrayList<String> fileInfo = FileUpdateUtil.saveImage(request);
			FileDAO fileDAO = new FileDAO();
			fileDAO.addFile(fileInfo, userId);
		}
		doGet(request, response);
	}

}
