package server.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import server.entities.UserEntity;
import server.main.Configurations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Эдуард on 30.12.2015.
 */

public class UserDAOTest {
    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeClass
    public static void init() {
        Configuration configuration = Configurations.getPostGresConfigurationRemote();
        sessionFactory = Configurations.createSessionFactory(configuration);
    }

    @Before
    public void initSession() {
        session = sessionFactory.openSession();
    }

    @After
    public void closeSession() {
        session.close();
    }

    // insert into users values (1, 'user', 'valentine', 'pass')
    private final Long id = 3L;
    private final String login  = "tester2";
    private final String loginDifferentCase  = "tEsTeR2";
    private final String name  = "GRAHAM WUT";
    private final String pass  = "12345";

    @Test
    public void getUserById() throws Exception {
        UserDAO userDAO = new UserDAO(session);

        UserEntity ue = userDAO.get(id);

        assertTrue(ue != null);
        assertEquals(ue.getLogin(), login);
        assertEquals(ue.getName(), name);
    }

    @Test
    public void authUser() throws Exception {
        UserDAO userDAO = new UserDAO(session);

        UserEntity ue = userDAO.get(login, pass);

        assertTrue(ue != null);
        assertEquals(ue.getName(), name);
        assertEquals((Long)ue.getId(), id);
    }


    @Test
    public void authUserDifferentCase() throws Exception {
        UserDAO userDAO = new UserDAO(session);

        UserEntity ue = userDAO.get(loginDifferentCase, pass);

        assertTrue(ue == null);
    }


    @Test
    public void getNonExistingUserById() throws Exception {
        UserDAO userDAO = new UserDAO(session);

        Long id = -100500L;
        UserEntity ue = userDAO.get(id);

        assertTrue(ue == null);
    }

    @Test
    public void authNonExistingUser() throws Exception {
        UserDAO userDAO = new UserDAO(session);

        String login  = "nonexistinguserlogin";
        String pass  = "randompass";
        UserEntity ue = userDAO.get(login, pass);

        assertTrue(ue == null);
    }

    @Test
    public void createUser () throws Exception {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserDAO userDAO = new UserDAO(session);

            String login = "newTesterUserKLEnkdlfje";
            Long id = userDAO.insertUser(name, login, pass);

            UserEntity ue = userDAO.get(id);

            assertTrue(ue != null);
            assertEquals(ue.getLogin(), login);
            assertEquals(ue.getName(), name);

            transaction.rollback();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}