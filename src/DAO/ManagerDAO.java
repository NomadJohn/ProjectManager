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
    public Component GetFrame() {
        return new ManagerFrame();
    }

    public StudentDTO login(int managerId, String managerPassword) {
        StudentDTO manager = null;
        ArrayList<HashMap<String, String>> result = DBManager.query(String.format("SELECT * FROM Managers WHERE manager_id = %d AND manager_password = '%s'", managerId, managerPassword));
        if (result.size() > 0)
            manager = new StudentDTO(managerId, result.get(0).get("manager_name"), "", 0, result.get(0).get("manager_password"), 0);
        return manager;
    }

    public boolean setPassword(int managerId, String newPassword) {
        int res = DBManager.update(String.format("UPDATE Managers SET manager_password='%s' WHERE manager_id=%d", newPassword, managerId));
        return res > 0;
    }
}
