/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.accesscontrol.Role;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IModel;
import model.accesscontrol.LeaveRequest;
import model.accesscontrol.User;
import dal.DBContext;

/**
 *
 * @author p14s
 */

public class RoleDBContext extends DBContext {

    public User login(String username, String password) {
        String sql = """
            SELECT u.user_id, u.username, u.password, u.name, u.department_id, u.manager_id, r.role_name
            FROM Users u
            JOIN UserRole ur ON u.user_id = ur.user_id
            JOIN Roles r ON ur.role_id = r.role_id
            WHERE u.username = ? AND u.password = ?
        """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setName(rs.getString("name"));
                u.setDepartmentId(rs.getInt("department_id"));
                u.setManagerId(rs.getObject("manager_id") != null ? rs.getInt("manager_id") : null);
                u.setRole(rs.getString("role_name"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Role> getByAccount(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}