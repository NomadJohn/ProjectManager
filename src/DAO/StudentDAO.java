package DAO;

import DTO.StudentDTO;
import uitls.DBManager;
import views.Manager.ManagerFrame;
import views.Student.StudentFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentDAO implements Loginable {
    public Component GetFrame() {
        return new StudentFrame();
    }

    public StudentDTO login(int StudentID, String StudentPassword) {
        StudentDTO student = null;
        ArrayList<HashMap<String, String>> result = DBManager.query(String.format("SELECT * FROM Students WHERE student_id = %d AND student_password = '%s'", StudentID, StudentPassword));
        if (result.size() > 0) {
            String projectIdStr = result.get(0).get("project_id");
            int projectId = projectIdStr == null ? 0 : Integer.parseInt(projectIdStr);
                    student = new StudentDTO(StudentID, result.get(0).get("student_name"), result.get(0).get("student_sex"),Integer.parseInt(result.get(0).get("student_age")), result.get(0).get("student_password"), projectId);
        }
        return student;
    }

    public boolean register(StudentDTO stu) {
        int result = DBManager.update(String.format("INSERT INTO Students(student_id, student_name, student_sex, student_age, student_password) VALUES(%d, '%s', '%s', %d, '%s')", stu.getId(), stu.getName(), stu.getSex(), stu.getAge(), stu.getPassword()));
        return result > 0;
    }

    public boolean joinProject(int StudentId, int ProjectId) {
        String sql = String.format("UPDATE Students SET project_id = %d WHERE student_id = %d", ProjectId, StudentId);
        return DBManager.update(sql) > 0;
    }

    public boolean leaveProject(int StudentId) {
        String sql = String.format("UPDATE Students SET project_id = null WHERE student_id = %d", StudentId);
        return DBManager.update(sql) > 0;
    }

    public boolean deleteOne(int studentId) {
        int result = DBManager.update(String.format("DELETE FROM Students WHERE student_id = %s", studentId));
        return result > 0;
    }

    public boolean update(StudentDTO stu) {
        int count = DBManager.update(String.format("UPDATE Students SET " +
                "student_name='%s', student_sex='%s', student_age=%d WHERE student_id=%d", stu.getName(), stu.getSex(), stu.getAge(), stu.getId()));
        return count > 0;
    }
    public ArrayList<HashMap<String, String>>  selectAll() {
        ArrayList<HashMap<String, String>> result = DBManager.query(String.format("SELECT * FROM Students"));
        return result;
    }

    public int getStuProjectId(int StudentID) {
        ArrayList<HashMap<String, String>> result = DBManager.query(String.format("SELECT project_id FROM Students WHERE student_id = %d", StudentID));
        if (result.size() > 0) {
            String projectIdStr = result.get(0).get("project_id");
            return projectIdStr == null ? 0 : Integer.parseInt(projectIdStr);
        }
        return 0;
    }

    public boolean setPassword(int studentId, String newPassword) {
        int res = DBManager.update(String.format("UPDATE Students SET student_password='%s' WHERE student_id=%d", newPassword, studentId));
        return res > 0;
    }
}
