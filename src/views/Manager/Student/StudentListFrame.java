package views.Manager.Student;

import DAO.StudentDAO;
import DTO.StudentDTO;
import uitls.DBManager;
import uitls.Utils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Font;

public class StudentListFrame extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private JTextField tStudentId;
    private JTextField tId;
    private JTextField tName;
    private JTextField tAge;

    public StudentListFrame(JTabbedPane parent) {
        parent.addChangeListener(changeEvent -> {
            if (parent.getSelectedIndex() == 1) reloadStudents();
        });
        setLayout(null);
        model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"学号", "姓名", "性别", "年龄", "项目名称", "完成时间"}
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
        bUpdate.setBounds(493, 429, 113, 71);
        add(bUpdate);

        JButton bDelete = new JButton("删除");
        bDelete.setBounds(620, 429, 113, 71);
        add(bDelete);


        JButton bSearch = new JButton("搜索");
        bSearch.setBounds(493, 19, 63, 27);
        add(bSearch);

        tStudentId = new JTextField();
        tStudentId.setBounds(362, 22, 117, 24);
        add(tStudentId);
        tStudentId.setColumns(10);

        JLabel label = new JLabel("学号");
        label.setFont(new Font("宋体", Font.PLAIN, 16));
        label.setBounds(320, 28, 40, 18);
        add(label);

        tId = new JTextField();
        tId.setEditable(false);
        tId.setBounds(275, 429, 113, 24);
        add(tId);
        tId.setColumns(10);

        tName = new JTextField();
        tName.setBounds(275, 476, 113, 24);
        add(tName);
        tName.setColumns(10);

        tAge = new JTextField();
        tAge.setColumns(10);
        tAge.setBounds(433, 476, 55, 24);
        add(tAge);

        JComboBox tSex = new JComboBox();
        tSex.setModel(new DefaultComboBoxModel(new String[]{"男", "女"}));
        tSex.setBounds(433, 429, 55, 27);
        add(tSex);

        JLabel label_1 = new JLabel("学号");
        label_1.setBounds(231, 432, 30, 18);
        add(label_1);

        JLabel label_1_1 = new JLabel("姓名");
        label_1_1.setBounds(231, 479, 30, 18);
        add(label_1_1);

        JLabel label_1_1_1 = new JLabel("性别");
        label_1_1_1.setBounds(389, 432, 30, 18);
        add(label_1_1_1);

        JLabel label_1_1_1_1 = new JLabel("年龄");
        label_1_1_1_1.setBounds(389, 479, 30, 18);
        add(label_1_1_1_1);

        loadStudents();
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
//					tSex = .setText((String) model.getValueAt(selectRow, 0));
                    tAge.setText((String) model.getValueAt(selectRow, 3));
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
        bDelete.addActionListener(arg -> {
            deleteSelectedStudent();
        });
        bUpdate.addActionListener(arg -> {
            int studentId = Integer.parseInt(tId.getText());
            String studentName = tName.getText();
            String studentSex = (String) tSex.getSelectedItem();
            int studentAge = Integer.parseInt(tAge.getText());
            boolean ok = new StudentDAO().update(new StudentDTO(studentId, studentName, studentSex, studentAge, "", 0));
            JOptionPane.showMessageDialog(StudentListFrame.this, ok ? "修改成功" : "修改失败");
            if (ok) {
                reloadStudents();
                tId.setText("");
                tName.setText("");
                tSex.setSelectedIndex(0);
                tAge.setText("");
            }
        });
        bSearch.addActionListener(arg -> {
            if (tStudentId.getText().equals("")) {
                reloadStudents();
                return;
            }
            searchByStudentId(Integer.parseInt(tStudentId.getText()));
            tStudentId.setText("");
        });
    }

    public void deleteSelectedStudent() {
        int[] rows = table.getSelectedRows();
        if (rows.length <= 0) {
            JOptionPane.showMessageDialog(StudentListFrame.this, "未选择数据！");
            return;
        }
        StudentDAO dao = new StudentDAO();
        int count = 0;
        for (int rowIndex : rows) {
            int studentId = Integer.parseInt((String) model.getValueAt(rowIndex, 0));
            count += dao.deleteOne(studentId) ? 1 : 0;
        }
        JOptionPane.showMessageDialog(StudentListFrame.this, count == 0 ? "删除失败" : String.format("成功删除%d条数据", count));
        reloadStudents();
    }

    public void searchByStudentId(int studentId) {
        ArrayList<HashMap<String, String>> result = DBManager.query(String.format("SELECT Students.student_id, Students.student_name, Students.student_sex, Students.student_age , Projects.project_name, Projects.project_end FROM Students LEFT JOIN Projects ON Students.project_id = Projects.project_id WHERE Students.student_id=%d", studentId));
        if (result.size() > 0) {
            removeAllStudents();
            addRowByHashMap(result.get(0));
        } else {
            JOptionPane.showMessageDialog(StudentListFrame.this, "未搜索到该学生");
        }
    }

    public void loadStudents() {
        String sql = "SELECT Students.student_id, Students.student_name, Students.student_sex, Students.student_age , Projects.project_name, Projects.project_end FROM Students LEFT JOIN Projects ON Students.project_id = Projects.project_id";
        ArrayList<HashMap<String, String>> result = DBManager.query(sql);
        for (HashMap<String, String> current : result)
            addRowByHashMap(current);
    }

    public void addRowByHashMap(HashMap<String, String> map) {
        String studentId = map.get("student_id");
        String studentName = map.get("student_name");
        String studentSex = map.get("student_sex");
        String studentAge = map.get("student_age");
        String projectName = map.get("project_name");
        String projectEnd = map.get("project_end");
        model.addRow(new Object[]{studentId, studentName, studentSex, studentAge, projectName == null ? "无" : projectName, projectEnd == null ? "未完成" : projectEnd});


    }

    public void reloadStudents() {
        removeAllStudents();
        loadStudents();
    }

    public void removeAllStudents() {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }
}
