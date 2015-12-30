package server.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import server.DAO.ObjectDAO;
import server.entities.DocumentEntity;
import server.entities.DocumentTypeEntity;
import server.warehouse.ContentSearch;

public class DocumentServiceImpl implements DocumentService {

	private final SessionFactory sessionFactory;

	public DocumentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public DocumentEntity find(String content) {
		return null;
	}

	@Override
	public Long add(DocumentEntity document) {
		return null;
	}

	@Override
	public Boolean remove(Long id) {
		return null;
	}

	@Override
	public Boolean remove(DocumentEntity document) {
		return null;
	}

	@Override
	public List<DocumentEntity> getDocumentsByTypeName(String docTypeName) {
		List<DocumentEntity> documents = new ArrayList<>();
		try {
			Session session = sessionFactory.openSession();
			ObjectDAO dao = new ObjectDAO(session);

			DocumentTypeEntity type = dao.getOne(DocumentTypeEntity.class,
					docTypeName);
			documents = dao.getDocumentsByDocumentType(type);

			session.close();
		} catch (HibernateException e) {
			Logger.getLogger(this.getClass().getName()).severe(e.getMessage());
		}
		return documents;
	}

	@Override
	public List<DocumentEntity> getDocumentsByTypeNameAndContent(
			String docTypeName, String content) throws IOException {

		List<DocumentEntity> documents = getDocumentsByTypeName(docTypeName);

		boolean needToFilterByContent = !(content == null || content.trim()
				.isEmpty());
		if (needToFilterByContent) {
			Set<String> searchDocsIdents = new ContentSearch(content)
					.findIdents();
			Iterator<DocumentEntity> it = documents.iterator();
			while (it.hasNext()) {
				DocumentEntity doc = it.next();
				if (!searchDocsIdents.contains(doc.getIdent()))
					it.remove();
			}
		}
		return documents;
	}
}
