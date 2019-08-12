package org.academiadecodigo.whiledlings.teachin.services;

import org.academiadecodigo.whiledlings.teachin.persistence.dao.IssueDao;
import org.academiadecodigo.whiledlings.teachin.persistence.dao.jpa.JpaIssueDao;
import org.academiadecodigo.whiledlings.teachin.persistence.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IssueService {

    private IssueDao issueDao;

    @Autowired
    public void setIssueDao(JpaIssueDao issueDao) {
        this.issueDao = issueDao;
    }

    public Issue get(Integer id) {
        return issueDao.findById(id);
    }

    @Transactional
    public Issue save(Issue issue) {
        return issueDao.saveOrUpdate(issue);
    }

    @Transactional
    public void delete(Integer id) {
        Issue issue = issueDao.findById(id);

        if (issue == null) {
            //throw new CustomerNotFoundException();
        }

        //if (!user.getGroupDynamics().isEmpty()) {
        //   throw new AssociationExistsException();
        //}

        issueDao.delete(id);
    }

    public List<Issue> list() {
        return issueDao.findAll();
    }
}
