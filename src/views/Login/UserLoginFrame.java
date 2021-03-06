package views.Login;

import uitls.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;


public class UserLoginFrame extends JFrame {
    boolean bTitle = false;

    public UserLoginFrame() {
        setIconImage(Utils.icon);
        setTitle("身份验证");
        setSize(450, 552);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        JTabbedPane jtp = new JTabbedPane();
        jtp.setFont(new Font("宋体", Font.PLAIN, 20));
        jtp.setBounds(0, 213, 446, 309);
        jtp.add("登录", new LoginMainFrame(UserLoginFrame.this));
        jtp.add("注册", new StudentRegisterFrame());
        getContentPane().add(jtp);

        JLabel lblNewLabel = new JLabel("项目管理系统", JLabel.CENTER);

        lblNewLabel.setFont(new Font("华文中宋", Font.BOLD, 38));
        lblNewLabel.setBounds(95, 164, 248, 51);
        getContentPane().add(lblNewLabel);
        lblNewLabel.setOpaque(true);
        
        JLabel lbBanner = new JLabel("", JLabel.CENTER);
        lbBanner.setBounds(95, 0, 248, 168);
        getContentPane().add(lbBanner);
        lbBanner.setIcon(new ImageIcon("image/banner.png"));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toggleTitle(lblNewLabel);
            }
        }, 0, 150);

    }

    public void toggleTitle(Component c) {
        bTitle = !bTitle;
        if (bTitle) {
            c.setForeground(Color.BLUE);
            c.setBackground(Color.GREEN);
            c.setFont(new Font("华文中宋", Font.BOLD, 38));
        } else {
            c.setForeground(Color.RED);
            c.setBackground(Color.yellow);
            c.setFont(new Font("华文中宋", Font.BOLD, 25));

        }
    }
}
