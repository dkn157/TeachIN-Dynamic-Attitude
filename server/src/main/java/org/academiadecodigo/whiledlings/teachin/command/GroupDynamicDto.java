package org.academiadecodigo.whiledlings.teachin.command;

public class GroupDynamicDto {

    private Integer id;
    private Integer minAge;
    private Integer maxAge;
    private String description;
    private String name;
    private Integer issue;

    public void setIssue(Integer issueDto) {
        this.issue = issueDto;
    }

    public Integer getIssue() {
        return issue;
    }


    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GroupDynamicDto{" +
                "id=" + id +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", issue=" + issue +
                '}';
    }
}
