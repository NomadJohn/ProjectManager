package views.Manager.Student;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentListFrame extends JPanel {
	private JTable table;
	private JScrollPane scrollPane;
	public StudentListFrame() {
		setLayout(null);
		
		table = new JTable();
		table.setBounds(14, 39, 422, 188);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 59, 850, 357);
		add(scrollPane);
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(655, 429, 113, 27);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("删除");
		btnNewButton_1.setBounds(655, 469, 113, 27);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("修改");
		btnNewButton_2.setBounds(34, 19, 113, 27);
		add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("修改");
		btnNewButton_2_1.setBounds(181, 19, 113, 27);
		add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("修改");
		btnNewButton_2_1_1.setBounds(329, 19, 113, 27);
		add(btnNewButton_2_1_1);
	}
}
