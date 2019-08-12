package org.academiadecodigo.whiledlings.teachin.persistence.dao;

import org.academiadecodigo.whiledlings.teachin.persistence.model.Model;

import java.util.List;

public interface Dao<T extends Model> {

    List<T> findAll();
    T findById(Integer id);
    T saveOrUpdate(T quote);
    void delete(Integer id);

}
