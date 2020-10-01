package DAO;

import uitls.DBManager;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentDAO {
    public boolean login(int StudentID, String StudentPassword) {
        ArrayList<HashMap<String, String>> result = DBManager.query(String.format("SELECT * FROM Students WHERE student_id = %d AND student_password = '%s'", StudentID, StudentPassword));
        if (result.size() > 0)
            return true;
        return false;
    }
}
