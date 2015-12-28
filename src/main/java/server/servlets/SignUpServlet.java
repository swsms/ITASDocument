package server.servlets;

import server.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SignUpServlet extends HttpServlet {
    private final UserService userService;

    public SignUpServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        long userId = userService.register(name, login, pass);

        if (userId == -1) {
            resp.getWriter().println("Error");
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            resp.getWriter().println("User is created");
            resp.getWriter().println(userId);
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }
    }
}
