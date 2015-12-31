package server.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import server.services.DocumentService;
import server.services.DocumentServiceImpl;
import server.services.TypeService;
import server.services.TypeServiceImpl;
import server.services.UserService;
import server.services.UserServiceImpl;
import server.servlets.DocumentServlet;
import server.servlets.SignInServlet;
import server.servlets.SignOutServlet;
import server.servlets.SignUpServlet;
import server.servlets.TypeServlet;
import server.warehouse.DownloadServlet;
import server.warehouse.UploadServlet;

public class Main {

	public static void main(String[] args) throws Exception {

		Configuration configuration = Configurations
				.getPostGresConfigurationRemote();
		SessionFactory sessionFactory = Configurations
				.createSessionFactory(configuration);

		UserService userService = new UserServiceImpl(sessionFactory);
		DocumentService documService = new DocumentServiceImpl(sessionFactory);
		TypeService typeService = new TypeServiceImpl(sessionFactory);

		Server server = new Server(8888);
		WebAppContext context = new WebAppContext();
		context.setServer(server);
		context.setContextPath("/");
		context.addServlet(new ServletHolder(new SignInServlet(userService)),
				"/signin");
		context.addServlet(new ServletHolder(new SignUpServlet(userService)),
				"/signup");
		context.addServlet(new ServletHolder(new SignOutServlet(userService)),
				"/signout");

		context.addServlet(new ServletHolder(new DocumentServlet(documService,
				userService)), "/documents");
		context.addServlet(new ServletHolder(new TypeServlet(typeService)),
				"/types");
		;
		context.addServlet(new ServletHolder(new UploadServlet(userService,
				documService)), "/putFile");
		context.addServlet(new ServletHolder(new DownloadServlet()), "/getFile");

		context.setResourceBase("frontend");
		server.setHandler(context);
		server.start();
		server.join();
	}
}
