package views.Login;

import javax.swing.*;

public class userLoginFrame extends JFrame {
    public userLoginFrame() {
        setTitle("登录&注册");
        setSize( 450, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane jtp = new JTabbedPane();
        jtp.add("登录", new LoginMainFrame(userLoginFrame.this));
        jtp.add("注册", new StudentRegisterFrame());
        add(jtp);

    }
}
