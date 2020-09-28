package uitls;

import javax.swing.*;

public class Utils {
    static public JTextField addWithLabel(JPanel panel,String lb) {
        JPanel usernamePanel = new JPanel();
        JLabel usernameLabel = new JLabel(lb);
        JTextField usernameFiled = new JTextField(12);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameFiled);
        panel.add(usernamePanel);
        return usernameFiled;
    }
}
