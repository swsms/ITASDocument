package server.services;

import server.entities.DocumentTypeEntity;
import java.util.List;

public interface TypeService {

    List<DocumentTypeEntity> getAllDocumentTypes();

    DocumentTypeEntity get(String name);
}
