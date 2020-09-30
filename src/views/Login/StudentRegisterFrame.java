package views.Login;

import uitls.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class StudentRegisterFrame extends JFrame {
    private JPanel contentPane;
    private JTextField tStudentId;
    private JTextField tStudentName;
    private JTextField tStudentPassword;
    private JTextField tStudentPasswordConfirm;

    public StudentRegisterFrame(int width, int height) {
        setTitle("注册");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, width, height);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbStudentId = new JLabel("\u5B66\u53F7");
        lbStudentId.setBounds(116, 16, 42, 18);
        contentPane.add(lbStudentId);

        tStudentId = new JTextField();
        tStudentId.setBounds(202, 13, 106, 24);
        contentPane.add(tStudentId);
        tStudentId.setColumns(10);

        JLabel lbStudentName = new JLabel("\u59D3\u540D");
        lbStudentName.setBounds(116, 50, 42, 18);
        contentPane.add(lbStudentName);

        tStudentName = new JTextField();
        tStudentName.setColumns(10);
        tStudentName.setBounds(202, 47, 106, 24);
        contentPane.add(tStudentName);

        JLabel lbStudentSex = new JLabel("\u6027\u522B");
        lbStudentSex.setBounds(116, 84, 42, 18);
        contentPane.add(lbStudentSex);

        JLabel lbStudentAge = new JLabel("\u5E74\u9F84");
        lbStudentAge.setBounds(116, 118, 42, 18);
        contentPane.add(lbStudentAge);

        JLabel lbStudentPassword = new JLabel("\u5BC6\u7801");
        lbStudentPassword.setBounds(116, 152, 42, 18);
        contentPane.add(lbStudentPassword);

        tStudentPassword = new JTextField();
        tStudentPassword.setColumns(10);
        tStudentPassword.setBounds(202, 149, 106, 24);
        contentPane.add(tStudentPassword);

        JLabel lbStudentPasswordConfirm = new JLabel("\u786E\u8BA4\u5BC6\u7801");
        lbStudentPasswordConfirm.setBounds(116, 186, 72, 18);
        contentPane.add(lbStudentPasswordConfirm);

        tStudentPasswordConfirm = new JTextField();
        tStudentPasswordConfirm.setColumns(10);
        tStudentPasswordConfirm.setBounds(202, 183, 106, 24);
        contentPane.add(tStudentPasswordConfirm);

        JComboBox cStudentSex = new JComboBox();
        cStudentSex.setModel(new DefaultComboBoxModel(new String[] {"\u7537", "\u5973"}));
        cStudentSex.setBounds(202, 84, 106, 24);
        contentPane.add(cStudentSex);

        JFormattedTextField tStudentAge = new JFormattedTextField(NumberFormat.getIntegerInstance());
        tStudentAge.setBounds(202, 115, 106, 24);
        contentPane.add(tStudentAge);

        JButton submintBtn = new JButton("\u6CE8\u518C");
        submintBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        submintBtn.setBounds(161, 217, 113, 27);
        contentPane.add(submintBtn);
    }
    public static void main(String[] args) {
        new StudentRegisterFrame(450, 300).setVisible(true);
    }
}
