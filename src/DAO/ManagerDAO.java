package DAO;

import uitls.DBManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ManagerDAO {
    public boolean login(int ManagerID, String ManagerPassword) {
        ArrayList<HashMap<String, String>> result = DBManager.query(String.format("SELECT * FROM Managers WHERE manager_id = %d AND manager_password = '%s'", ManagerID, ManagerPassword));
        if (result.size() > 0)
            return true;
        return false;
    }
}
