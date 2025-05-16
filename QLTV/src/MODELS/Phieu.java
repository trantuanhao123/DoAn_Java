/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELS;

/**
 *
 * @author THIS PC
 */
public class Phieu {
    private int maPhieu;
    private int maSV;
    private String ngayMuon;
    private int maThuThu;
    private String trangThai;

    public Phieu() {
    }

    // Constructor, getters, setters
    public Phieu(int maPhieu, int maSV, String ngayMuon, int maThuThu, String trangThai) {
        this.maPhieu = maPhieu;
        this.maSV = maSV;
        this.ngayMuon = ngayMuon;
        this.maThuThu = maThuThu;
        this.trangThai = trangThai;
    }

    // Getters and setters
    public int getMaPhieu() { return maPhieu; }
    public void setMaPhieu(int maPhieu) { this.maPhieu = maPhieu; }
    public int getMaSV() { return maSV; }
    public void setMaSV(int maSV) { this.maSV = maSV; }
    public String getNgayMuon() { return ngayMuon; }
    public void setNgayMuon(String ngayMuon) { this.ngayMuon = ngayMuon; }
    public int getMaThuThu() { return maThuThu; }
    public void setMaThuThu(int maThuThu) { this.maThuThu = maThuThu; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
