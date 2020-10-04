package views.Student.Project;

import DAO.FunctionDAO;
import DAO.ProjectDAO;
import DAO.StudentDAO;
import DTO.ProjectDTO;
import uitls.DBManager;
import uitls.Utils;
import views.Student.StudentFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/*
1、显示所有可加入的项目
2、选择一列，点击加入
3、加入系统，需要组长同意

已有申请，直接显示已申请加入xxx项目，不允许操作。
 */
public class ProjectJoinFrame extends JPanel {
    private JTable tProjectList;
    private JTextField tSearch;
    DefaultTableModel model;
    ArrayList<ProjectDTO> projects;
    ProjectDAO ProDAO = new ProjectDAO();
    /**
     * Create the panel.
     */
    public ProjectJoinFrame(JTabbedPane jtp) {
        setLayout(null);

        JButton submitBtn = new JButton("\u52A0\u5165\u9879\u76EE");
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int studentId = Utils.GetUserInfo().getId();
                int projectId = Integer.parseInt((String)tProjectList.getValueAt(tProjectList.getSelectedRow(), 0));
                StudentFrame.joinProject(studentId, projectId, ProjectJoinFrame.this);
            }
        });
        submitBtn.setBounds(350, 382, 93, 23);
        add(submitBtn);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(14, 49, 764, 320);
        add(scrollPane);

        tProjectList = new JTable(){ public boolean isCellEditable(int row, int column) { return false; }};
        scrollPane.setViewportView(tProjectList);
        model = new DefaultTableModel(
                new Object[][] {},
                new String[] {"项目号", "项目名", "项目描述", "项目开始时间", "项目状态"}
        );

        jtp.addChangeListener(changeEvent -> {
            if (jtp.getSelectedIndex() == 1) {
                reLoadProject();
            }
        });
        tProjectList.setModel(model);
        
        tSearch = new JTextField();
        tSearch.setBounds(14, 13, 197, 24);
        add(tSearch);
        tSearch.setColumns(10);
        
        JButton searchBtn = new JButton("搜索");
        searchBtn.addActionListener(arg0 -> {
            String keyword = tSearch.getText();
            removeAll();
            loadProjectBySearch(keyword);
        });
        searchBtn.setBounds(226, 12, 87, 27);
        add(searchBtn);

        tProjectList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2) {
                    Utils.getInfoFrame(projects.get(tProjectList.getSelectedRow())).setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    public void removeAll() {
        while (model.getRowCount() > 0) model.removeRow(0);
    }

    public void loadProjectAll() {
        projects = ProDAO.queryAll();
        for (ProjectDTO p: projects) {
            Object[] obj = {p.getId(), p.getName(), p.getDesc(), p.getBegin(), p.getEnd() == null ? "未完成" : "已完成"};
            model.addRow(obj);
        }
    }

    public void loadProjectBySearch(String keyword) {
        projects = ProDAO.queryByKeyword(keyword);
        for (ProjectDTO p: projects) {
            Object[] obj = {p.getId(), p.getName(), p.getDesc(), p.getBegin(), p.getEnd() == null ? "未完成" : "已完成"};
            model.addRow(obj);
        }
    }

    public void reLoadProject() {
        removeAll();
        loadProjectAll();
    }


}
