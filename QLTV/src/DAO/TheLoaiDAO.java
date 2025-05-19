/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import MODELS.TheLoai;
/**
 *
 * @author ACER
 */
public class TheLoaiDAO {
     public List<TheLoai> getAll() {
        List<TheLoai> list = new ArrayList<>();
        String sql = "SELECT maTheLoai, tenTheLoai FROM TheLoai";
        try (
            Connection conn = KetNoiCSDL.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                int ma = rs.getInt("maTheLoai");
                String ten = rs.getString("tenTheLoai");
                list.add(new TheLoai(ma, ten));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
