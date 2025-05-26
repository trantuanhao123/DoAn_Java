/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODELS.Lop;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author HAO
 */
public class lopDAO {

    public List<Lop> getAllLop() {
        try {
            List<Lop> listLop = new ArrayList<>();
            String sql = "SELECT * FROM LOP";
            Connection con = KetNoiCSDL.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Lop lop = new Lop();
                lop.setMaLop(rs.getInt(1));
                lop.setTenKhoa(rs.getString(2));
                lop.setTenLop(rs.getString(3));
                listLop.add(lop);
            }
            return listLop;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
