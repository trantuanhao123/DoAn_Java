/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELS;

/**
 *
 * @author HAO
 */
public class Lop {
    private int maLop; 
    private String tenKhoa,tenLop;

    public Lop(int maLop, String tenKhoa, String tenLop) {
        this.maLop = maLop;
        this.tenKhoa = tenKhoa;
        this.tenLop = tenLop;
    }

    public Lop() {
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    @Override
    public String toString() {
        return this.tenLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
    
}
