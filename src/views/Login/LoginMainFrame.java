package views.Login;
/*
1、学生注册功能
2、登录模式选择框（管理员，学生）
3、登陆成功，按登录方式显示Manger 或 Student
 */
import DAO.ManagerDAO;
import DAO.StudentDAO;
import uitls.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

        JLabel lbStudentID = new JLabel("学号");
        lbStudentID.setBounds(126, 68, 45, 18);
        contentPane.add(lbStudentID);

        JLabel lbPassword = new JLabel("\u5BC6\u7801");
        lbPassword.setBounds(126, 111, 45, 18);
        contentPane.add(lbPassword);

        tStudentID = new JTextField();
        tStudentID.setBounds(170, 65, 128, 24);
        contentPane.add(tStudentID);
        tStudentID.setColumns(10);

        JButton sumbitBtn = new JButton("\u767B\u5F55");

        sumbitBtn.setBounds(170, 220, 108, 27);
        contentPane.add(sumbitBtn);

        tStudentPassword = new JPasswordField();
        tStudentPassword.setBounds(170, 108, 128, 24);
        contentPane.add(tStudentPassword);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"学生", "管理员"}));
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(170, 157, 128, 24);
        contentPane.add(comboBox);

        JLabel lbType = new JLabel("身份");
        lbType.setBounds(126, 160, 45, 18);
        contentPane.add(lbType);

        sumbitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                boolean v  = false;
                if (comboBox.getSelectedIndex() == 0) {
                    v = new StudentDAO().login(Integer.valueOf(tStudentID.getText()), tStudentPassword.getText());
                }else{
                    v = new ManagerDAO().login(Integer.valueOf(tStudentID.getText()), tStudentPassword.getText());
                }
                if(v){
                    JOptionPane.showMessageDialog(LoginMainFrame.this, "登录成功");
                    //登录成功后的处理

                    mainFrame.dispose();
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
