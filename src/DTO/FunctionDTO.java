package DTO;

public class FunctionDTO {
    private int id;
    private String name;
    private int projectId;

    public FunctionDTO(String name, int projectId) {
        this.name = name;
        this.projectId = projectId;
    }

    public int getId() {
        return id;
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "FunctionDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projectId=" + projectId +
                '}';
    }
}
