/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import MODELS.ThuThu;

/**
 *
 * @author HAO
 */
public class userDao {

    public ThuThu checkLogin(String email, String maThuThu) throws Exception {
        String sql = "SELECT Email, maThuThu FROM ThuThu WHERE Email = ? AND maThuThu = ?";
        try (
            Connection con = KetNoiCSDL.openConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, maThuThu);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ThuThu usr = new ThuThu();
                    usr.setEmail(rs.getString("email"));
                    usr.setMaThuThu(rs.getString("maThuThu"));
                    return usr;
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error checking login: " + ex.getMessage(), ex);
        }
        return null;
    }

}
