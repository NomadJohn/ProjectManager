package DAO;

import DTO.FunctionDTO;
import DTO.ProjectDTO;
import uitls.DBManager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class FunctionDAO {
    public boolean insertMore(FunctionDTO[] functions) {
        if (functions.length <= 0)
            return true;
        String sql = "INSERT INTO Functions(project_id, function_name) VALUES";
        for (FunctionDTO f:functions) {
            sql += String.format("(%d,'%s'),", f.getProjectId(), f.getName());
        }
        sql = sql.substring(0,sql.length()-1);
        return DBManager.update(sql) > 0;
    }

    public ArrayList<FunctionDTO> queryByProjectId(int projectId) {
        String sql = String.format("SELECT * FROM Functions WHERE project_id = %d", projectId);
        ArrayList<FunctionDTO> functions = new ArrayList<FunctionDTO>();
        ArrayList<HashMap<String, String>> result = DBManager.query(sql);
        for (int i = 0; i < result.size(); i++) {
            int pid = Integer.parseInt(result.get(i).get("project_id"));
            int fid = Integer.parseInt(result.get(i).get("function_id"));
            String fName = result.get(i).get("function_name");
            String isCompleted = result.get(i).get("is_completed");
            functions.add(new FunctionDTO(fid, fName, pid, isCompleted));
        }
        return functions;
    }

    public String queryByProjectIdToString(int projectId) {
        String str = "";
        ArrayList<FunctionDTO> functions = queryByProjectId(projectId);
        for (FunctionDTO f:functions) {
            str += f.getName() + "、";
        }

        return str != "" ? str.substring(0, str.length()-1) : "无";
    }

    public boolean setIsCompletedFunction(int function_id, int isCompleted) {
        String sql = String.format("UPDATE Functions SET is_completed = '%d' WHERE function_id = %d;", isCompleted, function_id );
        return DBManager.update(sql) > 0;
    }
}
