package views.Manager;

import DTO.StudentDTO;
import views.Manager.Project.ProjectMainFrame;

import javax.swing.*;
import java.awt.*;

public class ManagerFrame extends JFrame {
    Container c = this.getContentPane();
    StudentDTO user = null;
    public ManagerFrame(StudentDTO user) {
        this.user = user;
        System.out.println("MainFrame.MainFrame");
        this.setSize(500, 500);
        this.setTitle("项目管理系统");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        c.setLayout(new GridLayout(1, 1));
        initMenu();
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

        switchToComponent(new ProjectMainFrame());
    }
    public void switchToComponent(Component cmp) {
        c.removeAll();
        c.add(cmp);
    }
    public static void main(String[] args) {
//        new ManagerFrame().setVisible(true);
    }
}
