/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODELS.ChiTietPhieu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KIET
 */
public class chitietphieuDAO {

    public List<ChiTietPhieu> getAll() throws Exception {
        List<ChiTietPhieu> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietPhieu";

        try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ChiTietPhieu ct = new ChiTietPhieu();
                ct.setMaPhieu(rs.getInt("maPhieu"));
                ct.setMaSach(rs.getInt("maSach"));
                ct.setSoLuongMuon(rs.getInt("soLuongMuon"));
                ct.setNgayTra(rs.getString("ngayTra"));
                list.add(ct);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ChiTietPhieu findByMaPhieu(int maPhieu) throws Exception {
        String sql = "SELECT * FROM ChiTietPhieu WHERE maPhieu = ?";
        try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maPhieu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ChiTietPhieu ct = new ChiTietPhieu();
                ct.setMaPhieu(rs.getInt("maPhieu"));
                ct.setMaSach(rs.getInt("maSach"));
                ct.setSoLuongMuon(rs.getInt("soLuongMuon"));
                ct.setNgayTra(rs.getString("ngayTra"));
                return ct;
            }
        }
        return null;
    }

    public void insert(ChiTietPhieu ct) throws Exception {
        String sql = "INSERT INTO ChiTietPhieu(maPhieu, maSach, soLuongMuon, ngayTra) VALUES (?, ?, ?, ?)";

        try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ct.getMaPhieu());
            stmt.setInt(2, ct.getMaSach());
            stmt.setInt(3, ct.getSoLuongMuon());
            stmt.setString(4, ct.getNgayTra());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ChiTietPhieu ct) throws Exception {
        String sql = "UPDATE ChiTietPhieu SET maSach=?, soLuongMuon=?, ngayTra=? WHERE maPhieu=? AND maSach=?";

        try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ct.getMaSach());
            stmt.setInt(2, ct.getSoLuongMuon());
            stmt.setString(3, ct.getNgayTra());
            stmt.setInt(4, ct.getMaPhieu());
            stmt.setInt(5, ct.getMaSach());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByMaPhieu(int maPhieu) throws Exception {
        String sql = "DELETE FROM ChiTietPhieu WHERE maPhieu=?";

        try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maPhieu);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
