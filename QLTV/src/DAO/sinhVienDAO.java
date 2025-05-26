/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODELS.SinhVien;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author HAO
 */
public class sinhVienDAO {
    public List<SinhVien> getALlSinhVien(){
        try{
            List<SinhVien> listSV = new ArrayList<>();
            String sql = "SELECT * FROM SINHVIEN";
            Connection con = KetNoiCSDL.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                SinhVien sv = new SinhVien();
                sv.setMaSV(rs.getInt(1));
                sv.setTenSV(rs.getString(2));
                sv.setNamSinh(rs.getInt(3));
                sv.setSDT(rs.getString(4));
                sv.setMaLop(rs.getInt(5));
                sv.setTuoi(rs.getInt(6));
                listSV.add(sv);
            }
            return listSV;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public int themSinhVien(SinhVien sv){
        try{
            String sql = "INSERT INTO SINHVIEN VALUES(?,?,?,?,?)";
            Connection con = KetNoiCSDL.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,sv.getTenSV());
            pstm.setInt(2, sv.getNamSinh());
            pstm.setString(3,sv.getSDT());
            pstm.setInt(4, sv.getMaLop());
            pstm.setInt(5, sv.getTuoi());
            return pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    public int xoaSinhVien(int maSV){
        try{
            String sql ="DELETE SINHVIEN WHERE MASV =?";
            Connection con = KetNoiCSDL.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, maSV);
            return pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    public int capNhatSinhVien(SinhVien sv){
        try{
            String sql = "UPDATE SINHVIEN SET TENSV=?,NAMSINH=?,SDT=?,MALOP=?,TUOI=? WHERE MASV=?";
            Connection con = KetNoiCSDL.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,sv.getTenSV());
            pstm.setInt(2, sv.getNamSinh());
            pstm.setString(3,sv.getSDT());
            pstm.setInt(4, sv.getMaLop());
            pstm.setInt(5, sv.getTuoi());
            pstm.setInt(6,sv.getMaSV());
            return pstm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}
