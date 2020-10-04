package uitls;

import DAO.FunctionDAO;
import DTO.ProjectDTO;
import DTO.StudentDTO;
import views.Login.UserLoginFrame;
import views.Manager.ManagerFrame;
import views.Student.StudentFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Utils {
    static public JTextField addWithLabel(JPanel panel, String lb) {
        JPanel usernamePanel = new JPanel();
        JLabel usernameLabel = new JLabel(lb);
        JTextField usernameFiled = new JTextField(12);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameFiled);
        panel.add(usernamePanel);
        return usernameFiled;
    }

    private static StudentDTO userInfo = new StudentDTO(0, "DEFAULT_NAME", "男", 0, "", 0);

    public static StudentDTO GetUserInfo() {
        return userInfo;
    }


    public static ManagerFrame GetManagerFrame() {
        return managerFrame;
    }

    private static ManagerFrame managerFrame;

    public static StudentDTO getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(StudentDTO userInfo) {
        Utils.userInfo = userInfo;
    }

    public static StudentFrame getStudentFrame() {
        return studentFrame;
    }

    public static void setStudentFrame(StudentFrame studentFrame) {
        Utils.studentFrame = studentFrame;
    }

    private static StudentFrame studentFrame;

    public static void SetManagerFrame(ManagerFrame mf) {
        managerFrame = mf;
    }

    public static void Logout() {
        if (managerFrame != null) managerFrame.dispose();
        if (studentFrame != null) studentFrame.dispose();
        UserLoginFrame u = new UserLoginFrame();
        u.setVisible(true);
    }
    public static JFrame getInfoFrame(ProjectDTO project) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 594, 383);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("项目名称");
        lblNewLabel.setBounds(14, 13, 72, 18);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("项目描述");
        lblNewLabel_1.setBounds(14, 44, 72, 18);
        contentPane.add(lblNewLabel_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(100, 44, 358, 114);
        contentPane.add(scrollPane);

        JTextArea tProjectDesc = new JTextArea();
        scrollPane.setViewportView(tProjectDesc);

        JLabel lblNewLabel_2 = new JLabel("项目功能");
        lblNewLabel_2.setBounds(14, 186, 72, 18);
        contentPane.add(lblNewLabel_2);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(100, 179, 358, 70);
        contentPane.add(scrollPane_1);

        JTextArea tProjectFunction = new JTextArea();
        scrollPane_1.setViewportView(tProjectFunction);

        JLabel lblNewLabel_3 = new JLabel("创建时间");
        lblNewLabel_3.setBounds(14, 278, 72, 18);
        contentPane.add(lblNewLabel_3);

        JLabel lbProjectBegin = new JLabel("");
        lbProjectBegin.setBounds(105, 278, 353, 18);
        contentPane.add(lbProjectBegin);

        JLabel lbProjectName = new JLabel("");
        lbProjectName.setBounds(105, 13, 353, 18);
        contentPane.add(lbProjectName);
        frame.setContentPane(contentPane);
        tProjectDesc.setEditable(false);
        tProjectFunction.setEditable(false);
        lbProjectName.setText(project.getName());
        lbProjectBegin.setText(project.getBegin().toString());
        tProjectDesc.setText(project.getDesc());
        tProjectFunction.setText(new FunctionDAO().queryByProjectIdToString(Integer.parseInt(project.getId())));

        return frame;
    }
}
