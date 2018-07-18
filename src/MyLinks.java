import Bean.User;
import Bean.links.Complex;
import dao.ComplexLinkDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyLinks
 */
@WebServlet("/links")
public class MyLinks extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComplexLinkDao complexLinkDao = new ComplexLinkDao();


		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			List<Complex> complex = complexLinkDao.findByUserId(user.getId());

			request.setAttribute("complexe_link", complex);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher( "/WEB-INF/dashboard.jsp" ).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
