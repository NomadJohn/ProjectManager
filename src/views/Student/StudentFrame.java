package views.Student;

import DAO.StudentDAO;
import DTO.StudentDTO;
//import com.formdev.flatlaf.FlatLightLaf;
import uitls.Utils;
import views.Student.Progress.ProgressManageFrame;
import views.Student.Project.ProjectCreateFrame;
import views.Student.Project.ProjectJoinFrame;

import javax.swing.*;
import java.awt.*;

/*
已有项目，则不显示Project下内容，即（项目创建、项目加入），或显示已经是项目成员
没有项目，则不现实Progress下内容，即（进度管理），或显示请加入项目
如果是组长，显示加入申请审核列表
 */
public class StudentFrame extends JFrame {
    private static JTabbedPane jtp;
    StudentDTO userInfo = null;
    public StudentFrame() {
        userInfo = Utils.GetUserInfo();
        this.setTitle("项目管理");
        this.setSize(810, 480);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setContentPane(addProject());
    }

    private JTabbedPane addProject() {
        jtp = new JTabbedPane();
        jtp.setFont(new Font("宋体", Font.PLAIN, 18));
        jtp.add("创建项目", new ProjectCreateFrame());
        jtp.add("加入项目", new ProjectJoinFrame(jtp));
        jtp.add("项目进度", new ProgressManageFrame());
        if (Utils.GetUserInfo().getProjectId() != 0) {
            jtp.setEnabledAt(0, false);
            jtp.setEnabledAt(1, false);
            jtp.setSelectedIndex(2);
        }else {
            jtp.setEnabledAt(2, false);
        }
        return jtp;
    }

    static public void setJtpIndexTo2() {
        jtp.setSelectedIndex(2);
        jtp.setEnabledAt(0, false);
        jtp.setEnabledAt(1, false);
        jtp.setEnabledAt(2, true);
    }

    static public void joinProject(int studentId, int projectId, Component frame) {
        if (new StudentDAO().joinProject(studentId, projectId)) {
            JOptionPane.showMessageDialog(frame, "加入成功");
            StudentFrame.setJtpIndexTo2();
            return;
        }
        JOptionPane.showMessageDialog(frame, "加入失败");
    }

    public static void main(String args[]) {
//        FlatLightLaf.install();
        new StudentFrame().setVisible(true);
    }
}
