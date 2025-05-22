/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author KIET
 */
public class ThongKeDAO {
    private Connection conn;

    public ThongKeDAO() {
        try {
            conn = KetNoiCSDL.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Không thể kết nối cơ sở dữ liệu!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }

    public DefaultPieDataset getGenreStats() { 
        DefaultPieDataset dataset = new DefaultPieDataset();
        try {
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Không thể kết nối cơ sở dữ liệu để lấy dữ liệu thể loại!");
                return dataset;
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT tl.TenTheLoai, COUNT(s.MaSach) as SoLuong " +
                "FROM TheLoai tl LEFT JOIN Sach s ON tl.MaTheLoai = s.MaTheLoai " +
                "GROUP BY tl.TenTheLoai"
            );
            while (rs.next()) {
                int count = rs.getInt("SoLuong");
                if (count > 0) {
                    dataset.setValue(rs.getString("TenTheLoai"), count); 
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn dữ liệu thể loại: " + e.getMessage());
        }
        return dataset;
    }

    public DefaultCategoryDataset getBorrowingStats() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Không thể kết nối cơ sở dữ liệu để lấy dữ liệu sách đang mượn!");
                return dataset;
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT COUNT(DISTINCT ct.MaSach) as SoSach, SUM(ct.SoLuongMuon) as TongSoLuong " +
                "FROM ChiTietPhieu ct JOIN Phieu p ON ct.MaPhieu = p.MaPhieu " +
                "WHERE p.TrangThai = N'Đang mượn'"
            );
            if (rs.next()) {
                dataset.addValue(rs.getInt("SoSach"), "Số lượng", "Số loại sách");
                dataset.addValue(rs.getInt("TongSoLuong"), "Số lượng", "Tổng số lượng sách");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn sách đang mượn: " + e.getMessage());
        }
        return dataset;
    }

    public DefaultCategoryDataset getTopBooks() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Không thể kết nối cơ sở dữ liệu để lấy dữ liệu top sách!");
                return dataset;
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT TOP 5 s.TenSach, COUNT(ct.MaSach) as SoLanMuon " +
                "FROM ChiTietPhieu ct JOIN Sach s ON ct.MaSach = s.MaSach " +
                "GROUP BY s.TenSach " +
                "ORDER BY SoLanMuon DESC"
            );
            while (rs.next()) {
                dataset.addValue(rs.getInt("SoLanMuon"), "Số lần mượn", rs.getString("TenSach"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn top sách: " + e.getMessage());
        }
        return dataset;
    }

    public DefaultCategoryDataset getTicketStatus() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Không thể kết nối cơ sở dữ liệu để lấy dữ liệu trạng thái phiếu!");
                return dataset;
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT TrangThai, COUNT(*) as SoLuong " +
                "FROM Phieu " +
                "GROUP BY TrangThai"
            );
            while (rs.next()) {
                dataset.addValue(rs.getInt("SoLuong"), "Số lượng phiếu", rs.getString("TrangThai"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn trạng thái phiếu: " + e.getMessage());
        }
        return dataset;
    }
}