package views;
/*
    入口类
 */

import com.formdev.flatlaf.FlatLightLaf;
import views.Login.userLoginFrame;


public class ProjectManager {
    public static void main(String[] args) {
//        try
//        {
//            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//        }
//        catch(Exception e)
//        {
//            //TODO exception
//        }
        FlatLightLaf.install();
        new userLoginFrame().setVisible(true);
    }
}
