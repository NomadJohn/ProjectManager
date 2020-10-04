package views.Manager.System;

import DAO.ManagerDAO;
import DAO.ProjectDAO;
import DAO.StudentDAO;
import DTO.StudentDTO;
import uitls.Utils;
import views.Login.UserLoginFrame;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

public class SystemMainFrame extends JPanel {
    private JPasswordField tCurrentPassword;
    private JPasswordField tNewPassword;
    private JPasswordField tNewPasswordConfirm;
    private JTextField tManagerName;
    private StudentDTO user;
    private final JButton bLogout = new JButton("退出系统");

    public SystemMainFrame() {
        setLayout(new GridLayout(0, 1, 0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        add(tabbedPane);

        JPanel ManagerInfo = new JPanel();
        tabbedPane.addTab("总览", null, ManagerInfo, null);
        ManagerInfo.setLayout(null);

        JLabel label_2 = new JLabel("编号");
        label_2.setFont(new Font("微软雅黑", Font.PLAIN, 32));
        label_2.setBounds(177, 9, 72, 55);
        ManagerInfo.add(label_2);

        JLabel label_2_1_1 = new JLabel("");
        label_2_1_1.setBounds(59, 75, 30, 18);
        ManagerInfo.add(label_2_1_1);

        JLabel lbCountStudent = new JLabel("0");
        lbCountStudent.setFont(new Font("宋体", Font.PLAIN, 32));
        lbCountStudent.setBounds(279, 172, 72, 38);
        ManagerInfo.add(lbCountStudent);

        JLabel lbCountProject = new JLabel("0");
        lbCountProject.setFont(new Font("宋体", Font.PLAIN, 32));
        lbCountProject.setBounds(279, 237, 72, 31);
        ManagerInfo.add(lbCountProject);

        JLabel lbManagerId = new JLabel("*");
        lbManagerId.setFont(new Font("微软雅黑", Font.BOLD, 32));
        lbManagerId.setBounds(279, 13, 152, 47);
        ManagerInfo.add(lbManagerId);

        JLabel lbManagerName = new JLabel("*");
        lbManagerName.setFont(new Font("微软雅黑", Font.PLAIN, 32));
        lbManagerName.setBounds(279, 75, 500, 38);
        ManagerInfo.add(lbManagerName);

        JPanel resetPassword = new JPanel();
        tabbedPane.addTab("修改密码", null, resetPassword, null);
        resetPassword.setLayout(null);

        tCurrentPassword = new JPasswordField();
        tCurrentPassword.setBounds(168, 13, 134, 24);
        resetPassword.add(tCurrentPassword);
        tCurrentPassword.setColumns(10);

        tNewPassword = new JPasswordField();
        tNewPassword.setColumns(10);
        tNewPassword.setBounds(168, 50, 134, 24);
        resetPassword.add(tNewPassword);

        tNewPasswordConfirm = new JPasswordField();
        tNewPasswordConfirm.setColumns(10);
        tNewPasswordConfirm.setBounds(168, 91, 134, 24);
        resetPassword.add(tNewPasswordConfirm);

        JButton bSubmit = new JButton("提交修改");
        bSubmit.setBounds(168, 185, 134, 44);
        resetPassword.add(bSubmit);

        JLabel label = new JLabel("当前密码");
        label.setBounds(82, 16, 72, 18);
        resetPassword.add(label);

        JLabel label_1 = new JLabel("新密码");
        label_1.setBounds(82, 53, 72, 18);
        resetPassword.add(label_1);

        JLabel label_1_1 = new JLabel("确认密码");
        label_1_1.setBounds(82, 94, 72, 18);
        resetPassword.add(label_1_1);

        tManagerName = new JTextField();
        tManagerName.setEditable(false);
        tManagerName.setColumns(10);
        tManagerName.setBounds(168, 138, 134, 24);
        resetPassword.add(tManagerName);

        JLabel label_1_1_1 = new JLabel("用户名");
        label_1_1_1.setBounds(82, 141, 72, 18);
        resetPassword.add(label_1_1_1);
        user = Utils.GetUserInfo();
        tManagerName.setText(user.getName());
        lbManagerId.setText(String.valueOf(user.getId()));
        lbManagerName.setText(user.getName());
        int countStudent, countProject;
        countStudent = new StudentDAO().selectAll().size();
        countProject = new ProjectDAO().selectAll().size();
        lbCountStudent.setText(String.valueOf(countStudent));
        lbCountProject.setText(String.valueOf(countProject));
        
        JLabel label_2_1 = new JLabel("用户");
        label_2_1.setFont(new Font("微软雅黑", Font.PLAIN, 32));
        label_2_1.setBounds(177, 69, 72, 55);
        ManagerInfo.add(label_2_1);
        
        JLabel label_2_2 = new JLabel("项目总计数");
        label_2_2.setForeground(Color.DARK_GRAY);
        label_2_2.setFont(new Font("微软雅黑", Font.PLAIN, 32));
        label_2_2.setBounds(80, 163, 169, 55);
        ManagerInfo.add(label_2_2);
        
        JLabel label_2_2_1 = new JLabel("用户总计数");
        label_2_2_1.setForeground(Color.DARK_GRAY);
        label_2_2_1.setFont(new Font("微软雅黑", Font.PLAIN, 32));
        label_2_2_1.setBounds(80, 213, 169, 55);
        ManagerInfo.add(label_2_2_1);
        bLogout.setBounds(279, 295, 162, 66);
        ManagerInfo.add(bLogout);
        bLogout.setFont(new Font("宋体", Font.PLAIN, 24));

        bSubmit.addActionListener(arg -> {
            String currentPassword = tCurrentPassword.getText();
            String newPassword = tNewPassword.getText();
            String newPasswordConfirm = tNewPasswordConfirm.getText();
            String tip;
            if (!currentPassword.equals(user.getPassword())) {
                tip = "当前密码不正确";
            } else if (!newPassword.equals(newPasswordConfirm)) {
                tip = "两次输入的密码不一致";

            } else {
                boolean ok = new ManagerDAO().setPassword(user.getId(), newPassword);
                tip = ok ? "修改成功" : "修改失败";
                JOptionPane.showMessageDialog(SystemMainFrame.this, tip);
                if (ok) {
                    Utils.Logout();
                }
                return;
            }
            JOptionPane.showMessageDialog(SystemMainFrame.this, tip);
        });
        bLogout.addActionListener(arg -> {
            Utils.Logout();
        });
    }
}
