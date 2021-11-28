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

import bo.FileItem;
import dao.FileDAO;
import utils.FileUpdateUtil;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
@MultipartConfig
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
		// TODO Auto-generated method stub
//		HttpServletRequest httpReq = (HttpServletRequest) request;
//		HttpSession session = httpReq.getSession(false);
//		
//		try {
//			request.setAttribute("role", session.getAttribute("role"));
//		} catch (Exception e) {
//			request.setAttribute("role",null);
//		}

		FileDAO fileDAO = new FileDAO();
		ArrayList<FileItem> files = fileDAO.getAllFile();

		request.setAttribute("files", files);
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

		ArrayList<String> fileInfo = FileUpdateUtil.saveImage(request);
		FileDAO fileDAO = new FileDAO();
		fileDAO.addFile(fileInfo);
		doGet(request, response);
	}

}
