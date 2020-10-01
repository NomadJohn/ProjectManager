package DAO;

import DTO.StudentDTO;

import java.awt.*;

public interface Loginable {
//    public Component frame = null;
    public Component GetFrame(StudentDTO user);
    public StudentDTO login(int a, String b);
}
