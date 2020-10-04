package views.Login;
/*
1、学生注册功能
2、登录模式选择框（管理员，学生）
3、登陆成功，按登录方式显示Manger 或 Student
 */

import DAO.Loginable;
import DAO.ManagerDAO;
import DAO.StudentDAO;
import DTO.ManagerDTO;
import DTO.StudentDTO;
import uitls.Utils;
import views.Manager.ManagerFrame;
import views.Student.StudentFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMainFrame extends JPanel {
    private JPanel contentPane;
    private JTextField tStudentID;
    private JPasswordField tStudentPassword;

    public LoginMainFrame(JFrame mainFrame) {
        setBounds(100, 100, 450, 339);
        contentPane = this;
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setLayout(null);

        JLabel lbStudentID = new JLabel("学号");
        lbStudentID.setFont(new Font("宋体", Font.PLAIN, 18));
        lbStudentID.setBounds(119, 49, 45, 22);
        contentPane.add(lbStudentID);

        JLabel lbPassword = new JLabel("\u5BC6\u7801");
        lbPassword.setFont(new Font("宋体", Font.PLAIN, 18));
        lbPassword.setBounds(119, 98, 45, 22);
        contentPane.add(lbPassword);

        tStudentID = new JTextField();
        tStudentID.setBounds(172, 50, 128, 24);
        contentPane.add(tStudentID);
        tStudentID.setColumns(10);

        JButton sumbitBtn = new JButton("\u767B\u5F55");
        sumbitBtn.setFont(new Font("宋体", Font.PLAIN, 18));

        sumbitBtn.setBounds(172, 208, 108, 37);
        contentPane.add(sumbitBtn);

        tStudentPassword = new JPasswordField();
        tStudentPassword.setBounds(172, 96, 128, 24);
        contentPane.add(tStudentPassword);

        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("宋体", Font.PLAIN, 18));
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"学生", "管理员"}));
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(172, 145, 128, 24);
        contentPane.add(comboBox);

        JLabel lbType = new JLabel("身份");
        lbType.setFont(new Font("宋体", Font.PLAIN, 18));
        lbType.setBounds(119, 147, 45, 22);
        contentPane.add(lbType);

        sumbitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int type = comboBox.getSelectedIndex();
                int id = Integer.parseInt(tStudentID.getText());
                String password = tStudentPassword.getText();
                Loginable entity = type == 0 ? new StudentDAO() : new ManagerDAO();
                StudentDTO userInfo = entity.login(id, password);
                if (userInfo != null) {
//                    JOptionPane.showMessageDialog(LoginMainFrame.this, "登录成功");
                    //登录成功后的处理
                    mainFrame.dispose();
                    Utils.setUserInfo(userInfo);
                    entity.GetFrame().setVisible(true);
                    return;
                }
                JOptionPane.showMessageDialog(LoginMainFrame.this, "登录失败");
            }
        });
    }

//    public static void main(String[] args) {
//        new LoginMainFrame().setVisible(true);
//    }
}
