/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODELS.SachDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SachDAO {

    public boolean InsertSach(SachDTO sach) throws Exception {
        String sql = "INSERT INTO Sach(maTheLoai, maNXB, namXB, soLuong, tenSach, tacGia, hinhAnh) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sach.getTheLoai());
            ps.setInt(2, sach.getNXB());
            ps.setInt(3, sach.getNamXB());
            ps.setInt(4, sach.getSoLuong());
            ps.setString(5, sach.getTenSach());
            ps.setString(6, sach.getTacGia());
            ps.setString(7, sach.getHinhAnh());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean UpdateSach(SachDTO sach) throws Exception {
        String sql = "UPDATE Sach SET maTheLoai = ?, maNXB = ?, namXB = ?, soLuong = ?, tenSach = ?, tacGia = ?, hinhAnh = ? WHERE maSach = ?";

        try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sach.getTheLoai());
            ps.setInt(2, sach.getNXB());
            ps.setInt(3, sach.getNamXB());
            ps.setInt(4, sach.getSoLuong());
            ps.setString(5, sach.getTenSach());
            ps.setString(6, sach.getTacGia());
            ps.setString(7, sach.getHinhAnh());
            ps.setInt(8, sach.getMaSach());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean DeleteSach(int maSach) throws Exception {
        String sql = "DELETE FROM Sach WHERE maSach = ?";

        try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);
            return ps.executeUpdate() > 0;
        }
    }

    public List<SachDTO> SearchSach(String keyword) throws Exception {
        String sql = "SELECT * FROM Sach WHERE maSach LIKE ? OR tenSach LIKE ? OR tacGia LIKE ? OR namXB LIKE ?";

        try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            ps.setString(4, searchPattern);

            try (ResultSet rs = ps.executeQuery()) {
                List<SachDTO> sachList = new ArrayList<>();
                while (rs.next()) {
                    SachDTO sach = new SachDTO(
                            rs.getInt("maSach"),
                            rs.getInt("maTheLoai"),
                            rs.getInt("maNXB"),
                            rs.getInt("namXB"),
                            rs.getInt("soLuong"),
                            rs.getString("tenSach"),
                            rs.getString("tacGia"),
                            rs.getString("hinhAnh")
                    );
                    sachList.add(sach);
                }
                return sachList;
            }
        }
    }

    public List<SachDTO> GetAllSach() throws Exception {
        String sql = "SELECT * FROM Sach";

        try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                List<SachDTO> sachList = new ArrayList<>();
                while (rs.next()) {
                    SachDTO sach = new SachDTO(
                            rs.getInt("maSach"),
                            rs.getInt("maTheLoai"),
                            rs.getInt("maNXB"),
                            rs.getInt("namXB"),
                            rs.getInt("soLuong"),
                            rs.getString("tenSach"),
                            rs.getString("tacGia"),
                            rs.getString("hinhAnh")
                    );
                    sachList.add(sach);
                }
                return sachList;
            }
        }
    }

    public SachDTO FindByID(int maSach) throws Exception {
        String sql = "SELECT * FROM Sach WHERE maSach = ?";

        try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSach);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new SachDTO(
                            rs.getInt("maSach"),
                            rs.getInt("maTheLoai"),
                            rs.getInt("maNXB"),
                            rs.getInt("namXB"),
                            rs.getInt("soLuong"),
                            rs.getString("tenSach"),
                            rs.getString("tacGia"),
                            rs.getString("hinhAnh")
                    );
                }
            }
        }
        return null;
    }

    public List<SachDTO> selectAll() {
        List<SachDTO> list = new ArrayList<>();
        try {
            Connection conn = KetNoiCSDL.getConnection();
            String sql = "SELECT * FROM Sach";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SachDTO s = new SachDTO();
                s.setMaSach(rs.getInt("MaSach"));
                s.setTenSach(rs.getString("TenSach"));
                s.setTacGia(rs.getString("TacGia"));
                s.setTheLoai(rs.getInt("MaTheLoai"));
                s.setNXB(rs.getInt("MaNXB"));
                s.setNamXB(rs.getInt("NamXB"));
                s.setSoLuong(rs.getInt("SoLuong"));
                s.setHinhAnh(rs.getString("HinhAnh"));

                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
