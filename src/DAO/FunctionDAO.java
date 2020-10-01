package DAO;

import DTO.FunctionDTO;
import uitls.DBManager;

public class FunctionDAO {
    public boolean insertMore(FunctionDTO[] functions) {
        String sql = "INSERT INTO Functions(project_id, function_name) VALUES";
        for (FunctionDTO f:functions) {
            sql += String.format("(%d,'%s'),", f.getProjectId(), f.getName());
        }
        sql = sql.substring(0,sql.length()-1);
        System.out.println(sql);
        return DBManager.update(sql) > 0;
    }
}
