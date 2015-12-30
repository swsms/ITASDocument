package server.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import server.entities.DocumentTypeEntity;
import server.services.TypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TypeServlet extends HttpServlet {

    private final TypeService typeService;

    public TypeServlet(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        List<DocumentTypeEntity> types = typeService.getAllDocumentTypes();

        ObjectMapper mapper = new ObjectMapper();
        String typesJson = mapper.writeValueAsString(types);

        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().println(typesJson);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setCharacterEncoding("utf-8");
    }
}
