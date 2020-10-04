package views;
/*
    入口类
 */

import com.formdev.flatlaf.FlatLightLaf;
import views.Login.UserLoginFrame;


public class ProjectManager {
    public static void main(String[] args) {
//        FlatLightLaf.install();
        UserLoginFrame loginFrame = new UserLoginFrame();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }
}
