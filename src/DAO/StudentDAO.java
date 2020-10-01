package DAO;

import DTO.StudentDTO;
import uitls.DBManager;
import views.Manager.ManagerFrame;
import views.Student.StudentFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentDAO implements Loginable {
    public Component GetFrame(StudentDTO user) {
        return new StudentFrame(user);
    }

    public StudentDTO login(int StudentID, String StudentPassword) {
        StudentDTO student = null;
        ArrayList<HashMap<String, String>> result = DBManager.query(String.format("SELECT * FROM Students WHERE student_id = %d AND student_password = '%s'", StudentID, StudentPassword));
        if (result.size() > 0)
            student = new StudentDTO(StudentID, result.get(0).get("student_name"), result.get(0).get("student_sex"),Integer.parseInt(result.get(0).get("student_age")), result.get(0).get("student_password"));
        return student;
    }
}
