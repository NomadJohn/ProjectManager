package views.Student.Project;

import DAO.FunctionDAO;
import DAO.ProjectDAO;
import DAO.StudentDAO;
import DTO.ProjectDTO;
import uitls.DBManager;
import uitls.Utils;

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
                if (new StudentDAO().joinProject(studentId, projectId)) {
                    JOptionPane.showMessageDialog(ProjectJoinFrame.this, "加入成功");
                    jtp.setEnabledAt(0, false);
                    jtp.setEnabledAt(1, false);
                    jtp.setEnabledAt(2, true);
                    return;
                }
                JOptionPane.showMessageDialog(ProjectJoinFrame.this, "加入失败");
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
        searchBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {

        	}
        });
        searchBtn.setBounds(226, 12, 87, 27);
        add(searchBtn);

        tProjectList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2) {
                    infoFrame(projects.get(tProjectList.getSelectedRow())).setVisible(true);
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

    public void loadProject() {
        projects = new ProjectDAO().queryAll();
        for (ProjectDTO p: projects) {
            Object[] obj = {p.getId(), p.getName(), p.getDesc(), p.getBegin(), p.getEnd() == null ? "未完成" : "已完成"};
            model.addRow(obj);
        }
    }

    public void reLoadProject() {
        removeAll();
        loadProject();
    }

    private JFrame infoFrame(ProjectDTO project) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 594, 383);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("项目名称");
        lblNewLabel.setBounds(14, 13, 72, 18);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("项目描述");
        lblNewLabel_1.setBounds(14, 44, 72, 18);
        contentPane.add(lblNewLabel_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(100, 44, 358, 114);
        contentPane.add(scrollPane);

        JTextArea tProjectDesc = new JTextArea();
        scrollPane.setViewportView(tProjectDesc);

        JLabel lblNewLabel_2 = new JLabel("项目功能");
        lblNewLabel_2.setBounds(14, 186, 72, 18);
        contentPane.add(lblNewLabel_2);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(100, 179, 358, 70);
        contentPane.add(scrollPane_1);

        JTextArea tProjectFunction = new JTextArea();
        scrollPane_1.setViewportView(tProjectFunction);

        JLabel lblNewLabel_3 = new JLabel("创建时间");
        lblNewLabel_3.setBounds(14, 278, 72, 18);
        contentPane.add(lblNewLabel_3);

        JLabel lbProjectBegin = new JLabel("");
        lbProjectBegin.setBounds(105, 278, 353, 18);
        contentPane.add(lbProjectBegin);

        JLabel lbProjectName = new JLabel("");
        lbProjectName.setBounds(105, 13, 353, 18);
        contentPane.add(lbProjectName);
        frame.setContentPane(contentPane);

        lbProjectName.setText(project.getName());
        lbProjectBegin.setText(project.getBegin().toString());
        tProjectDesc.setText(project.getDesc());
        tProjectFunction.setText(new FunctionDAO().queryByProjectIdToString(Integer.parseInt(project.getId())));

        return frame;
    }
}
