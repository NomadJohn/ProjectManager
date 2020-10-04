package views.Student.StudentInfo;

import DAO.ProjectDAO;
import DAO.StudentDAO;
import DTO.FunctionDTO;
import DTO.StudentDTO;
import uitls.Utils;
import views.Student.StudentFrame;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentInfoFrame extends JPanel {
	private StudentDTO userInfo = Utils.getUserInfo();
	private JLabel lbProjectId;
	public StudentInfoFrame() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("学号");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(14, 13, 93, 32);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(14, 70, 100, 32);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("性别");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(14, 131, 100, 32);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("年龄");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(14, 200, 100, 32);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("进行中项目");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(14, 265, 100, 32);
		add(lblNewLabel_4);
		
		JLabel lbStudentId = new JLabel("");
		lbStudentId.setFont(new Font("宋体", Font.PLAIN, 20));
		lbStudentId.setBounds(128, 13, 393, 32);
		add(lbStudentId);
		
		JLabel lbStudentNane = new JLabel("");
		lbStudentNane.setFont(new Font("宋体", Font.PLAIN, 20));
		lbStudentNane.setBounds(128, 70, 409, 32);
		add(lbStudentNane);
		
		JLabel lbStudentSex = new JLabel("");
		lbStudentSex.setFont(new Font("宋体", Font.PLAIN, 20));
		lbStudentSex.setBounds(128, 131, 113, 32);
		add(lbStudentSex);
		
		JLabel lbStudentAge = new JLabel("");
		lbStudentAge.setFont(new Font("宋体", Font.PLAIN, 20));
		lbStudentAge.setBounds(128, 200, 113, 32);
		add(lbStudentAge);
		
		lbProjectId = new JLabel("");
		lbProjectId.setFont(new Font("宋体", Font.PLAIN, 20));
		lbProjectId.setBounds(128, 265, 487, 32);
		add(lbProjectId);
		
		JButton button = new JButton("退出登录");
		button.addActionListener(arg0 -> {
			Utils.Logout();
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(331, 328, 120, 42);
		add(button);


		lbStudentId.setText(String.valueOf(userInfo.getId()));
		lbStudentNane.setText(userInfo.getName());
		lbStudentSex.setText(userInfo.getSex());
		lbStudentAge.setText(String.valueOf(userInfo.getAge()));

		JTabbedPane jtp = StudentFrame.getJtp();
		jtp.addChangeListener(changeEvent -> {
			if (jtp.getSelectedIndex() == 3) {
				reSetProjectName();
			}
		});
	}

	private void reSetProjectName() {
		int currProjectId = new StudentDAO().getStuProjectId(userInfo.getId());
		lbProjectId.setText(String.valueOf(currProjectId == 0 ? "没有加入项目" : new ProjectDAO().getProjectNameById(currProjectId)));
	}
}
