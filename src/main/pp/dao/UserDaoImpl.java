package pp.dao;

import pp.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pp.util.HiberCon;

import java.util.List;

public class UserDaoImpl implements UserDao {
   SessionFactory sessionFactory = HiberCon.getSessionFactory();

    public UserDaoImpl() {

    }

    public List<User> getUsers() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createQuery("from UsersEntity", User.class).list();
        session.close();
        return users;
    }

    public User getUser(long id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public void saveUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void deleteUser(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        if (user != null) {
            session.delete(user);
        }
        transaction.commit();
        session.close();
    }
}