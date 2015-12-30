package server.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import server.entities.DocumentEntity;
import server.services.DocumentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

public class DocumentServlet extends HttpServlet {

    private final DocumentService documentService;

    public DocumentServlet(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        String type = req.getParameter("type");

        List<DocumentEntity> documents = documentService.getDocumentsByTypeName(type);

        ObjectMapper mapper = new ObjectMapper();
        String documentsJson = mapper.writeValueAsString(documents);

        resp.getWriter().println(documentsJson);
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
