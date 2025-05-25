/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import MODELS.ThuThu;
import java.sql.*;

/**
 *
 * @author ACER
 */
public class ThuThuDAO {
      public List<ThuThu> getAll() {
        List<ThuThu> list = new ArrayList<>();
        String sql = "SELECT MaThuThu, TenThuThu, SDT, Email, NgaySinh FROM ThuThu";

        try (
            Connection conn = KetNoiCSDL.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                String ma = String.valueOf(rs.getInt("MaThuThu")); // chuyển INT thành String
                String ten = rs.getString("TenThuThu");
                String sdt = rs.getString("SDT");
                String email = rs.getString("Email");
                Date ngaySinh = rs.getDate("NgaySinh");

                list.add(new ThuThu(ma, ten, sdt, email, ngaySinh));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
      
      public ThuThu findById(String maThuThu) {
        String sql = "SELECT MaThuThu, TenThuThu, SDT, Email, NgaySinh FROM ThuThu WHERE MaThuThu = ?";
        try (
            Connection conn = KetNoiCSDL.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, Integer.parseInt(maThuThu));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String ma = String.valueOf(rs.getInt("MaThuThu"));
                String ten = rs.getString("TenThuThu");
                String sdt = rs.getString("SDT");
                String email = rs.getString("Email");
                Date ngaySinh = rs.getDate("NgaySinh");
                return new ThuThu(ma, ten, sdt, email, ngaySinh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // nếu không tìm thấy
    }

    public int themThuThu(ThuThu tt) {
        try {
            String sql = "INSERT INTO ThuThu (TenThuThu, SDT, Email, NgaySinh) VALUES (?, ?, ?, ?)";
            Connection con = KetNoiCSDL.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, tt.getTenThuThu());
            pstm.setString(2, tt.getSDT());
            pstm.setString(3, tt.getEmail());
            pstm.setDate(4, new java.sql.Date(tt.getNgaySinh().getTime()));

            return pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int xoaThuThu(String maThuThu) {
        try {
            String sql = "DELETE FROM ThuThu WHERE MaThuThu = ?";
            Connection con = KetNoiCSDL.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(maThuThu)); // chuyển về int
            return pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int suaThuThu(ThuThu tt) {
        try {
            String sql = "UPDATE ThuThu SET TenThuThu = ?, SDT = ?, Email = ?, NgaySinh = ? WHERE MaThuThu = ?";
            Connection con = KetNoiCSDL.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, tt.getTenThuThu());
            pstm.setString(2, tt.getSDT());
            pstm.setString(3, tt.getEmail());
            pstm.setDate(4, new java.sql.Date(tt.getNgaySinh().getTime()));
            pstm.setInt(5, Integer.parseInt(tt.getMaThuThu())); // chuyển về int
            return pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
