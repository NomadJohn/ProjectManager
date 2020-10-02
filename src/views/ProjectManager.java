package views;
/*
    入口类
 */

import views.Login.UserLoginFrame;


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
//        FlatLightLaf.install();
        new UserLoginFrame().setVisible(true);
    }
}
