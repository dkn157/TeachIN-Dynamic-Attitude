package org.academiadecodigo.whiledlings.teachin.persistence.dao.jpa;
import org.academiadecodigo.whiledlings.teachin.persistence.dao.KeyWordDao;
import org.academiadecodigo.whiledlings.teachin.persistence.model.KeyWord;
import org.springframework.stereotype.Repository;

@Repository
public class JpaKeyWordDao extends GenericJpaDao<KeyWord> implements KeyWordDao {

    public JpaKeyWordDao() {
        super(KeyWord.class);
    }
}
