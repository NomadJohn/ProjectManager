package uitls;

import DTO.StudentDTO;

import javax.swing.*;

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

    private static StudentDTO userInfo;

    public static StudentDTO GetUserInfo() {
        return userInfo;
    }

    public static void SetUserInfo(StudentDTO _userInfo) {
        userInfo = _userInfo;
    }
}
