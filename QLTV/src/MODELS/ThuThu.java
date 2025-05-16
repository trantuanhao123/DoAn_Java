/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELS;

import java.util.Date;

/**
 *
 * @author HAO
 */
public class ThuThu {
    private String maThuThu,tenThuThu,SDT,Email;
    private Date ngaySinh;

    public ThuThu(String maThuThu, String tenThuThu, String SDT, String Email, Date ngaySinh) {
        this.maThuThu = maThuThu;
        this.tenThuThu = tenThuThu;
        this.SDT = SDT;
        this.Email = Email;
        this.ngaySinh = ngaySinh;
    }

    public ThuThu() {
    }

    public String getMaThuThu() {
        return maThuThu;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

    public String getTenThuThu() {
        return tenThuThu;
    }

    public void setTenThuThu(String tenThuThu) {
        this.tenThuThu = tenThuThu;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    @Override
    public String toString() {
        return "ThuThu{" + "maThuThu=" + maThuThu + ", tenThuThu=" + tenThuThu + ", SDT=" + SDT + ", Email=" + Email + ", ngaySinh=" + ngaySinh + '}';
    }
    
}
