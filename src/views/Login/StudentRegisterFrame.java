package views.Login;

import uitls.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRegisterFrame extends JFrame {
    public StudentRegisterFrame(int width, int height) {
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setTitle("注册");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JTextField tStudentId = Utils.addWithLabel(panel, "学       号");
        JTextField tStudentName =  Utils.addWithLabel(panel, "姓       名");
        JTextField tStudentSex =  Utils.addWithLabel(panel, "性       别");
        JTextField tStudentAge =  Utils.addWithLabel(panel, "年       龄");
        JTextField tStudentPassword =  Utils.addWithLabel(panel, "密       码");
        JTextField tStudentPasswordConfirm =  Utils.addWithLabel(panel, "确认密码");
        JPanel submitPanel = new JPanel();
        JButton submitBtn = new JButton("注册");
        submitPanel.add(submitBtn);

        panel.add(submitPanel);
        panel.setLayout(new GridLayout(7, 1));
        this.setContentPane(panel);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    public static void main(String[] args) {
        new StudentRegisterFrame(400, 300).setVisible(true);
    }
}
