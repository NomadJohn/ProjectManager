package views.Login;
/*
1、学生注册功能
2、登录模式选择框（管理员，学生）
3、登陆成功，按登录方式显示Manger 或 Student
 */
import uitls.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMainFrame extends JFrame {
    public LoginMainFrame(int width, int height) {
        this.setSize(width, height);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        Utils.addWithLabel(panel, "姓名");
        Utils.addWithLabel(panel, "密码");
        JPanel submitPanel = new JPanel();
        JButton submitBtn = new JButton("登录");
        submitPanel.add(submitBtn);
        panel.add(submitPanel);
        panel.setLayout(new GridLayout(3, 1));

        this.setContentPane(panel);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    public static void main(String[] args) {
        new LoginMainFrame(400, 300).setVisible(true);
    }
}
