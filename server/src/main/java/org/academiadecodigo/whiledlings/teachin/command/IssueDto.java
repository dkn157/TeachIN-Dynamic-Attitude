package org.academiadecodigo.whiledlings.teachin.command;

public class IssueDto {

    private Integer id;
    private String tittle;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
