package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UserService{

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public void addUser(User user) {
        userDao.saveUser(user);
    }


    public void updateUser(User user) {
        userDao.updateUser(user);

    }


    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }


    public User getUser(long id) {
        return userDao.getUser(id);
    }


    public List<User> getAllUsers() {
        return userDao.getUsers();
    }
}
