package server.servlets;

import server.entities.UserEntity;
import server.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class SignInServlet extends HttpServlet {
    private final UserService userService;

    public SignInServlet(UserService service) {
        this.userService = service;
    }

    @Override
    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        UserEntity profile = userService.login(login, pass);

        if (profile != null) {
            userService.addSession(req.getSession().getId(), profile);

            resp.sendRedirect("documents.html");
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("YEAH!");

        String sessionId = req.getSession().getId();
        UserEntity profile = userService.getUserBySessionId(sessionId);

        if (profile == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}


