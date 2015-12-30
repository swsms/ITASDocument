package server.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import server.DAO.ObjectDAO;
import server.entities.DocumentEntity;
import server.entities.DocumentTypeEntity;

import java.util.ArrayList;
import java.util.List;

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

            DocumentTypeEntity type = dao.getOne(DocumentTypeEntity.class, docTypeName);
            documents = dao.getDocumentsByDocumentType(type);

            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            return documents;
        }
    }
}
