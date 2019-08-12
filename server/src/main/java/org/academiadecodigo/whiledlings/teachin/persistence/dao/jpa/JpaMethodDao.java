package org.academiadecodigo.whiledlings.teachin.persistence.dao.jpa;

import org.academiadecodigo.whiledlings.teachin.persistence.dao.MethodDao;
import org.academiadecodigo.whiledlings.teachin.persistence.model.Method;
import org.springframework.stereotype.Repository;

@Repository
public class JpaMethodDao extends GenericJpaDao<Method> implements MethodDao {

    public JpaMethodDao() {
        super(Method.class);
    }
}
