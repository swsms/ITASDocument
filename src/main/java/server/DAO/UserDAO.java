package server.DAO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.entities.UserEntity;

/**
 * Created by ����� on 28.12.2015.
 */
public class UserDAO {

    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public UserEntity get(long id) throws HibernateException {
        return (UserEntity) session.get(UserEntity.class, id);
    }

    public UserEntity get(String login, String password) throws HibernateException {
        Criteria criteria = session.createCriteria(UserEntity.class);
        return ((UserEntity) criteria
                .add(Restrictions.eq("login", login))
                .add(Restrictions.eq("password", password))
                .uniqueResult());
    }

    public long insertUser(UserEntity user) throws HibernateException {
        return (Long) session.save(user);
    }

}
