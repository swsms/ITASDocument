package server.DAO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import server.entities.ObjectEntity;
import server.utils.GenericProcessor;

import java.util.List;

public class ObjectDAO {

    private Session session;

    public ObjectDAO(Session session) {
        this.session = session;
    }

    public ObjectEntity get(long id) throws HibernateException {
        return (ObjectEntity) session.get(ObjectEntity.class, id);
    }

    public <T extends ObjectEntity> T getOne(Class<T> clazz, long id)
            throws HibernateException {
        try {
            Object result = session.get(clazz, id);
            return GenericProcessor.makeGeneric(result, clazz);
        } catch(ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T extends ObjectEntity> List<T> getAll(Class<T> clazz)
            throws HibernateException  {
        try {
            Criteria criteria = session.createCriteria(clazz);
            List result = criteria.list();
            return GenericProcessor.makeGeneric(result, clazz);
        } catch(ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T extends ObjectEntity> long insertObject(T objectEntity)
            throws HibernateException {
        return (Long) session.save(objectEntity);
    }

    public <T extends ObjectEntity> void removeObject(T objectEntity)
            throws HibernateException {
        session.delete(objectEntity);
    }

//    public long insertObject(String name, Date dateCreated, UserEntity creator)
//            throws HibernateException {
//
//        ObjectEntity objectEntity = new ObjectEntity(name, dateCreated, creator);
//        return (Long) session.save(objectEntity);
//    }
}
