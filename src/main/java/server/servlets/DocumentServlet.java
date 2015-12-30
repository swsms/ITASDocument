package server.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import server.entities.DocumentEntity;
import server.entities.UserEntity;
import server.services.DocumentService;
import server.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DocumentServlet extends HttpServlet {
	private static final long serialVersionUID = -8055016741883203430L;

	private final DocumentService documentService;
	private final UserService userService;

	public DocumentServlet(DocumentService documentService,
						   UserService userService) {
		this.documentService = documentService;
		this.userService = userService;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserEntity profile = userService.getUserBySessionId(req.getSession().getId());

		if (profile == null) {
			resp.setContentType("text/html;charset=utf-8");
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} else {
			resp.setContentType("text/html;charset=utf-8");
			resp.setStatus(HttpServletResponse.SC_OK);
		}
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
