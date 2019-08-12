package org.academiadecodigo.whiledlings.teachin.persistence.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "method")
public class Method extends AbstractModel {

    private String name;
    private String description;

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "method",
            fetch = FetchType.EAGER
    )
    private Set<GroupDynamic> groupDynamics;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
