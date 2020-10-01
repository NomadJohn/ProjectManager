package DTO;

public class FunctionDTO {
    private int id;
    private String name;
    private int projectId;
    private String isCompleted;

    public FunctionDTO(String name, int projectId) {
        this.name = name;
        this.projectId = projectId;
    }

    public FunctionDTO(int id, String name, int projectId, String isCompleted) {
        this.id = id;
        this.name = name;
        this.projectId = projectId;
        this.isCompleted = isCompleted;
    }

    public String getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(String isCompleted) {
        this.isCompleted = isCompleted;
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
