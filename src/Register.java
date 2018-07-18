import java.io.IOException;
import java.sql.SQLException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.*;
import Core.Mailer.Mail;
import dao.Exceptions.AlreadyExistException;
import dao.UserDao;

/**
 * Servlet implementation class Register
 */
@WebServlet("/sign_up")
public class Register extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = (String) request.getAttribute("email");
        String login = (String) request.getAttribute("Login");
        String password = (String) request.getAttribute("Password");
        String type = (String) request.getAttribute("type");

        String error = (String) request.getAttribute("error");
        String success = (String) request.getAttribute("success");

        if(email!=null) {
            request.setAttribute("email", email);
        }

        if(login!=null) {
            request.setAttribute("login", login);
        }

        if(password!=null) {
            request.setAttribute("password", password);
        }

        if(type!=null) {
            request.setAttribute("type", type);
        }

        if(error!=null) {
            request.setAttribute("error", error);
        }

        if(success!=null) {
            request.setAttribute("success", success);
        }

        request.getRequestDispatcher( "/WEB-INF/register.jsp" ).forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String type = request.getParameter("type");

        boolean isValidUser = Core.Inspector.User.isValidUser(login, email, password, type);

        if (isValidUser) {
            try {
                User user = new User(login, email, password, type);
                user.setToken(user.generateToken());

                if (user.save()) {
                    Mail mail = new Mail();
                    mail.send("adrienpayen@maildrop.cc", "test", "test : http://localhost:8080/confirm_account/" + user.getToken());

                    request.setAttribute("success", "You are registered, check your mails");
                } else {
                    request.setAttribute("error", "User already exist");
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("error", "Form errors");
        }

        doGet(request, response);
    }
}
