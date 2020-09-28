package DTO;

import java.time.LocalTime;

public class ProjectDTO {
    private int id;
    private String name;
    private String desc;
    private LocalTime begin;
    private LocalTime end;

    public ProjectDTO(int id, String name, String desc, LocalTime begin, LocalTime end) {
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

    public LocalTime getBegin() {
        return begin;
    }

    public void setBegin(LocalTime begin) {
        this.begin = begin;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
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
