package org.academiadecodigo.whiledlings.teachin.persistence.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "issue")
public class Issue extends AbstractModel {

    private String tittle;

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "issue",
            fetch = FetchType.EAGER
    )
    private Set<GroupDynamic> groupDynamics;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public Set<GroupDynamic> getGroupDynamics() {
        return groupDynamics;
    }

    public void setGroupDynamics(Set<GroupDynamic> groupDynamics) {
        this.groupDynamics = groupDynamics;
    }

    public void addGroupDynamic(GroupDynamic groupDynamic) {
        groupDynamics.add(groupDynamic);
        groupDynamic.setIssue(this);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "tittle='" + tittle + '\'' +
                ", groupDynamics=" + groupDynamics +
                '}';
    }
}
