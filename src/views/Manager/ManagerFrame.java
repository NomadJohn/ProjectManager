package views.Manager;

import DTO.StudentDTO;
import com.formdev.flatlaf.FlatLightLaf;
import uitls.DBManager;
import uitls.Utils;
import views.Manager.Project.ProjectMainFrame;
import views.Manager.Student.StudentMainFrame;
import views.Manager.System.SystemMainFrame;

import javax.swing.*;
import java.awt.*;

public class ManagerFrame extends JFrame {
    StudentDTO userInfo = null;

    public ManagerFrame() {
        userInfo = Utils.GetUserInfo();
        setSize(980, 600);
        setTitle("项目管理系统");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        Utils.SetManagerFrame(ManagerFrame.this);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setBounds(0, 0, 482, 453);
        getContentPane().add(tabbedPane);


        JPanel project = new JPanel();
        tabbedPane.addTab("项目", new ImageIcon("image//project_m.png"), project, null);
        ProjectMainFrame projectMain = new ProjectMainFrame();
        project.add(projectMain);
        project.setLayout(new GridLayout(1, 0, 0, 0));


        StudentMainFrame student = new StudentMainFrame();
        tabbedPane.addTab("学生", new ImageIcon("image/student_m.png"), student, null);

        SystemMainFrame system = new SystemMainFrame();
        tabbedPane.addTab("系统", new ImageIcon("image/system_m.png"), system, null);
        getContentPane().setLayout(new GridLayout(1, 1));

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mns = new JMenu("系统(S)");
        menuBar.add(mns);

        JMenuItem menuItem_3 = new JMenuItem("学生管理");
        mns.add(menuItem_3);

        JMenuItem menuItem_2 = new JMenuItem("项目管理");
        mns.add(menuItem_2);

        JMenuItem menuItem_4 = new JMenuItem("系统信息");
        mns.add(menuItem_4);

        JMenu mnh = new JMenu("帮助(H)");
        menuBar.add(mnh);

        JMenuItem menuItem_1 = new JMenuItem("退出系统");
        mnh.add(menuItem_1);

        JMenuItem menuItem = new JMenuItem("关于");
        mnh.add(menuItem);
//        initMenu();
    }

    void initMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("项目管理");
        JMenuItem m1_1 = new JMenuItem("项目列表");
        JMenuItem m1_2 = new JMenuItem("创建项目");
        m1.add(m1_1);
        m1.add(m1_2);
        JMenu m2 = new JMenu("学生管理");

        JMenuItem m2_1 = new JMenuItem("学生列表");
        JMenuItem m2_2 = new JMenuItem("添加学生");
        m2.add(m2_1);
        m2.add(m2_2);
        JMenu m3 = new JMenu("系统管理");

        JMenuItem m3_1 = new JMenuItem("修改密码");
        JMenuItem m3_2 = new JMenuItem("退出登录");
        m3.add(m3_1);
        m3.add(m3_2);

        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        this.setJMenuBar(mb);
//
    }

    public void switchToComponent(Component cmp) {
        removeAll();
        getContentPane().add(cmp);
    }


    public static void main(String[] args) {
//        FlatLightLaf.install();
        ManagerFrame mf = new ManagerFrame();
        mf.setVisible(true);
    }
}
