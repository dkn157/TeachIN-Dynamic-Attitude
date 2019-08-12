package org.academiadecodigo.whiledlings.teachin.persistence.dao.jpa;

import org.academiadecodigo.whiledlings.teachin.persistence.dao.Dao;
import org.academiadecodigo.whiledlings.teachin.persistence.model.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class GenericJpaDao<T extends Model> implements Dao<T> {

    protected Class<T> modelType;

    public GenericJpaDao(Class<T> modelType) {
        this.modelType = modelType;
    }

    @PersistenceContext
    protected EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<T> findAll() {

        CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(modelType);
        Root<T> root = criteriaQuery.from(modelType);
        return em.createQuery(criteriaQuery).getResultList();

        // Using JPA
        // return em.createQuery( "from " + modelType.getSimpleName(), modelType).getResultList();
    }

    @Override
    public T findById(Integer id) {
        return em.find(modelType, id);
    }

    @Override
    public T saveOrUpdate(T modelObject) {
        return em.merge(modelObject);
    }

    @Override
    public void delete(Integer id) {
        em.remove(em.find(modelType, id));
    }
}
