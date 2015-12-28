package server.services;

import server.entities.DocumentEntity;

public interface DocumentService {

    DocumentEntity find(String content);

    Long add(DocumentEntity document);

    Boolean remove(Long id);

    Boolean remove(DocumentEntity document);
}