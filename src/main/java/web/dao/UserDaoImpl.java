package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.entity.UserEntity;
import web.model.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        List<UserEntity> usersEntity = session.createQuery("from UserEntity", UserEntity.class).list();
        for (UserEntity userEntity : usersEntity) {
            users.add(new User(userEntity.getId(), userEntity.getName(), userEntity.getAge()));
        }
        return users;
    }

    @Override
    public User getUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        UserEntity userEntity = session.get(UserEntity.class, id);
        User user = null;
        if (userEntity != null) {
            user = new User(userEntity.getId(), userEntity.getName(), userEntity.getAge());
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        UserEntity userEntity = new UserEntity(user.getName(), user.getAge());
        session.save(userEntity);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        UserEntity userEntity = session.get(UserEntity.class, user.getId());
        System.out.println("updateUser"+user.getId());
        if (userEntity != null) {
            userEntity.setName(user.getName());
            userEntity.setAge(user.getAge());
            session.saveOrUpdate(userEntity);
        }
    }

    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        UserEntity userEntity = session.get(UserEntity.class, id);
        if (userEntity != null) {
            session.delete(userEntity);
        }
    }
}
