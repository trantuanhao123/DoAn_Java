/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODELS.Phieu;
import MODELS.SachDTO;
import MODELS.SinhVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HAO
 */
public class timKiemDao {

    public List<SachDTO> searchSach(String key) throws Exception {
        String sql = "SELECT s.MaSach, s.TenSach, s.TacGia, s.NamXB, s.MaNXB, s.MaTheLoai, s.SoLuong, s.HinhAnh, "
                + "nxb.TenNXB, tl.TenTheLoai "
                + "FROM Sach s "
                + "LEFT JOIN NhaXuatBan nxb ON s.MaNXB = nxb.MaNXB "
                + "LEFT JOIN TheLoai tl ON s.MaTheLoai = tl.MaTheLoai "
                + "WHERE s.TenSach LIKE ?";
        List<SachDTO> list = new ArrayList<>();
        try (
                Connection con = KetNoiCSDL.getConnection(); PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, "%" + key + "%");

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    SachDTO sach = new SachDTO();
                    sach.setMaSach(rs.getInt("MaSach"));
                    sach.setTenSach(rs.getString("TenSach"));
                    sach.setTacGia(rs.getString("TacGia"));
                    sach.setNamXB(rs.getInt("NamXB"));
                    sach.setNXB(rs.getInt("MaNXB"));
                    sach.setTheLoai(rs.getInt("MaTheLoai"));
                    sach.setSoLuong(rs.getInt("SoLuong"));
                    sach.setHinhAnh(rs.getString("HinhAnh"));
                    list.add(sach);
                }
            }
        } catch (Exception e) {
            throw new Exception("Lỗi khi tìm kiếm sách: " + e.getMessage(), e);
        }
        return list;
    }

    public List<Phieu> searchPhieuMuon(String key) throws Exception {
        String sql = "SELECT p.MaPhieu, p.MaSV, p.NgayMuon, p.MaThuThu, p.TrangThai "
                + "FROM Phieu p JOIN SinhVien sv ON p.MaSV = sv.MaSV "
                + "WHERE sv.TenSV LIKE ?";
        List<Phieu> list = new ArrayList<>();
        try (
                Connection con = KetNoiCSDL.getConnection(); PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, "%" + key + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Phieu phieu = new Phieu();
                phieu.setMaPhieu(rs.getInt("MaPhieu"));
                phieu.setMaSV(rs.getInt("MaSV"));
                phieu.setNgayMuon(rs.getString("NgayMuon"));
                phieu.setMaThuThu(rs.getInt("MaThuThu"));
                phieu.setTrangThai(rs.getString("TrangThai"));
                list.add(phieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    public List<Phieu> searchPhieuTra(String key) throws Exception {
        String sql = "SELECT p.MaPhieu, p.MaSV, p.NgayMuon, p.MaThuThu, p.TrangThai "
                + "FROM Phieu p JOIN SinhVien sv ON p.MaSV = sv.MaSV "
                + "WHERE sv.TenSV LIKE ? AND p.TrangThai = N'Đã trả'";
        List<Phieu> list = new ArrayList<>();
        try (Connection con = KetNoiCSDL.getConnection(); PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, "%" + key + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Phieu phieu = new Phieu();
                phieu.setMaPhieu(rs.getInt("MaPhieu"));
                phieu.setMaSV(rs.getInt("MaSV"));
                phieu.setNgayMuon(rs.getString("NgayMuon"));
                phieu.setMaThuThu(rs.getInt("MaThuThu"));
                phieu.setTrangThai(rs.getString("TrangThai"));
                list.add(phieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    public List<SinhVien> searchSinhVienByName(String key) throws Exception {
        String sql = "SELECT MaSV, TenSV, NamSinh, SDT, MaLop, Tuoi FROM SinhVien WHERE TenSV LIKE ?";
        List<SinhVien> list = new ArrayList<>();
        try (Connection con = KetNoiCSDL.getConnection(); PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, "%" + key + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                SinhVien sv = new SinhVien();
                sv.setMaSV(rs.getInt("MaSV"));
                sv.setTenSV(rs.getString("TenSV"));
                sv.setNamSinh(rs.getInt("NamSinh"));
                sv.setSDT(rs.getString("SDT"));
                sv.setMaLop(rs.getInt("MaLop"));
                sv.setTuoi(rs.getInt("Tuoi"));
                list.add(sv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }
}
