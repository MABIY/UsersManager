package com.lh.services;

import com.lh.domain.User;
import com.lh.util.SqlHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {

    public int getPageCount() {
        int count = 0;
        String sql = "select count(*) from users";
        ResultSet rs = SqlHelper.executeQuery(sql, null);
        try {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
        }

        return count;
    }

    public ArrayList<User> getUsersPage(int pageNow, int pageSize) {
        ArrayList<User> al = new ArrayList<>();

        //查询

        String sql = "select * from users limit " + (pageNow - 1) * pageSize + "," + pageSize;
        ResultSet rs = SqlHelper.executeQuery(sql, null);
        // 二次封装
        try {
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setGrade(rs.getInt(4));
                al.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
        }
        return al;
    }


    public boolean checkUser(User user) {

        boolean exitUser = false;
        String sql = "select * from users WHERE id=? and passwd=?";
        String[] parameters = {
                user.getId() + "", user.getPasswd()
        };


        ResultSet rs = SqlHelper.executeQuery(sql, parameters);
        try {
            if (rs.next()) {
                exitUser = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());

        }
        return exitUser;
    }

    public boolean delUser(String id) {
        boolean b = false;
        String sql = "delete from users where id=?";
        String parameters[] = {id};
        ResultSet rs = null;
        try {
            rs = SqlHelper.executeUpdate(sql, parameters);
            b = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
        }
        return b;
    }

    public User getUserById(String id) {
        User u = new User();
        String sql = "select * from users where id=?";
        String parameters[] = {id};
        ResultSet rs = null;
        try {
             rs = SqlHelper.executeQuery(sql, parameters);
            if (rs.next()) {
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setGrade(rs.getInt(4));
                u.setPasswd(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
        }
        return u;
    }

    public boolean updateUser(User user) {
        boolean success = false;
        String sql = "update users set username=?,email=?,grade=?,passwd=? where id=?";
        String[] parameters = {user.getUsername(), user.getEmail(), user.getGrade()+"",user.getPasswd(), user.getId()+""};
        try {
            SqlHelper.executeUpdate(sql, parameters);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean addUser(User user) {
        boolean success = false;
        String sql = "insert into users(username, email, grade, passwd) values(?,?,?,?)";
        String parameters[] = {user.getUsername(), user.getEmail(), user.getGrade()+"", user.getPasswd()};
        try {
            SqlHelper.executeUpdate(sql, parameters);
            success = true;
        } catch (Exception e) {

        }
        return success;
    }
}
