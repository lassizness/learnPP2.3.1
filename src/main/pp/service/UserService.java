package pp.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pp.dao.UserDao;
import pp.dao.UserDaoImpl;
import pp.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"pp.controller", "pp.service"})  // specify packages to scan
public class UserService {
     UserDao userDao= new UserDaoImpl();

    @Transactional
    public void addUser(User user) {
        userDao.saveUser(user);
    }

    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Transactional(readOnly = true)
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getUsers();
    }
}
