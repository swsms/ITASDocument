package server.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import server.DAO.ObjectDAO;
import server.entities.DocumentTypeEntity;

import java.util.ArrayList;
import java.util.List;

public class TypeServiceImpl implements TypeService {

    private final SessionFactory sessionFactory;

    public TypeServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<DocumentTypeEntity> getAllDocumentTypes() {
        List<DocumentTypeEntity> types = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            ObjectDAO dao = new ObjectDAO(session);

            types = dao.getAll(DocumentTypeEntity.class);

            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            return types;
        }
    }

    @Override
    public DocumentTypeEntity get(String name) {
        DocumentTypeEntity type = null;
        try {
            Session session = sessionFactory.openSession();
            ObjectDAO dao = new ObjectDAO(session);

            type = dao.getOne(DocumentTypeEntity.class, name);

            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            return type;
        }
    }
}
