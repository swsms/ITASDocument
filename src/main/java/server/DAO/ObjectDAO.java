package server.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import server.entities.ObjectEntity;
import server.entities.UserEntity;

import java.util.Date;

public class ObjectDAO {

    private Session session;

    public ObjectDAO(Session session) {
        this.session = session;
    }

    public ObjectEntity get(long id) throws HibernateException {
        return (ObjectEntity) session.get(ObjectEntity.class, id);
    }

    public long insertObject(String name, Date dateCreated, UserEntity creater) throws HibernateException {
        ObjectEntity objectEntity = new ObjectEntity(name, dateCreated, creater);
        return (Long) session.save(objectEntity);
    }
}
