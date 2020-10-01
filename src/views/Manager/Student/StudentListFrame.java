package views.Manager.Student;

import DAO.StudentDAO;
import DTO.StudentDTO;
import uitls.DBManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Font;

public class StudentListFrame extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private JTextField textField;

    public StudentListFrame() {
        setLayout(null);
        model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"学号", "姓名", "性别", "年龄", "项目名称", "完成时间"}
        );
        table = new JTable(model);
        table.setBounds(14, 39, 422, 188);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(14, 59, 850, 357);
        add(scrollPane);

        JButton btnNewButton = new JButton("修改");
        btnNewButton.addActionListener(arg0 -> {
        });
        btnNewButton.setBounds(655, 429, 113, 27);
        add(btnNewButton);

        JButton bDelete = new JButton("删除");
        bDelete.setBounds(655, 469, 113, 27);
        add(bDelete);


        JButton bSearch = new JButton("搜索");
        bSearch.setBounds(493, 19, 113, 27);
        add(bSearch);

        textField = new JTextField();
        textField.setBounds(362, 22, 117, 24);
        add(textField);
        textField.setColumns(10);

        JLabel label = new JLabel("学号");
        label.setFont(new Font("宋体", Font.PLAIN, 16));
        label.setBounds(319, 28, 40, 18);
        add(label);

        loadStudents();
        model.addTableModelListener(arg -> {
            System.out.println(arg);
        });
        bDelete.addActionListener(arg -> {
            deleteSelectedStudent();
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
    }

    public void loadStudents() {
        String sql = "SELECT Students.student_id, Students.student_name, Students.student_sex, Students.student_age , Projects.project_name, Projects.project_end FROM Students LEFT JOIN Projects ON Students.project_id = Projects.project_id";
        ArrayList<HashMap<String, String>> result = DBManager.query(sql);
        for (HashMap<String, String> current : result) {
            String studentId = current.get("student_id");
            String studentName = current.get("student_name");
            String studentSex = current.get("student_sex");
            String studentAge = current.get("student_age");
            String projectName = current.get("project_name");
            String projectEnd = current.get("project_end");
            model.addRow(new Object[]{studentId, studentName, studentSex, studentAge, projectName == null ? "无" : projectName, projectEnd == null ? "未完成" : projectEnd});
        }
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
