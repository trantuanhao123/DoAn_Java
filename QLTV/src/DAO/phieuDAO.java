/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODELS.Phieu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KIET
 */
public class phieuDAO {

    public List<Phieu> getAllPhieu() throws Exception {
        List<Phieu> list = new ArrayList<>();
        String sql = "SELECT * FROM Phieu";

        try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Phieu p = new Phieu();
                p.setMaPhieu(rs.getInt("maPhieu"));
                p.setMaSV(rs.getInt("maSV"));
                p.setNgayMuon(rs.getString("ngayMuon"));
                p.setMaThuThu(rs.getInt("maThuThu"));
                p.setTrangThai(rs.getString("trangThai"));
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

//    public void insert(Phieu p) throws Exception {
//        String sql = "INSERT INTO Phieu(maSV, ngayMuon, trangThai, maThuThu) VALUES (?, ?, ?, ?)";
//
//        try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(1, p.getMaSV());
//            stmt.setString(2, p.getNgayMuon());
//            stmt.setInt(3, p.getMaThuThu());
//            stmt.setString(4, p.getTrangThai());
//            stmt.executeUpdate();
//        }
//    }
public void insert(Phieu p) throws Exception {
    String sql = "INSERT INTO Phieu(maSV, ngayMuon, trangThai,maThuThu) VALUES (?, ?, ?, ?)";
    try (Connection conn = KetNoiCSDL.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, p.getMaSV());
        stmt.setString(2, p.getNgayMuon());
        stmt.setInt(4, p.getMaThuThu());
        stmt.setString(3, p.getTrangThai());

        stmt.executeUpdate();

        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                p.setMaPhieu(rs.getInt(1)); 
            }
        }
    }
}


    public void update(Phieu p) throws Exception {
        String sql = "UPDATE Phieu SET maSV=?, ngayMuon=?, trangThai=? , maThuThu=? WHERE maPhieu=?";

        try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getMaSV());
            stmt.setString(2, p.getNgayMuon());
            stmt.setInt(4, p.getMaThuThu());
            stmt.setString(3, p.getTrangThai());
            stmt.setInt(5, p.getMaPhieu());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int maPhieu) throws Exception {
    Connection conn = KetNoiCSDL.getConnection();

    String sql1 = "DELETE FROM ChiTietPhieu WHERE maPhieu = ?";
    try (PreparedStatement stmt1 = conn.prepareStatement(sql1)) {
        stmt1.setInt(1, maPhieu);
        stmt1.executeUpdate();
    }
    
    String sql2 = "DELETE FROM Phieu WHERE maPhieu = ?";
    try (PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
        stmt2.setInt(1, maPhieu);
        stmt2.executeUpdate();
    }
}
}
