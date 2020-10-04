package views.Student.Project;

import DAO.FunctionDAO;
import DAO.ProjectDAO;
import DTO.FunctionDTO;
import DTO.ProjectDTO;
import uitls.Utils;
import views.Student.StudentFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.swing.table.DefaultTableModel;

/*
数据库字段：
    项目名称：阴间管理系统
    项目功能：功能1, 功能2, 功能3, 功能4...（或添加多个列表框
    项目描述：大牛项目
    开始时间：
    完成时间：
    是否完成：0，1
表单：
    项目名称：
    项目功能：
    项目描述：
 */
public class ProjectCreateFrame extends JPanel {
    private JTextField tProjectName;
    private JTextField tProjectFunction;
    private JTable table;

    /**
     * Create the panel.
     */
    public ProjectCreateFrame() {
        setLayout(null);

        JLabel lbProjectName = new JLabel("\u9879\u76EE\u540D\u79F0");
        lbProjectName.setBounds(14, 13, 72, 18);
        add(lbProjectName);

        tProjectName = new JTextField();
        tProjectName.setBounds(96, 10, 315, 24);
        add(tProjectName);
        tProjectName.setColumns(10);

        JLabel lbProjectFunction = new JLabel("\u9879\u76EE\u529F\u80FD");
        lbProjectFunction.setBounds(441, 13, 63, 18);
        add(lbProjectFunction);

        JLabel lbProjectDesc = new JLabel("\u9879\u76EE\u63CF\u8FF0");
        lbProjectDesc.setBounds(14, 44, 72, 18);
        add(lbProjectDesc);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(96, 47, 319, 277);
        add(scrollPane);

        JTextArea tProjectDesc = new JTextArea();
        tProjectDesc.setLineWrap(true);
        scrollPane.setViewportView(tProjectDesc);

        tProjectFunction = new JTextField();
        tProjectFunction.setBounds(507, 10, 256, 24);
        add(tProjectFunction);
        tProjectFunction.setColumns(10);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(441, 84, 322, 240);
        add(scrollPane_1);
        
        table = new JTable();
        scrollPane_1.setViewportView(table);
        DefaultTableModel model = new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                    "项目功能"
            }
        );
        table.setModel(model);

        JButton addFunctionBtn = new JButton("\u6DFB\u52A0");
        addFunctionBtn.addActionListener(arg0 -> {
            String functionName = tProjectFunction.getText();
            if (!functionName.equals("")) {
                Object[] obj = {functionName};
                model.addRow(obj);
                tProjectFunction.setText(null);
            }
        });
        addFunctionBtn.setBounds(507, 46, 98, 27);
        add(addFunctionBtn);

        JButton submitBtn = new JButton("\u521B\u5EFA\u9879\u76EE");
        submitBtn.setFont(new Font("宋体", Font.PLAIN, 18));
        submitBtn.addActionListener(e -> {
            System.out.println(tProjectDesc.getText());
            int project_id = new ProjectDAO().insert(new ProjectDTO(tProjectName.getText(), tProjectDesc.getText(), Timestamp.valueOf(LocalDateTime.now())));
            if (project_id <= 0) {
                JOptionPane.showMessageDialog(ProjectCreateFrame.this, "创建项目失败");
                return ;
            }

            int functionCount = model.getRowCount();
            FunctionDTO[] functions = new FunctionDTO[functionCount];
            for (int i = 0; i < functionCount; i++) {
                functions[i] = new FunctionDTO((String)model.getValueAt(i, 0), project_id);
            }

            if (!new FunctionDAO().insertMore(functions)) {
                JOptionPane.showMessageDialog(ProjectCreateFrame.this, "插入功能失败");
                return;
            }

            JOptionPane.showMessageDialog(ProjectCreateFrame.this, "创建项目成功");
            tProjectName.setText(null);
            tProjectDesc.setText(null);
            tProjectFunction.setText(null);
            for (int i = functionCount-1; i >=0 ; i--) {
                model.removeRow(i);
            }

            if (Utils.isManager())
                return;
            int studentId = Utils.GetUserInfo().getId();
            StudentFrame.joinProject(studentId, project_id, ProjectCreateFrame.this);
        });
        submitBtn.setBounds(351, 351, 113, 27);
        add(submitBtn);
        
        JButton delFunctionBtn = new JButton("删除");
        delFunctionBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
                int[] indexs = table.getSelectedRows();
                for (int i = indexs.length-1; i>=0; i--)
                    model.removeRow(indexs[i]);
        	}
        });
        delFunctionBtn.setBounds(665, 46, 98, 27);
        add(delFunctionBtn);
    }
}
