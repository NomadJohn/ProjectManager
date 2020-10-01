package DAO;

import DTO.FunctionDTO;
import DTO.ProjectDTO;
import uitls.DBManager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class FunctionDAO {
    public boolean insertMore(FunctionDTO[] functions) {
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
        ArrayList<HashMap<String, String>> result = DBManager.query(String.format("SELECT * From Functions "));
        for (int i = 0; i < result.size(); i++) {
            int pid = Integer.parseInt(result.get(i).get("project_id"));
            int fid = Integer.parseInt(result.get(i).get("function_id"));
            System.out.println(result.get(i).get("function_id"));
            String fName = result.get(i).get("function_name");
            functions.add(new FunctionDTO(fid, fName, pid));
        }
        return functions;
    }

    public String queryByProjectIdToString(int projectId) {
        String str = "";
        ArrayList<FunctionDTO> functions = queryByProjectId(projectId);
        for (FunctionDTO f:functions) {
            str += f.getName() + "、";
        }
        return str;
    }
}
