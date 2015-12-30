package server.services;

import java.io.IOException;
import java.util.List;

import server.entities.DocumentEntity;
import server.entities.UserEntity;

public interface DocumentService {

	DocumentEntity find(String content);

	Long add(String name, String ident, String typeName, UserEntity author);

	Long add(DocumentEntity document);

	Boolean remove(Long id);

	Boolean remove(DocumentEntity document);

	List<DocumentEntity> getDocumentsByTypeName(String docTypeName);

	List<DocumentEntity> getDocumentsByTypeNameAndContent(String docTypeName,
			String content) throws IOException;
}