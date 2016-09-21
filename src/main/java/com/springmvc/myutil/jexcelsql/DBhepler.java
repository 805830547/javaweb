package com.springmvc.myutil.jexcelsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBhepler {
    /*
     * String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; String
     * url = "jdbc:sqlserver://127.0.0.1;DatabaseName=javenforexcel";
     */

    String driver = "com.mysql.jdbc.Driver";
    // String url = "jdbc:mysql://127.0.0.1:3306/weather?"
    // + "user=root&password=root&useUnicode=true&characterEncoding=UTF8";
    String url = "jdbc:mysql://127.0.0.1:3306/weather?"
            + "useUnicode=true&characterEncoding=UTF-8&user=root&password=root";
    Connection con = null;
    ResultSet res = null;

    public void DataBase() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            // .getConnection(url, "root", "root");
            // System.out.println("成功加载MySQL驱动程序");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.err.println("装载 JDBC/ODBC 驱动程序失败。");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("无法连接数据库");
            e.printStackTrace();
        }
    }

    // 查询
    public ResultSet Search(String sql, String str[]) {
        DataBase();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            if (str != null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i + 1, str[i]);
                }
            }
            res = pst.executeQuery();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    // 增删修改
    public int AddU(String sql, String str[]) {
        int a = 0;
        DataBase();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            if (str != null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i + 1, str[i]);
                }
            }
            a = pst.executeUpdate();
            pst.close();
            con.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        // list = cityService.getStringList("province", "country = '中国'");
        list = CityService.getStringList("city_name_zh", "province = '辽宁'");
        for (String lString : list) {
            System.out.println(lString);
        }
    }
}
