package server.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import server.services.UserService;
import server.services.UserServiceImpl;
import server.servlets.SignInServlet;
import server.servlets.SignUpServlet;

/**
 * Created by Артем on 28.12.2015.
 */
public class Main {


    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());

        ServiceRegistry serviceRegistry = builder.build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = Configurations.getPostGresConfigurationLocal();

        SessionFactory sessionFactory = createSessionFactory(configuration);
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
