package server.services;

import org.hibernate.SessionFactory;
import server.entities.DocumentEntity;

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
}
