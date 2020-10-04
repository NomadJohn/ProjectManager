package views.Manager.Project;

import DAO.ProjectDAO;
import DAO.StudentDAO;
import DTO.ProjectDTO;
import DTO.StudentDTO;
import uitls.DBManager;
import uitls.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjectListFrame extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private JTextField tProjectId;
    private JTextField tId;
    private JTextField tName;
    private JTextField tStart;
    ArrayList<HashMap<String, String>> result;

    public ProjectListFrame(JTabbedPane parent) {
        parent.addChangeListener(changeEvent -> {
            if (parent.getSelectedIndex() == 1) reloadProjects();
        });
        setLayout(null);
        model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"项目编号", "项目名称", "开始时间", "是否完成"}
        );
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setBounds(14, 39, 422, 188);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(14, 59, 850, 357);
        add(scrollPane);

        JButton bUpdate = new JButton("提交修改");
        bUpdate.addActionListener(arg0 -> {
        });
        bUpdate.setBounds(624, 429, 113, 71);
        add(bUpdate);

        JButton bDelete = new JButton("删除");
        bDelete.setBounds(751, 429, 113, 71);
        add(bDelete);


        JButton bSearch = new JButton("搜索");
        bSearch.setBounds(493, 19, 63, 27);
        add(bSearch);

        tProjectId = new JTextField();
        tProjectId.setBounds(362, 22, 117, 24);
        add(tProjectId);
        tProjectId.setColumns(10);

        JLabel label = new JLabel("项目编号");
        label.setFont(new Font("宋体", Font.PLAIN, 16));
        label.setBounds(287, 22, 72, 18);
        add(label);

        tId = new JTextField();
        tId.setEditable(false);
        tId.setBounds(224, 429, 113, 24);
        add(tId);
        tId.setColumns(10);

        tName = new JTextField();
        tName.setBounds(224, 476, 113, 24);
        add(tName);
        tName.setColumns(10);

        tStart = new JTextField();
        tStart.setEditable(false);
        tStart.setColumns(10);
        tStart.setBounds(433, 429, 137, 24);
        add(tStart);

        JComboBox tEnd = new JComboBox();
        tEnd.setModel(new DefaultComboBoxModel(new String[]{"是", "否"}));
        tEnd.setBounds(433, 476, 137, 27);
        add(tEnd);

        JLabel label_1 = new JLabel("项目编号");
        label_1.setBounds(147, 432, 63, 18);
        add(label_1);

        JLabel label_1_1 = new JLabel("项目名称");
        label_1_1.setBounds(147, 479, 63, 18);
        add(label_1_1);

        JLabel label_1_1_1 = new JLabel("是否完成");
        label_1_1_1.setBounds(356, 482, 63, 18);
        add(label_1_1_1);

        JLabel label_1_1_1_1 = new JLabel("开始时间");
        label_1_1_1_1.setBounds(356, 429, 63, 18);
        add(label_1_1_1_1);

        loadProjects();
        model.addTableModelListener(arg -> {
            System.out.println(arg);
        });
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    int selectRow = table.getSelectedRow();
                    tId.setText((String) model.getValueAt(selectRow, 0));
                    tName.setText((String) model.getValueAt(selectRow, 1));
                    tStart.setText((String) model.getValueAt(selectRow, 2));
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                int mods = mouseEvent.getModifiersEx();
                if (mods != 0 && InputEvent.BUTTON3_DOWN_MASK != 0) {
                    int row = table.getSelectedRow();
                    String projectId = result.get(row).get("project_id");
                    String projectName = result.get(row).get("project_name");
                    String projectDesc = result.get(row).get("project_desc");
                    String projectBegin = result.get(row).get("project_begin");
                    System.out.println(projectName);
                    System.out.println(projectDesc);
                    System.out.println(projectBegin);
                    JFrame frame = Utils.getInfoFrame(new ProjectDTO(Integer.parseInt(projectId), projectName, projectDesc, Timestamp.valueOf(projectBegin), null));
                    frame.setVisible(true);
                }
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        bDelete.addActionListener(arg -> {
            deleteSelectedProject();
        });
        bUpdate.addActionListener(arg -> {
            int projectId = Integer.parseInt(tId.getText());
            String projectName = tName.getText();
            boolean projectEnd = tEnd.getSelectedIndex() == 0;
            boolean ok = new ProjectDAO().update(projectId, projectName, projectEnd) > 0;
            JOptionPane.showMessageDialog(ProjectListFrame.this, ok ? "修改成功" : "修改失败");
            if (ok) {
                reloadProjects();
                tId.setText("");
                tName.setText("");
                tEnd.setSelectedIndex(0);
            }
        });
        bSearch.addActionListener(arg -> {
            if (tProjectId.getText().equals("")) {
                reloadProjects();
                return;
            }
            searchByProjectId(Integer.parseInt(tProjectId.getText()));
            tProjectId.setText("");
        });
    }

    public void deleteSelectedProject() {
        int[] rows = table.getSelectedRows();
        if (rows.length <= 0) {
            JOptionPane.showMessageDialog(ProjectListFrame.this, "未选择数据！");
            return;
        }
        StudentDAO dao = new StudentDAO();
        int count = 0;
        for (int rowIndex : rows) {
            int studentId = Integer.parseInt((String) model.getValueAt(rowIndex, 0));
            count += dao.deleteOne(studentId) ? 1 : 0;
        }
        JOptionPane.showMessageDialog(ProjectListFrame.this, count == 0 ? "删除失败" : String.format("成功删除%d条数据", count));
        reloadProjects();
    }

    public void searchByProjectId(int projectId) {
        ArrayList<HashMap<String, String>> result = new ProjectDAO().selectOne(projectId);
        if (result.size() > 0) {
            removeAllProjects();
            addRowByHashMap(result.get(0));
        } else {
            JOptionPane.showMessageDialog(ProjectListFrame.this, "未搜索到该项目");
        }
    }

    public void loadProjects() {
        String sql = "SELECT * FROM Projects";
        result = DBManager.query(sql);
        for (HashMap<String, String> current : result)
            addRowByHashMap(current);
    }

    public void addRowByHashMap(HashMap<String, String> map) {
        String projectId = map.get("project_id");
        String projectName = map.get("project_name");
        String projectBegin = map.get("project_begin");
        String projectEnd = map.get("project_end");
        model.addRow(new Object[]{projectId, projectName, projectBegin, projectEnd == null ? "未完成" : projectEnd});
    }

    public void reloadProjects() {
        removeAllProjects();
        loadProjects();
    }

    public void removeAllProjects() {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }
}
