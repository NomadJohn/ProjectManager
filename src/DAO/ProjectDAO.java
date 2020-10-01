package DAO;

import DTO.ProjectDTO;
import uitls.DBManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ProjectDAO {
    public int insert(ProjectDTO project) {
        return DBManager.updateCallId(String.format("INSERT INTO Projects(project_name, project_desc) VALUES ('%s', '%s')", project.getName(), project.getDesc()));
    }
}
