package server.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import server.entities.DocumentEntity;
import server.entities.ObjectEntity;
import server.main.Configurations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ObjectDAOTest {
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

    @Test
    public void getExistingObject() throws Exception {
        ObjectDAO objectDAO = new ObjectDAO(session);

        Long id = 1L;
        ObjectEntity objectEntity = objectDAO.get(id);
        assertEquals(id, objectEntity.getId());
    }

    @Test
    public void getNonExistingObject() throws Exception {
        ObjectDAO objectDAO = new ObjectDAO(session);

        Long id = -100L;
        ObjectEntity objectEntity = objectDAO.get(id);
        assertTrue(objectEntity == null);
    }

    @Test
    public void getOneDocument() throws Exception {
        ObjectDAO objectDAO = new ObjectDAO(session);

        Long id = 1L;
        DocumentEntity documentEntity = objectDAO.getOne(DocumentEntity.class, id);

        assertTrue(documentEntity != null);
        assertEquals(id, documentEntity.getId());
    }

    @Test
    public void getAllDocuments() throws Exception {
        ObjectDAO objectDAO = new ObjectDAO(session);

        List<DocumentEntity> documents = objectDAO.getAll(DocumentEntity.class);

        assertTrue(documents.size() > 0);
    }
}