package server.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import server.DAO.UserDAO;
import server.entities.UserEntity;


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

    @Override
    public Long register(String name, String login, String password) {
        Long id = -1L;
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserDAO dao = new UserDAO(session);

            id = dao.insertUser(name, login, password);

            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            return id;
        }
    }

    public UserEntity loginFromSession() {
        return null;
    }

    public void logout() {

    }
}
