package server.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import server.services.UserService;
import server.services.UserServiceImpl;
import server.servlets.SignInServlet;
import server.servlets.SignUpServlet;

public class Main {

    public static void main(String[] args) throws Exception {

        Configuration configuration = Configurations.getPostGresConfigurationLocal();

        SessionFactory sessionFactory = Configurations.createSessionFactory(configuration);
        UserService service = new UserServiceImpl(sessionFactory);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignInServlet(service)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(service)), "/signup");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();

        server.join();
    }
}
