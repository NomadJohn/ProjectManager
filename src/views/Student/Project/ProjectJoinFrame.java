package views.Student.Project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
1、显示所有可加入的项目
2、选择一列，点击加入
3、加入系统，需要组长同意

已有申请，直接显示已申请加入xxx项目，不允许操作。
 */
public class ProjectJoinFrame extends JPanel {
    private JTable tProjectList;

    /**
     * Create the panel.
     */
    public ProjectJoinFrame() {
        setLayout(null);

        JButton submitBtn = new JButton("\u52A0\u5165\u9879\u76EE");
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        submitBtn.setBounds(312, 370, 93, 23);
        add(submitBtn);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(14, 13, 748, 341);
        add(scrollPane);

        tProjectList = new JTable();
        scrollPane.setViewportView(tProjectList);
        tProjectList.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "New column", "New column", "New column", "New column", "New column"
                }
        ));

    }
}
