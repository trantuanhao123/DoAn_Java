/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author KIET
 */
public class KetNoiCSDL {
    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        if (conn == null|| conn.isClosed()) {
            try {
                String url = "jdbc:sqlserver://localhost:1433;databaseName=QL_ThuVien;encrypt=true;trustServerCertificate=true";
                String user = "sa";
                String password = "123";
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
