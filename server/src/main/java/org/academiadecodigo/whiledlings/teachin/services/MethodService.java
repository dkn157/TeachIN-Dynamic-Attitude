package org.academiadecodigo.whiledlings.teachin.services;
import org.academiadecodigo.whiledlings.teachin.persistence.dao.jpa.JpaMethodDao;
import org.academiadecodigo.whiledlings.teachin.persistence.model.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MethodService {

    private JpaMethodDao methodDao;

    @Autowired
    public void setMethodDao(JpaMethodDao methodDao) {
        this.methodDao = methodDao;
    }

    public Method get(Integer id) {
        return methodDao.findById(id);
    }

    @Transactional
    public Method save(Method method) {
        return methodDao.saveOrUpdate(method);
    }

    @Transactional
    public void delete(Integer id) {
        Method issue = methodDao.findById(id);

        if (issue == null) {
            //throw new CustomerNotFoundException();
        }

        //if (!user.getAccounts().isEmpty()) {
        //   throw new AssociationExistsException();
        //}

        methodDao.delete(id);
    }

    public List<Method> list() {
        return methodDao.findAll();
    }
}
