package views.Login;
/*
1、学生注册功能
2、登录模式选择框（管理员，学生）
3、登陆成功，按登录方式显示Manger 或 Student
 */
import uitls.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMainFrame extends JFrame {
    private JPanel contentPane;
    private JTextField tStudentName;
    private JPasswordField tStudentPassword;

    public LoginMainFrame(int width, int height) {
        setTitle("登录");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, width, height);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbStudentName = new JLabel("\u59D3\u540D");
        lbStudentName.setBounds(126, 68, 45, 18);
        contentPane.add(lbStudentName);

        JLabel lbStudentPassword = new JLabel("\u5BC6\u7801");
        lbStudentPassword.setBounds(126, 111, 45, 18);
        contentPane.add(lbStudentPassword);

        tStudentName = new JTextField();
        tStudentName.setBounds(170, 65, 128, 24);
        contentPane.add(tStudentName);
        tStudentName.setColumns(10);

        JButton sumbitBtn = new JButton("\u767B\u5F55");
        sumbitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        sumbitBtn.setBounds(169, 159, 113, 27);
        contentPane.add(sumbitBtn);

        tStudentPassword = new JPasswordField();
        tStudentPassword.setBounds(170, 108, 128, 24);
        contentPane.add(tStudentPassword);
    }

    public static void main(String[] args) {
        new LoginMainFrame(450, 300).setVisible(true);
    }
}
