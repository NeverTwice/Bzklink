import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;        

/**
 * Servlet implementation class Generate
 */
@WebServlet("/generate")
public class Generate extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Params from form
		String url = request.getParameter("url");
		String with_password = request.getParameter("with_password");
		String password_url = request.getParameter("password_url");
		
		// Session
		HttpSession session = request.getSession();
		
		// Redirection
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Home");
		
		
		String id = UUID.randomUUID().toString();
		
		
		String retour = "http://localhost:8080/link?id="+id;
		request.setAttribute("link", retour);
		dispatcher.forward(request,response);
		
	}

}
