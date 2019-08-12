package org.academiadecodigo.whiledlings.teachin.services;

import org.academiadecodigo.whiledlings.teachin.persistence.dao.UserDao;
import org.academiadecodigo.whiledlings.teachin.persistence.dao.jpa.JpaUserDao;
import org.academiadecodigo.whiledlings.teachin.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private JpaUserDao userDao;

    @Autowired
    public void setUserDao(JpaUserDao userDao) {
        this.userDao = userDao;
    }

    public User get(Integer id) {
        return userDao.findById(id);
    }

    @Transactional
    public User save(User user) {
        return userDao.saveOrUpdate(user);
    }

    @Transactional
    public void delete(Integer id) {

        User user = userDao.findById(id);

        if (user == null) {
            //throw new CustomerNotFoundException();
        }

        userDao.delete(id);
    }

    public List<User> list() {
        return userDao.findAll();
    }
}
