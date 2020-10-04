package views.Student.StudentInfo;

import DAO.StudentDAO;
import DTO.StudentDTO;
import uitls.Utils;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyPwdFrame extends JPanel {
	private JTextField tOldPwd;
	private JTextField tNewPwd;
	private JTextField tNewPwdConfirm;
	public ModifyPwdFrame() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("原密码");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(216, 103, 86, 30);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("新密码");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(216, 161, 86, 30);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("确认密码");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(216, 219, 86, 30);
		add(lblNewLabel_2);
		
		tOldPwd = new JTextField();
		tOldPwd.setBounds(328, 108, 185, 24);
		add(tOldPwd);
		tOldPwd.setColumns(10);
		
		tNewPwd = new JTextField();
		tNewPwd.setColumns(10);
		tNewPwd.setBounds(328, 166, 185, 24);
		add(tNewPwd);
		
		tNewPwdConfirm = new JTextField();
		tNewPwdConfirm.setColumns(10);
		tNewPwdConfirm.setBounds(328, 224, 185, 24);
		add(tNewPwdConfirm);
		
		JButton submitBtn = new JButton("修改密码");
		submitBtn.setFont(new Font("宋体", Font.PLAIN, 20));
		submitBtn.setBounds(315, 299, 138, 40);
		add(submitBtn);

		submitBtn.addActionListener(actionEvent -> {
			StudentDTO userInfo = Utils.getUserInfo();
			String newPwd = tNewPwd.getText();
			if(!tOldPwd.getText().equals(userInfo.getPassword())) {
				JOptionPane.showMessageDialog(ModifyPwdFrame.this, "原密码错误");
				clearText();
				return;
			}

			if(newPwd.equals(tNewPwdConfirm.getText())) {
				if (new StudentDAO().setPassword(userInfo.getId(), tNewPwd.getText())) {
					JOptionPane.showMessageDialog(ModifyPwdFrame.this, "修改密码成功");
					Utils.Logout();
					return;
				}
				JOptionPane.showMessageDialog(ModifyPwdFrame.this, "修改密码失败");
				clearText();
			}
		});
	}

	private void clearText() {
		tOldPwd.setText(null);
		tNewPwd.setText(null);
		tNewPwdConfirm.setText(null);
	}
}
