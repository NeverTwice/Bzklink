import Bean.clicks.Stats;
import Bean.links.Complex;
import Bean.links.Simple;
import dao.ComplexLinkDao;
import dao.SimpleLinkDao;
import dao.StatsClickDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Servlet implementation class Home
 */
@WebServlet("/redirect/*")
public class Redirect extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo(); // /{value}/test
		String[] pathParts = pathInfo.split("/");
		String target = pathParts[1];

		SimpleLinkDao simpleLinkDao = new SimpleLinkDao();
		ComplexLinkDao complexLinkDao = new ComplexLinkDao();
		StatsClickDao statsClickDao = new StatsClickDao();

		try {
			Simple simpleLink = simpleLinkDao.findBy("target", target);

			if (simpleLink == null) {
				Complex complexLink = complexLinkDao.findBy("target", target);

				Date now = new Date();

				Date expiration = null;
				if (complexLink.getExpiration() != null) {
					expiration = complexLink.getExpiration();
				}

				Date availability = null;
				if (complexLink.getDateAvailable() != null) {
					availability = complexLink.getDateAvailable();
				}

				if (expiration != null && expiration.before(now) || availability != null && availability.after(now)) {
					response.sendRedirect("/");
					return;
				}

				if(!complexLink.getPassword().equals("")) {
					request.setAttribute("target", target);
					request.getRequestDispatcher( "/WEB-INF/password.jsp" ).forward( request, response );
					return;
				}

				ArrayList<Stats> nbClick = statsClickDao.findByComplex(complexLink.getId());

				if (complexLink.getMaxClick() != 0 && complexLink.getMaxClick() <= nbClick.size()) {
					response.sendRedirect("/");
					return;
				}


				Stats stats = new Stats(complexLink);
				stats.save();

				response.sendRedirect(complexLink.getOrigin());
				return;
			} else {
				response.sendRedirect(simpleLink.getOrigin());
				return;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComplexLinkDao complexLinkDao = new ComplexLinkDao();
		String target = request.getParameter("target");
		if(target != null) {
			Complex complexLink;
			try {
				complexLink = complexLinkDao.findBy("target", target);

				if(request.getParameter("password").equals(complexLink.getPassword())) {
					response.sendRedirect(complexLink.getOrigin());
				} else {
					request.setAttribute("target", target);
					request.getRequestDispatcher( "/WEB-INF/password.jsp" ).forward( request, response );
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			request.getRequestDispatcher( "/WEB-INF/password.jsp" ).forward( request, response );
		}
	}

}
