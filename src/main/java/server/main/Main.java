package server.main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import server.services.*;
import server.servlets.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Configuration configuration = Configurations.getPostGresConfigurationLocal();
        SessionFactory sessionFactory = Configurations.createSessionFactory(configuration);

        UserService userService = new UserServiceImpl(sessionFactory);
        DocumentService documService = new DocumentServiceImpl(sessionFactory);
        TypeService typeService = new TypeServiceImpl(sessionFactory);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignInServlet(userService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(userService)), "/signup");
        context.addServlet(new ServletHolder(new SignOutServlet(userService)), "/signout");

        context.addServlet(new ServletHolder(new DocumentServlet(documService)), "/documents");
        context.addServlet(new ServletHolder(new TypeServlet(typeService)), "/types");

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
