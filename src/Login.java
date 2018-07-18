import Bean.User;
import dao.Exceptions.AlreadyExistException;
import dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class Register
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

         UserDao userDao = new UserDao();

         try {
             User user = userDao.findBy("email", email);

             if (user != null && user.getPassword().equals(password) && user.isEnabled()) {
                 HttpSession session = request.getSession();
                 session.setAttribute("user", user);
                 session.setMaxInactiveInterval(30*60);

                 response.sendRedirect("/");
                 return;
             }

             if (user != null && !user.isEnabled()) {
                 request.setAttribute("login_error", "Your account is not enabled. Please check your mails");
             } else {
                 request.setAttribute("login_error", "Wrong email and/or wrong password");
             }

             ServletContext context= getServletContext();
             RequestDispatcher rd= context.getRequestDispatcher("/");

             rd.forward(request, response);
         } catch (SQLException e) {
             e.printStackTrace();
             System.out.println("User not connected");
             request.setAttribute("login_error", "Error auth");

             ServletContext context= getServletContext();
             RequestDispatcher rd= context.getRequestDispatcher("/");

             rd.forward(request, response);
         }
    }
}
