package server.warehouse;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = -6055645013387902929L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Client searchClient = Client.create();
		WebResource searchResource = searchClient.resource(WarehouseConfig
				.get().apiSearchPath(req.getParameter("content")));
		ClientResponse searchResponse = searchResource
				.get(ClientResponse.class);
		if (searchResponse.getStatus() == Response.Status.OK.getStatusCode()) {
			resp.setContentType("application/json");
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().println(searchResponse.getEntity(String.class));
			Logger.getLogger(
					"Warehouse search response : " + this.getClass().getName())
					.info(searchResponse.toString());
		} else
			resp.setStatus(searchResponse.getStatus());
	}
}
