package org.academiadecodigo.whiledlings.teachin.persistence.dao.jpa;

import org.academiadecodigo.whiledlings.teachin.persistence.dao.UserDao;
import org.academiadecodigo.whiledlings.teachin.persistence.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserDao extends GenericJpaDao<User> implements UserDao {

    public JpaUserDao() {
        super(User.class);
    }

}
