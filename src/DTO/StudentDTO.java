package DTO;

public class StudentDTO {
    private int id;
    private String name;
    private String sex;
    private int age;
    private String password;

    public StudentDTO(int id, String name, String sex, int age, String password) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.password = password;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}
