package server.servlets;

import server.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SignOutServlet extends HttpServlet {

    private final UserService userService;
    private final String USER_SESSION_ATTRIBUTE = "USER";

    public SignOutServlet(UserService service) {
        this.userService = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }
}
