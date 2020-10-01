package views.Student.Progress;

import DAO.FunctionDAO;
import DTO.FunctionDTO;
import uitls.Utils;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/*
1、标记已完成功能
2、添加新功能
3、删除功能
4、修改功能 *
 */
public class ProgressManageFrame extends JPanel {
	JProgressBar progressBar;
	private JTable table;
	private JTable table_1;
	ArrayList<FunctionDTO> functions;
	DefaultTableModel inCompletedModel;
	DefaultTableModel completedModel;
	FunctionDAO FuncDAO =  new FunctionDAO();

	public ProgressManageFrame() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 40, 297, 357);
		add(scrollPane);
		
		table = new JTable();
		completedModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"id", "\u5DF2\u5B8C\u6210\u529F\u80FD"
				}
		);
		table.setModel(completedModel);
		hideColumn(table, 0);
		scrollPane.setViewportView(table);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setBounds(14, 13, 767, 14);
		add(progressBar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(465, 40, 316, 357);
		add(scrollPane_1);
		
		table_1 = new JTable();
		inCompletedModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"id", "\u672A\u5B8C\u6210\u529F\u80FD"
				}
		);
		table_1.setModel(inCompletedModel);
		hideColumn(table_1, 0);
		scrollPane_1.setViewportView(table_1);
		
		JButton button = new JButton("<-移至已完成");
		button.addActionListener(e -> {
			int functionId = (int)inCompletedModel.getValueAt(table_1.getSelectedRow(), 0);
			boolean success = FuncDAO.setIsCompletedFunction(functionId, 1);
			if (success)
				reloadFunctions();
		});
		button.setBounds(325, 173, 126, 27);
		add(button);
		
		JButton button_1 = new JButton("移至未完成->");
		button_1.addActionListener(arg0 -> {
			int functionId = (int)completedModel.getValueAt(table.getSelectedRow(), 0);
			boolean success = FuncDAO.setIsCompletedFunction(functionId, 0);
			if (success)
				reloadFunctions();
		});
		button_1.setBounds(325, 242, 126, 27);
		add(button_1);
		loadFunctions();
	}

	private void setProgressBar(int completedCount) {
		int allCount = functions.size();
		int value = (int)(((float)completedCount / allCount) * 100);
		progressBar.setValue(value);
	}

	private void loadFunctions() {
		int completedCount = 0;
		functions = FuncDAO.queryByProjectId(Utils.GetUserInfo().getProjectId());
		for(FunctionDTO f:functions) {
			Object[] obj = {f.getId(), f.getName()};
			if (f.getIsCompleted().equals("1")) {
				completedCount++;
				completedModel.addRow(obj);
			}else
				inCompletedModel.addRow(obj);
		}
		setProgressBar(completedCount);
	}

	private void rmModelAll() {
		while (completedModel.getRowCount() > 0) completedModel.removeRow(0);
		while (inCompletedModel.getRowCount() > 0) inCompletedModel.removeRow(0);
	}

	private void reloadFunctions() {
		rmModelAll();
		loadFunctions();
	}

	private void hideColumn(JTable table,int index){
		TableColumn tc= table.getColumnModel().getColumn(index);
		tc.setMaxWidth(0);
		tc.setPreferredWidth(0);
		tc.setMinWidth(0);
		tc.setWidth(0);

		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(0);
	}
}
