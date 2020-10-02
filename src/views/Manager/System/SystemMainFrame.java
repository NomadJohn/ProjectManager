package views.Manager.System;

import DAO.ManagerDAO;
import DTO.StudentDTO;
import uitls.Utils;
import views.Login.UserLoginFrame;

import javax.swing.*;
import java.awt.GridLayout;

public class SystemMainFrame extends JPanel {
    private JPasswordField tCurrentPassword;
    private JPasswordField tNewPassword;
    private JPasswordField tNewPasswordConfirm;
    private JTextField tManagerName;
    private StudentDTO user;

    public SystemMainFrame() {
        setLayout(new GridLayout(1, 0, 0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        add(tabbedPane);

        JPanel ManagerInfo = new JPanel();
        tabbedPane.addTab("总览", null, ManagerInfo, null);
        ManagerInfo.setLayout(null);

        JLabel label_2 = new JLabel("编号");
        label_2.setBounds(59, 13, 30, 18);
        ManagerInfo.add(label_2);

        JLabel label_2_1_1 = new JLabel("");
        label_2_1_1.setBounds(59, 75, 30, 18);
        ManagerInfo.add(label_2_1_1);

        JLabel label_2_1_1_1 = new JLabel("总学生计数");
        label_2_1_1_1.setBounds(59, 106, 75, 18);
        ManagerInfo.add(label_2_1_1_1);

        JLabel label_2_1_1_1_1 = new JLabel("总项目计数");
        label_2_1_1_1_1.setBounds(59, 151, 82, 18);
        ManagerInfo.add(label_2_1_1_1_1);

        JLabel lbCountStudent = new JLabel("0");
        lbCountStudent.setBounds(150, 106, 72, 18);
        ManagerInfo.add(lbCountStudent);

        JLabel lbProjectCount = new JLabel("0");
        lbProjectCount.setBounds(150, 151, 72, 18);
        ManagerInfo.add(lbProjectCount);

        JLabel lbManagerId = new JLabel("*");
        lbManagerId.setBounds(113, 13, 152, 18);
        ManagerInfo.add(lbManagerId);

        JLabel label_2_1 = new JLabel("账号");
        label_2_1.setBounds(59, 44, 30, 18);
        ManagerInfo.add(label_2_1);

        JLabel lbManagerName = new JLabel("*");
        lbManagerName.setBounds(113, 44, 152, 18);
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
        lbCountStudent.setText("123");
        lbProjectCount.setText("442");

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
                    logout();
                }
                return;
            }
            JOptionPane.showMessageDialog(SystemMainFrame.this, tip);
        });
    }

    public void logout() {
        Utils.GetManagerFrame().dispose();
        new UserLoginFrame().setVisible(true);
    }


}
