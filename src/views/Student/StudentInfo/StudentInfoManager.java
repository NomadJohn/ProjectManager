package views.Student.StudentInfo;

import javax.swing.*;
import java.awt.*;

public class StudentInfoManager extends JPanel {
	public StudentInfoManager() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 809, 425);
		tabbedPane.add("学生信息", new StudentInfoFrame());
		tabbedPane.add("修改密码", new ModifyPwdFrame());
		add(tabbedPane);
	
	}
}
