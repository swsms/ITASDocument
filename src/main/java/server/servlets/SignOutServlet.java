package server.servlets;

import server.entities.UserEntity;
import server.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SignOutServlet extends HttpServlet {

    private final UserService userService;

    public SignOutServlet(UserService service) {
        this.userService = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String sessionId = req.getSession().getId();
        UserEntity profile = userService.getUserBySessionId(sessionId);

        if (profile == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            userService.deleteSession(sessionId);

            resp.sendRedirect("login.html");
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
