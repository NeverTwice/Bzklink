import Bean.User;
import dao.Exceptions.AlreadyExistException;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

/**
 * Servlet implementation class RegisterUrl
 */
@WebServlet("/confirm_account/*")
public class RegisterUrl extends HttpServlet {
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // /{value}/test
        String[] pathParts = pathInfo.split("/");
        String token = pathParts[1];

        if (token == null) {
            response.sendRedirect("/");
            return;
        }
        UserDao userDao = new UserDao();

        User user = null;

        try {
            user = userDao.findBy("token", token);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/");
            return;
        }

        if (user != null && user.getToken() != null && user.getToken().equals(token)) {
            user.setEnabled(true);

            try {
                user.save();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("/");

    }
}
