package DAO;

import DTO.ProjectDTO;
import uitls.DBManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Timestamp;

public class ProjectDAO {
    public int insert(ProjectDTO project) {
        return DBManager.updateCallId(String.format("INSERT INTO Projects(project_name, project_desc, project_begin) VALUES ('%s', '%s', '%s')", project.getName(), project.getDesc(), project.getBegin().toString()));
    }

    public int update(int project_id, String project_name, boolean end) {
        String sql;
        sql = String.format("UPDATE Projects SET project_name='%s', project_end=%s WHERE project_id='%s'", project_name, end ? "NOW()" : "NULL", project_id);
        return DBManager.update(sql);
    }

    public ArrayList<ProjectDTO> queryAll() {
        ArrayList<ProjectDTO> projects = new ArrayList<ProjectDTO>();
        ArrayList<HashMap<String, String>> result = DBManager.query(String.format("SELECT * From Projects "));
        return getProjectDTOS(projects, result);
    }

    public ArrayList<ProjectDTO> queryByKeyword(String keyword) {
        String sql = "SELECT * From Projects WHERE project_name like" + "'%" + keyword + "%'";
        ArrayList<ProjectDTO> projects = new ArrayList<ProjectDTO>();
        ArrayList<HashMap<String, String>> result = DBManager.query(sql);
        return getProjectDTOS(projects, result);
    }

    private ArrayList<ProjectDTO> getProjectDTOS(ArrayList<ProjectDTO> projects, ArrayList<HashMap<String, String>> result) {
        for (int i = 0; i < result.size(); i++) {
            int projectId = Integer.parseInt(result.get(i).get("project_id"));
            String projectName = result.get(i).get("project_name");
            String projectDesc = result.get(i).get("project_desc");
            Timestamp projectBegin = Timestamp.valueOf(result.get(i).get("project_begin"));
            String endStr = result.get(i).get("project_end");
            Timestamp projectEnd = endStr == null ? null : Timestamp.valueOf(endStr);
            projects.add(new ProjectDTO(projectId, projectName, projectDesc, projectBegin, projectEnd));
        }
        return projects;
    }

    public ArrayList<HashMap<String, String>> selectAll() {
        ArrayList<HashMap<String, String>> result = DBManager.query(String.format("SELECT * FROM Projects"));
        return result;
    }

    public ArrayList<HashMap<String, String>> selectOne(int projectId) {
        ArrayList<HashMap<String, String>> result = DBManager.query(String.format("SELECT * FROM Projects WHERE Project_id=" + projectId));
        return result;
    }

    public String getProjectNameById(int projectId) {
        String sql = String.format("SELECT project_name From Projects WHERE project_id = %d;", projectId);
        ArrayList<HashMap<String, String>> result = DBManager.query(sql);
        if (result.size() == 0)
            return "";
        return result.get(0).get("project_name");
    }

    public static void main(String[] args) {
        new ProjectDAO().getProjectNameById(16);
    }
}
