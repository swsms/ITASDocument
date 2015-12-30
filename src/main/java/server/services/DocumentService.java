package server.services;

import server.entities.DocumentEntity;

import java.util.List;

public interface DocumentService {

    DocumentEntity find(String content);

    Long add(DocumentEntity document);

    Boolean remove(Long id);

    Boolean remove(DocumentEntity document);

    List<DocumentEntity> getDocumentsByTypeName(String docTypeName);
}