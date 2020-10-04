package views;
/*
    入口类
 */

import com.formdev.flatlaf.FlatLightLaf;
import views.Login.UserLoginFrame;


public class ProjectManager {
    public static void main(String[] args) {
        FlatLightLaf.install();
        new UserLoginFrame().setVisible(true);
    }
}
