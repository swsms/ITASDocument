package server.servlets;

import server.entities.UserEntity;
import server.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Артем on 28.12.2015.
 */
public class LoginServlet extends HttpServlet {
    private final UserService userService;

    public LoginServlet(UserService service) {
        this.userService = service;
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        UserEntity profile = userService.login(login, pass);

        if (profile == null) {
            response.getWriter().println("Unauthorized");
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            //  userService.addSession(request.getSession().getId(), profile);

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Authorized");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }
}
