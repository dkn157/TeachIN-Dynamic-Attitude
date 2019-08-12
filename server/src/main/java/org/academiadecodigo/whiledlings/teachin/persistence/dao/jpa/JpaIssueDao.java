package org.academiadecodigo.whiledlings.teachin.persistence.dao.jpa;

import org.academiadecodigo.whiledlings.teachin.persistence.dao.IssueDao;
import org.academiadecodigo.whiledlings.teachin.persistence.model.Issue;
import org.springframework.stereotype.Repository;

@Repository
public class JpaIssueDao extends GenericJpaDao<Issue> implements IssueDao {

    public JpaIssueDao() {
        super(Issue.class);
    }
}
