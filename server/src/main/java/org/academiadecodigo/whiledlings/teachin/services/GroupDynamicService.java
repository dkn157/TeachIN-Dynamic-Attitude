package org.academiadecodigo.whiledlings.teachin.services;

import org.academiadecodigo.whiledlings.teachin.command.GroupDynamicDto;
import org.academiadecodigo.whiledlings.teachin.persistence.dao.jpa.JpaGroupDynamic;
import org.academiadecodigo.whiledlings.teachin.persistence.model.GroupDynamic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupDynamicService {

    private JpaGroupDynamic groupDynamicDao;

    @Autowired
    public void setGroupDynamicDao(JpaGroupDynamic groupDynamicDao) {
        this.groupDynamicDao = groupDynamicDao;
    }

    public GroupDynamic get(Integer id) {
        return groupDynamicDao.findById(id);
    }

    @Transactional
    public GroupDynamic save(GroupDynamic groupDynamic) {
        return groupDynamicDao.saveOrUpdate(groupDynamic);
    }

    @Transactional
    public void delete(Integer id) {
        GroupDynamic groupDynamic = groupDynamicDao.findById(id);

        if (groupDynamic == null) {
            //throw new CustomerNotFoundException();
        }

        //if (!user.getAccounts().isEmpty()) {
        //   throw new AssociationExistsException();
        //}

        groupDynamicDao.delete(id);
    }

    public List<GroupDynamic> list() {
        return groupDynamicDao.findAll();
    }

}
