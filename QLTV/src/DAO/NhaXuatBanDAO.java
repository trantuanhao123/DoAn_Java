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
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class NhaXuatBanDAO {

    public List<NhaXuatBan> getAll() {
        List<NhaXuatBan> list = new ArrayList<>();
        String sql = "SELECT MaNXB, TenNXB, DIACHI FROM NhaXuatBan";

        try (
                Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                int ma = rs.getInt("MaNXB");
                String ten = rs.getString("TenNXB");
                String diaChi = rs.getString("DIACHI");
                list.add(new NhaXuatBan(ma, ten,diaChi));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int themNXB(NhaXuatBan nxb) {
        try {
            String sql = "INSERT INTO NhaXuatBan VALUES (?,?)";
            Connection con = KetNoiCSDL.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, nxb.getTenNXB());
            pstm.setString(2,nxb.getDiaChi());
            return pstm.executeUpdate();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return -1;
    }
    public int xoaNXB(int maNXB) {
        try {
            String sql = "DELETE FROM NHAXUATBAN WHERE MANXB=?";
            Connection con = KetNoiCSDL.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, maNXB);
            return pstm.executeUpdate();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return -1;
    }
    public int suaNXB(NhaXuatBan nxb) {
        try {
            String sql = "UPDATE NhaXuatBan SET TENNXB =?, DIACHI=? WHERE MANXB =?";
            Connection con = KetNoiCSDL.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, nxb.getTenNXB());
            pstm.setString(2,nxb.getDiaChi());
            pstm.setInt(3,nxb.getMaNXB());
            return pstm.executeUpdate();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return -1;
    }
}
