/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IModel;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author p14s
 */




import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=assmprj301_db;encrypt=true;trustServerCertificate=true;";
        String user = "ducnh";
        String password = "ducnhhe";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, password);
    }
}
//    public DBContext()
//    {
//        try {
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=PRJ301A42;encrypt=true;trustServerCertificate=true;";
//            String user = "ducnh";
//            String pass = "ducnhhe";
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            connection = DriverManager.getConnection(url, user, pass);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public abstract ArrayList<T> list();
//    public abstract T get(int   id);
//    public abstract void insert(T model);
//    public abstract void update(T model);
//    public abstract void delete(T model);
//    
//}
