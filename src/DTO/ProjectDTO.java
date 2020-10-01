package DTO;

import java.sql.Timestamp;

public class ProjectDTO {
    private int id;
    private String name;
    private String desc;
    private Timestamp begin;
    private Timestamp end;

    public ProjectDTO(String name, String desc, Timestamp begin) {
        this.name = name;
        this.desc = desc;
        this.begin = begin;
    }

    public ProjectDTO(int id, String name, String desc, Timestamp begin, Timestamp end) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.begin = begin;
        this.end = end;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getBegin() {
        return begin;
    }

    public void setBegin(Timestamp begin) {
        this.begin = begin;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
