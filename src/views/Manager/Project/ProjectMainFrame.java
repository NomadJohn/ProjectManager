package views.Manager.Project;

import views.Student.Project.ProjectCreateFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ProjectMainFrame extends JPanel {

    public ProjectMainFrame() {
    	setLayout(new GridLayout(1, 0, 0, 0));
    	
    	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	add(tabbedPane);
    	
    	JPanel create = new JPanel();
    	tabbedPane.addTab("创建项目", null, create, null);
    	create.setLayout(new GridLayout(1,0,0,0));
		ProjectCreateFrame cf = new ProjectCreateFrame();
		create.add(cf);
    	

    	
    	JPanel list = new JPanel();
    	tabbedPane.addTab("项目列表", null, list, null);

    }
}
