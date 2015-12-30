package server.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.entities.DocumentEntity;
import server.services.DocumentService;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DocumentServlet extends HttpServlet {
	private static final long serialVersionUID = -8055016741883203430L;

	private final DocumentService documentService;

	public DocumentServlet(DocumentService documentService) {
		this.documentService = documentService;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String type = req.getParameter("type");
		String content = req.getParameter("content");

		List<DocumentEntity> documents = documentService
				.getDocumentsByTypeNameAndContent(type, content);

		ObjectMapper mapper = new ObjectMapper();
		String documentsJson = mapper.writeValueAsString(documents);

		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().println(documentsJson);
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setCharacterEncoding("utf-8");
	}
}
