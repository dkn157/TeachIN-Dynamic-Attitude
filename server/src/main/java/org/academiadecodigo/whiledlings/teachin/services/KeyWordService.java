package org.academiadecodigo.whiledlings.teachin.services;

import org.academiadecodigo.whiledlings.teachin.persistence.dao.jpa.JpaKeyWordDao;
import org.academiadecodigo.whiledlings.teachin.persistence.model.KeyWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KeyWordService {

    private JpaKeyWordDao keyWordDao;

    @Autowired
    public void setKeyWordDao(JpaKeyWordDao keyWordDao) {
        this.keyWordDao = keyWordDao;
    }

    public KeyWord get(Integer id) {
        return keyWordDao.findById(id);
    }

    @Transactional
    public KeyWord save(KeyWord keyWord) {
        return keyWordDao.saveOrUpdate(keyWord);
    }

    @Transactional
    public void delete(Integer id) {
        KeyWord keyWord = keyWordDao.findById(id);

        if (keyWord == null) {
            //throw new CustomerNotFoundException();
        }

        //if (!user.getGroupDynamics().isEmpty()) {
        //   throw new AssociationExistsException();
        //}

        keyWordDao.delete(id);
    }

    public List<KeyWord> list() {
        return keyWordDao.findAll();
    }
}
