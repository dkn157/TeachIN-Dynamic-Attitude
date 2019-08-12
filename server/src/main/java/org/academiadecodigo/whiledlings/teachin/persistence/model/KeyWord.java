package org.academiadecodigo.whiledlings.teachin.persistence.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "keyword")
public class KeyWord extends AbstractModel {

    private String description;

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "keyword",
            fetch = FetchType.EAGER
    )
    private Set<GroupDynamic> groupDynamics;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<GroupDynamic> getGroupDynamics() {
        return groupDynamics;
    }

    public void setGroupDynamics(Set<GroupDynamic> groupDynamics) {
        this.groupDynamics = groupDynamics;
    }
}
