/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODELS.NhaXuatBan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class NhaXuatBanDAO {
    public List<NhaXuatBan> getAll() {
    List<NhaXuatBan> list = new ArrayList<>();
    String sql = "SELECT MaNXB, TenNXB FROM NhaXuatBan";  // Bảng nhà xuất bản

    try (
        Connection conn = KetNoiCSDL.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
    ) {
        while (rs.next()) {
            int ma = rs.getInt("MaNXB");      // đúng tên cột
            String ten = rs.getString("TenNXB");
            list.add(new NhaXuatBan(ma, ten));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

}
