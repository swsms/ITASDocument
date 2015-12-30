package server.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import server.DAO.UserDAO;
import server.entities.UserEntity;

import java.util.HashMap;
import java.util.Map;


public class UserServiceImpl implements UserService {
    private final SessionFactory sessionFactory;
    private final Map<String, UserEntity> sessionIdToProfile;


    public UserServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.sessionIdToProfile = new HashMap<>();
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

    @Override
    public UserEntity getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    @Override
    public void addSession(String sessionId, UserEntity profile) {
        sessionIdToProfile.put(sessionId, profile);
    }

    @Override
    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }

}
