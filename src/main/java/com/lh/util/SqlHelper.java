package com.lh.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class SqlHelper {
    private static Connection ct;

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static CallableStatement cs;

    private static String url;
    private static String username;
    private static String driver;
    private static String password;

    private static Properties pp;
    private static InputStream fis;

    //加载驱动，只需要一次
    static {
        try {
            pp = new Properties();
            //当我们使用java web的时候，读取文件要使用类加载器

            fis = SqlHelper.class.getClassLoader().getResourceAsStream("test/dbinfo.properties");
            pp.load(fis);
            url = pp.getProperty("url");
            username = pp.getProperty("username");
            driver = pp.getProperty("driver");
            password = pp.getProperty("password");
            Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            fis = null;
        }
    }

    public static Connection getConnection() {
        try {
            ct = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ct;
    }

    //分页
    public static ResultSet executeQueery2() {
        return null;
    }

    //调用存储过程
    public static CallableStatement callPro2(String sql, String[] inprarmeters, int[] outParmeters) {
        try {
            ct = getConnection();
            cs = ct.prepareCall(sql);
            if (inprarmeters != null) {
                for (int i = 0; i < inprarmeters.length; i++) {
                    cs.setObject(i + 1, inprarmeters[i]);
                }
            }

            //给out参数赋值
            if (outParmeters != null) {
                for (int i = 0; i < outParmeters.length; i++) {
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet executeQuery(String sql, String[] parameters) {
        try {
            ct = getConnection();
            ps = ct.prepareStatement(sql);
            if (parameters != null && !parameters.equals("")) {
                for (int i = 0; i < parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
        return rs;
    }

    public static void close(ResultSet rs, Statement ps, Connection ct) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ct != null) {
            try {
                ct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static PreparedStatement getPs() {
        return ps;
    }

    public static ResultSet getRs() {
        return rs;
    }

    public static Connection getCt() {
        return ct;
    }

    public static ResultSet executeUpdate(String sql, String[] parameters) {
        try {
            ct = getConnection();
            ps = ct.prepareStatement(sql);
            if (parameters != null && !parameters.equals("")) {
                for (int i = 0; i < parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                }
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
        return rs;
    }
}
