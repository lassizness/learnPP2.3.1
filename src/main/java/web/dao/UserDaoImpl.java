package web.dao;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;
import web.entity.UserEntity;
import web.model.User;
import web.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
   SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public UserDaoImpl() {

    }

    public List<User> getUsers() {
        Session session = sessionFactory.openSession();
        List<UserEntity> usersEntity = session.createQuery("from UserEntity", UserEntity.class).list();
        session.close();
        List<User> users = new ArrayList<>();
        for(UserEntity var :usersEntity){
            users.add(new User(var.getId(),var.getName(),var.getAge()));
        }

        return users;
    }

    public User getUser(long id) {
        Session session = sessionFactory.openSession();
        UserEntity userEntity = session.get(UserEntity.class, id);
        session.close();
        User user=new User(userEntity.getName(),userEntity.getAge());
        return user;
    }

    public void saveUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            UserEntity userEntity = new UserEntity();
            userEntity.setName(user.getName());
            userEntity.setAge(user.getAge());
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
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