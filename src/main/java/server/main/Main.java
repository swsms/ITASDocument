package server.main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
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

        Configuration configuration = Configurations.getPostGresConfigurationRemote();

        SessionFactory sessionFactory = Configurations.createSessionFactory(configuration);
        UserService service = new UserServiceImpl(sessionFactory);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignInServlet(service)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(service)), "/signup");



        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("frontend");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});



        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();

        server.join();
    }
}
