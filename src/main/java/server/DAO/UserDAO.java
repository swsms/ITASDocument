package server.DAO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import server.PasswordEncoder;
import server.entities.UserEntity;

public class UserDAO {

	private Session session;

	public UserDAO(Session session) {
		this.session = session;
	}

	public UserEntity get(long id) throws HibernateException {
		return (UserEntity) session.get(UserEntity.class, id);
	}

	public UserEntity get(String login, String password)
			throws HibernateException {
		Criteria criteria = session.createCriteria(UserEntity.class);
		return ((UserEntity) criteria
				.add(Restrictions.eq("login", login))
				.add(Restrictions.eq("password",
						PasswordEncoder.encode(password))).uniqueResult());
	}

	public long insertUser(String name, String login, String password)
			throws HibernateException {
		UserEntity userEntity = new UserEntity(name, login,
				PasswordEncoder.encode(password));
		return (Long) session.save(userEntity);
	}
}
