package DAO;

import DTO.StudentDTO;

import java.awt.*;

public interface Loginable {
//    public Component frame = null;
    public Component GetFrame();
    public StudentDTO login(int a, String b);
}
