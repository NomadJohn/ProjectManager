package views.Manager.System;

import uitls.Utils;

import javax.swing.*;
import java.awt.GridLayout;

public class SystemMainFrame extends JPanel {
    public SystemMainFrame() {
    	setLayout(new GridLayout(1, 0, 0, 0));
    	
    	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	add(tabbedPane);
    	
    	JPanel ManagerInfo = new JPanel();
    	tabbedPane.addTab("管理员信息", null, ManagerInfo, null);
    	
    	JPanel resetPassword = new JPanel();
    	tabbedPane.addTab("修改密码", null, resetPassword, null);
        System.out.println(Utils.GetUserInfo());
    }
}
