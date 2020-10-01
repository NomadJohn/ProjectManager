package DAO;

import DTO.ManagerDTO;
import DTO.StudentDTO;
import uitls.DBManager;
import views.Manager.ManagerFrame;
import views.Student.StudentFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ManagerDAO implements Loginable {
    public Component GetFrame(StudentDTO user) {
        return new ManagerFrame(user);
    }

    public StudentDTO login(int ManagerID, String ManagerPassword) {
        StudentDTO manager = null;
        ArrayList<HashMap<String, String>> result = DBManager.query(String.format("SELECT * FROM Managers WHERE manager_id = %d AND manager_password = '%s'", ManagerID, ManagerPassword));
        if (result.size() > 0)
            manager = new StudentDTO(ManagerID,result.get(0).get("manager_name"),"",0, result.get(0).get("manager_password"));
        return manager;
    }
}
