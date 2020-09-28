package views.Manager.Project;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ProjectMainFrame extends JTabbedPane {

    public ProjectMainFrame() {
//        addTab("管理", new JEditorPane());

//        查看详情，项目学生信息
        JPanel listPanel = new ProjectListFrame();
        JPanel createPanel = new ProjectCreateFrame();
        addTab("项目列表", listPanel);

//        创建空项目，暂时没人选题，学生和管理员均可创建
        addTab("创建项目", createPanel);

        setFont(new Font(null, Font.PLAIN, 24));

        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                System.out.println(getSelectedComponent());
            }
        });
    }
}
