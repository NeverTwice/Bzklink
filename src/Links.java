import Bean.User;
import Bean.links.Complex;
import Bean.links.Simple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/link")
public class Links extends HttpServlet {
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String origin = (String) request.getAttribute("origin");
        String target = (String) request.getAttribute("target");

        if(origin!=null && target!=null) {
            request.setAttribute("origin", origin);
            request.setAttribute("target", target);
        }

        request.getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String origin = request.getParameter("url");

        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user!=null) {
                String from = request.getParameter("available_from");
                String to = request.getParameter("available_to");
                String max_clic = request.getParameter("max_clic");
                String password = request.getParameter("password");

                int maxClic = 0;
                if (!max_clic.equals("")) {
                    maxClic = Integer.parseInt(max_clic);
                }

                Date availableFrom = null;
                Date availableTo = null;

                try {
                    java.sql.Date start = null;
                    java.sql.Date expiration = null;

                    if (!from.equals("")) {
                        availableFrom = new SimpleDateFormat("yyyy-MM-dd").parse(from);
                        start = new java.sql.Date(availableFrom.getTime());
                    }

                    if (!to.equals("")) {
                        availableTo = new SimpleDateFormat("yyyy-MM-dd").parse(to);
                        expiration = new java.sql.Date(availableTo.getTime());
                    }


                    Complex complex = new Complex(origin, start, expiration, user, password, maxClic);

                    if (complex.save()) {
                        request.setAttribute("origin", complex.getOrigin());
                        request.setAttribute("target", complex.getTarget());
                    } else {
                        request.setAttribute("error", "error");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } else {
                Simple simple = new Simple(origin);

                if (simple.save()) {
                    request.setAttribute("origin", simple.getOrigin());
                    request.setAttribute("target", simple.getTarget());
                } else {
                    request.setAttribute("error", "error");
                }
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }
}
