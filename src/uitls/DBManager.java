package uitls;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DBManager {
    static final String DB_URL = "jdbc:mysql://hw.z-os.cn:3306/ProjectManager";
    static final String USER = "ProjectManager";
    static final String PASSWORD = "ProjectManager";
    private static Connection conn = null;
    private static Statement stmt = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConn() {
        return conn;
    }

    public static ArrayList<HashMap<String, String>> query(String sql) {
        ArrayList<HashMap<String, String>> resultArray = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();


            while (rs.next()) {
                HashMap<String, String> map = new HashMap<>();
                for (int i = 1; i <= data.getColumnCount(); i++) {// 数据库里从 1 开始

                    String c = data.getColumnName(i);
                    String v = rs.getString(c);
                    map.put(c, v);
                }
                resultArray.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultArray;
    }

    public static int update(String sql) {
        int affectedRowCount = 0;
        try {
            affectedRowCount = stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectedRowCount;
    }

    public static void close() {
        try {
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        ArrayList<HashMap<String, String>> rs = query("select VERSION()");
//        System.out.println(rs.get(0).get("VERSION()"));
//    }
}
