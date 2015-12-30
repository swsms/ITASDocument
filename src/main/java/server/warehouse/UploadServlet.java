package server.warehouse;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = -4925536353994142598L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Client uploadClient = Client.create();
		WebResource uploadResource = uploadClient.resource(WarehouseConfig
				.get().apiRoot());

		File fileToUpload = null;
		try {
			fileToUpload = file(req);
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).severe(e.getMessage());
		}

		FormDataMultiPart multiPart = new FormDataMultiPart();
		if (fileToUpload != null)
			multiPart.bodyPart(new FileDataBodyPart("file", fileToUpload,
					MediaType.APPLICATION_OCTET_STREAM_TYPE));

		ClientResponse uploadResponse = uploadResource.type(
				MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class,
				multiPart);
		if (fileToUpload != null)
			fileToUpload.delete();
		if (uploadResponse.getStatus() == Response.Status.OK.getStatusCode()) {
			resp.setContentType("text/plain");
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().println(uploadResponse.getEntity(String.class));
			Logger.getLogger(
					"Warehouse upload response : " + this.getClass().getName())
					.info(uploadResponse.toString());
		} else
			resp.setStatus(uploadResponse.getStatus());
	}

	private File file(HttpServletRequest req) throws FileUploadException,
			Exception {
		if (!ServletFileUpload.isMultipartContent(req))
			return null;
		ServletFileUpload uploadHandler = new ServletFileUpload(
				new DiskFileItemFactory());
		for (FileItem fi : uploadHandler.parseRequest(req))
			if (!fi.isFormField()) {
				File file = new File(System.getProperty("java.io.tmpdir")
						+ File.separator + prepareFileName(fi.getName()));
				fi.write(file);
				return file;
			}
		return null;
	}

	// Страхуемся для некоторых браузеров, которые передают в имени полный путь
	// к файлу
	private String prepareFileName(String name) {
		int lastSeparatorIdx = name.lastIndexOf(File.separator);
		if (lastSeparatorIdx == -1)
			return name;
		return name.substring(lastSeparatorIdx + 1);
	}
}
