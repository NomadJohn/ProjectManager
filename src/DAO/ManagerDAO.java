package DAO;

import uitls.DBManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ManagerDAO {
    public boolean login(String ManagerName, String ManagerPassword) {
        ArrayList<HashMap<String, String>> result = DBManager.query(String.format("SELECT * FROM managers WHERE manager_name = '%s' AND manager_password = '%s'", ManagerName, ManagerPassword));
        if (result.size() > 0)
            return true;
        return false;
    }
}
