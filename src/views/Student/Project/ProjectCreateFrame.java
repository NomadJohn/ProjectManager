package views.Student.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        lbProjectFunction.setBounds(441, 13, 72, 18);
        add(lbProjectFunction);

        JLabel lbProjectDesc = new JLabel("\u9879\u76EE\u63CF\u8FF0");
        lbProjectDesc.setBounds(14, 44, 72, 18);
        add(lbProjectDesc);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(96, 47, 319, 252);
        add(scrollPane);

        JTextArea tProjectDesc = new JTextArea();
        scrollPane.setViewportView(tProjectDesc);

        tProjectFunction = new JTextField();
        tProjectFunction.setBounds(523, 10, 147, 24);
        add(tProjectFunction);
        tProjectFunction.setColumns(10);

        JButton addFunctionBtn = new JButton("\u6DFB\u52A0");
        addFunctionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        addFunctionBtn.setBounds(684, 9, 72, 27);
        add(addFunctionBtn);

        JScrollPane spFunctionList = new JScrollPane();
//        spFunctionList.setLayout(new FlowLayout());
        spFunctionList.setBounds(429, 49, 335, 250);
        add(spFunctionList);

        JButton submitBtn = new JButton("\u521B\u5EFA\u9879\u76EE");
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        submitBtn.setBounds(329, 357, 113, 27);
        add(submitBtn);
    }
}
