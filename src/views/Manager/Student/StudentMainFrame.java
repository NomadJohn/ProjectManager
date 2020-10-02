package views.Manager.Student;

import javax.swing.*;
import java.awt.GridLayout;

public class StudentMainFrame extends JPanel {
    public StudentMainFrame() {
        setLayout(new GridLayout(0, 1, 0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        add(tabbedPane);



        StudentAddFrame add = new StudentAddFrame();
        tabbedPane.addTab("创建学生", null, add, null);



        StudentListFrame list = new StudentListFrame(tabbedPane);
        tabbedPane.addTab("学生列表", null, list, null);
    }
}
