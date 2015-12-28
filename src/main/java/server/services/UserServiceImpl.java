package server.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import server.DAO.UserDAO;
import server.entities.UserEntity;

/**
 * Created by Артем on 28.12.2015.
 */
public class UserServiceImpl implements UserService {
    private final SessionFactory sessionFactory;

    public UserServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserEntity login(String login, String password) {
        UserEntity userEntity = null;
        try {
            Session session = sessionFactory.openSession();
            UserDAO dao = new UserDAO(session);

            userEntity = dao.get(login, password);

            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            return userEntity;
        }
    }

    public UserEntity loginFromSession() {
        return null;
    }

    public void logout() {

    }
}
