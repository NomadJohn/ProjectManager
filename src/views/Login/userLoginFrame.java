package views.Login;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;

public class userLoginFrame extends JFrame {
    public userLoginFrame() {
        setTitle("身份验证");
        setSize( 449, 428);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        JTabbedPane jtp = new JTabbedPane();
        jtp.setFont(new Font("宋体", Font.PLAIN, 20));
        jtp.setBounds(0, 87, 446, 309);
        jtp.add("登录", new LoginMainFrame(userLoginFrame.this));
        jtp.add("注册", new StudentRegisterFrame());
        getContentPane().add(jtp);
        
        JLabel lblNewLabel = new JLabel("项目管理系统");
        lblNewLabel.setFont(new Font("华文中宋", Font.BOLD, 38));
        lblNewLabel.setBounds(96, 24, 248, 51);
        getContentPane().add(lblNewLabel);

    }
}
