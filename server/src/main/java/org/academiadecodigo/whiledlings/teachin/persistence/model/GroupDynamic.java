package org.academiadecodigo.whiledlings.teachin.persistence.model;

import javax.persistence.*;

@Entity
@Table(name = "groupdynamic")
public class GroupDynamic extends AbstractModel {

    private int minAge;
    private int maxAge;
    private String description;
    private String name;

    @ManyToOne
    private Issue issue;

    @ManyToOne
    private Method method;

    @ManyToOne
    private KeyWord keyword;

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public KeyWord getKeyword() {
        return keyword;
    }

    public void setKeyword(KeyWord keyword) {
        this.keyword = keyword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
