/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELS;

/**
 *
 * @author THIS PC
 */
public class ChiTietPhieu {
    private int maPhieu;
    private int maSach;
    private int soLuongMuon;
    private String ngayTra;

    public ChiTietPhieu() {
    }

    // Constructor, getters, setters
    public ChiTietPhieu(int maPhieu, int maSach, int soLuongMuon, String ngayTra) {
        this.maPhieu = maPhieu;
        this.maSach = maSach;
        this.soLuongMuon = soLuongMuon;
        this.ngayTra = ngayTra;
    }

    // Getters and setters
    public int getMaPhieu() { return maPhieu; }
    public void setMaPhieu(int maPhieu) { this.maPhieu = maPhieu; }
    public int getMaSach() { return maSach; }
    public void setMaSach(int maSach) { this.maSach = maSach; }
    public int getSoLuongMuon() { return soLuongMuon; }
    public void setSoLuongMuon(int soLuongMuon) { this.soLuongMuon = soLuongMuon; }
    public String getNgayTra() { return ngayTra; }
    public void setNgayTra(String ngayTra) { this.ngayTra = ngayTra; }
}
