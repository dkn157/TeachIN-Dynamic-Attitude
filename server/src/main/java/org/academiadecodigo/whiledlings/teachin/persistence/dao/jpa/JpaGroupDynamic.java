package org.academiadecodigo.whiledlings.teachin.persistence.dao.jpa;

import org.academiadecodigo.whiledlings.teachin.persistence.dao.GroupDynamicDao;
import org.academiadecodigo.whiledlings.teachin.persistence.model.GroupDynamic;
import org.springframework.stereotype.Repository;

@Repository
public class JpaGroupDynamic extends GenericJpaDao<GroupDynamic> implements GroupDynamicDao {

    public JpaGroupDynamic() {
        super(GroupDynamic.class);
    }
}
