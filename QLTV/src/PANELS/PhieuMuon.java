/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PANELS;

import DAO.SachDAO;
import DAO.phieuDAO;
import DAO.sinhVienDAO;
import DAO.chitietphieuDAO;
import DAO.sinhVienDAO;
import MODELS.ChiTietPhieu;
import MODELS.Phieu;
import MODELS.SachDTO;
import MODELS.SinhVien;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kiet
 */
public class PhieuMuon extends javax.swing.JPanel {

    private Object oldValue = null;
    sinhVienDAO svDAO = new sinhVienDAO();
    List<SinhVien> danhSachSinhVien = svDAO.getALlSinhVien();

    /**
     * Creates new form PhieuMuon
     */
    public PhieuMuon() throws Exception {
        initComponents();
        addSoLuongListener();
        loadPhieuTable();
        loadChiTietPhieuTable();
        loadSachChonTable();
    }

    private String timTenSinhVien(int maSV, List<SinhVien> danhSach) {
        for (SinhVien sv : danhSach) {
            if (sv.getMaSV() == maSV) {
                return sv.getTenSV();
            }
        }
        return "Không rõ";
    }

    private void addSoLuongListener() {
        DefaultTableModel model = (DefaultTableModel) tblSachChon.getModel();
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();

                    if (column == 3) {
                        Object value = model.getValueAt(row, column);
                        try {
                            int soLuong = Integer.parseInt(value.toString());
                            if (soLuong < 1 || soLuong > 3) {
                                JOptionPane.showMessageDialog(null, "Số lượng phải từ 1 đến 3!");
                                model.setValueAt(3, row, column);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ!");
                            model.setValueAt(1, row, column);
                        }
                    }
                }
            }
        });
    }

    private void loadPhieuTable() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblPhieu.getModel();
        model.setRowCount(0);

        phieuDAO pdao = new phieuDAO();
        List<Phieu> danhSach = pdao.getAllPhieu();

        sinhVienDAO svDAO = new sinhVienDAO();
        List<SinhVien> danhSachSinhVien = svDAO.getALlSinhVien();

        for (Phieu p : danhSach) {
            String tenSV = timTenSinhVien(p.getMaSV(), danhSachSinhVien);
            model.addRow(new Object[]{
                p.getMaPhieu(), tenSV, p.getNgayMuon(), p.getTrangThai(), p.getMaThuThu()
            });
        }
    }

    private void loadChiTietPhieuTable() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblChiTietPhieu.getModel();
        model.setRowCount(0);
        chitietphieuDAO dpdao = new chitietphieuDAO();
        List<ChiTietPhieu> danhSach = dpdao.getAll();
        for (ChiTietPhieu ct : danhSach) {
            model.addRow(new Object[]{
                ct.getMaPhieu(), ct.getMaSach(), ct.getSoLuongMuon(), ct.getNgayTra()
            });
        }
    }

    private boolean validateInput() {
        if (txtMaPhieu.getText().isEmpty() || txtMaSV.getText().isEmpty()
                || txtNgayMuon.getText().isEmpty() || txtMaThuThu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin phiếu!");
            return false;
        }
        if (!rbtnDangMuon.isSelected() && !rbtnDaTra.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái!");
            return false;
        }
        try {
            Integer.parseInt(txtMaPhieu.getText());
            Integer.parseInt(txtMaSV.getText());
            Integer.parseInt(txtMaThuThu.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã phiếu, mã SV và mã thủ thư phải là số!");
            return false;
        }
        return true;
    }

    private void loadSachChonTable() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblSachChon.getModel();
        model.setRowCount(0);

        List<SachDTO> list = new SachDAO().GetAllSach();

        for (SachDTO s : list) {
            model.addRow(new Object[]{
                false,
                s.getMaSach(),
                s.getTenSach(),
                1
            });
        }
    }

    private void hienThiKetQuaLenBang(List<SachDTO> danhSach) {
        DefaultTableModel model = (DefaultTableModel) tblSachChon.getModel();
        model.setRowCount(0);

        for (SachDTO sach : danhSach) {
            model.addRow(new Object[]{
                false,
                sach.getMaSach(),
                sach.getTenSach(),
                1 // 
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieu = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietPhieu = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiemSach = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSachChon = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtNgayTra = new javax.swing.JTextField();
        txtMaSV = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNgayMuon = new javax.swing.JTextField();
        txtMaPhieu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMaThuThu = new javax.swing.JTextField();
        rbtnDangMuon = new javax.swing.JRadioButton();
        rbtnDaTra = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnTimSach = new javax.swing.JButton();

        tblPhieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Phiếu", "Mã Sinh Viên", "Ngày Mượn", "Trạng Thái", "Mã Thủ Thư"
            }
        ));
        tblPhieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieu);

        tblChiTietPhieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Phiếu", " Mã Sách ", "Số Lượng", "Ngày Trả"
            }
        ));
        jScrollPane2.setViewportView(tblChiTietPhieu);

        jLabel9.setText("Tìm Sách:");

        tblSachChon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Chọn", "Mã Sách", "Tên Sách", "Số Lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblSachChon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane3.setViewportView(tblSachChon);

        jLabel2.setText("Ngày Mượn:");

        jLabel1.setText("Mã Sinh Viên:");

        jLabel6.setText("Ngày Trả:");

        txtMaPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPhieuActionPerformed(evt);
            }
        });

        jLabel3.setText("Mã Phiếu:");

        buttonGroup1.add(rbtnDangMuon);
        rbtnDangMuon.setText("Đang Mượn");

        buttonGroup1.add(rbtnDaTra);
        rbtnDaTra.setText("Đã Trả");

        jLabel7.setText("Mã Thủ Thư:");

        jLabel4.setText("Trạng Thái:");

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/Repair.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnTimSach.setText("Tìm");
        btnTimSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(134, 134, 134))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbtnDangMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rbtnDaTra, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNgayMuon)
                                            .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(41, 41, 41)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaThuThu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTimKiemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnTimSach))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(37, 37, 37))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtMaThuThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rbtnDangMuon)
                                    .addComponent(rbtnDaTra)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTimKiemSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(btnTimSach))
                                .addGap(19, 19, 19)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa)))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (!validateInput()) {
            return;
        }

        try {
            int maSV = Integer.parseInt(txtMaSV.getText());
            int tongMuonTrenHeThong = new chitietphieuDAO().countBooksBorrowedByStudent(maSV);
            int tongMuonMoi = 0;

            DefaultTableModel model = (DefaultTableModel) tblSachChon.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                boolean chon = (boolean) model.getValueAt(i, 0);
                if (chon) {
                    int soLuong = (int) model.getValueAt(i, 3);
                    tongMuonMoi += soLuong;
                }
            }

            if (tongMuonTrenHeThong + tongMuonMoi > 3) {
                JOptionPane.showMessageDialog(this,
                        "Sinh viên này đã mượn " + tongMuonTrenHeThong + " cuốn. "
                        + "Không thể mượn thêm " + tongMuonMoi + " cuốn (tối đa 3 cuốn)!");
                return;
            }

            Phieu p = new Phieu();
            p.setMaSV(maSV);
            p.setNgayMuon(txtNgayMuon.getText());
            p.setTrangThai(rbtnDangMuon.isSelected() ? "Đang mượn" : "Đã trả");
            p.setMaThuThu(Integer.parseInt(txtMaThuThu.getText()));

            phieuDAO dao = new phieuDAO();
            dao.insert(p);

            for (int i = 0; i < model.getRowCount(); i++) {
                boolean chon = (boolean) model.getValueAt(i, 0);
                if (chon) {
                    int maSach = (int) model.getValueAt(i, 1);
                    int soLuong = (int) model.getValueAt(i, 3);

                    ChiTietPhieu ct = new ChiTietPhieu();
                    ct.setMaPhieu(p.getMaPhieu());
                    ct.setMaSach(maSach);
                    ct.setSoLuongMuon(soLuong);

                    String ngayMuon = txtNgayMuon.getText().trim();
                    String ngayTra = txtNgayTra.getText().trim();
                    java.time.LocalDate ngayMuonDate = java.time.LocalDate.parse(ngayMuon);

                    if (ngayTra.isEmpty()) {
                        java.time.LocalDate ngayTraAuto = ngayMuonDate.plusDays(7);
                        ct.setNgayTra(ngayTraAuto.toString());
                    } else {
                        if (!ngayTra.matches("\\d{4}-\\d{2}-\\d{2}")) {
                            JOptionPane.showMessageDialog(this, "Ngày trả không hợp lệ. Định dạng đúng là yyyy-MM-dd!");
                            return;
                        }

                        java.time.LocalDate ngayTraDate = java.time.LocalDate.parse(ngayTra);
                        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(ngayMuonDate, ngayTraDate);

                        if (daysBetween > 14) {
                            JOptionPane.showMessageDialog(this, "Không được mượn quá 14 ngày kể từ ngày mượn!");
                            return;
                        }

                        ct.setNgayTra(ngayTra);
                    }

                    new chitietphieuDAO().insert(ct);
                }
            }

            JOptionPane.showMessageDialog(this, "Thêm phiếu thành công!");
            loadPhieuTable();
            loadChiTietPhieuTable();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm phiếu!");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblPhieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuMouseClicked
        int row = tblPhieu.getSelectedRow();
        int maPhieu = Integer.parseInt(tblPhieu.getValueAt(row, 0).toString());

        txtMaPhieu.setText(String.valueOf(maPhieu));
        txtMaSV.setText(tblPhieu.getValueAt(row, 1).toString());
        txtNgayMuon.setText(tblPhieu.getValueAt(row, 2).toString());
        txtMaThuThu.setText(tblPhieu.getValueAt(row, 4).toString());

        String trangThai = tblPhieu.getValueAt(row, 3).toString();
        rbtnDangMuon.setSelected(trangThai.equals("Đang mượn"));
        rbtnDaTra.setSelected(trangThai.equals("Đã trả"));

        try {
            loadChiTietPhieuTable();

            chitietphieuDAO dao = new chitietphieuDAO();
            List<ChiTietPhieu> danhSachCT = dao.findAllByMaPhieu(maPhieu);

            DefaultTableModel model = (DefaultTableModel) tblSachChon.getModel();
            model.setRowCount(0);

            for (ChiTietPhieu ct : danhSachCT) {
                SachDTO sach = new SachDAO().FindByID(ct.getMaSach());
                model.addRow(new Object[]{
                    true,
                    ct.getMaSach(),
                    sach != null ? sach.getTenSach() : "Không rõ",
                    ct.getSoLuongMuon()
                });

                txtNgayTra.setText(ct.getNgayTra());
            }

        } catch (Exception ex) {
            Logger.getLogger(PhieuMuon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblPhieuMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (tblSachChon.isEditing()) {
            tblSachChon.getCellEditor().stopCellEditing();
        }

        if (!validateInput()) {
            return;
        }

        try {
            Phieu p = new Phieu();
            p.setMaPhieu(Integer.parseInt(txtMaPhieu.getText()));
            p.setMaSV(Integer.parseInt(txtMaSV.getText()));
            p.setNgayMuon(txtNgayMuon.getText());
            p.setMaThuThu(Integer.parseInt(txtMaThuThu.getText()));
            p.setTrangThai(rbtnDangMuon.isSelected() ? "Đang mượn" : "Đã trả");

            phieuDAO dao = new phieuDAO();
            dao.update(p);

            chitietphieuDAO ctDao = new chitietphieuDAO();
            ctDao.deleteByMaPhieu(p.getMaPhieu());

            for (int i = 0; i < tblSachChon.getRowCount(); i++) {
                boolean daChon = (boolean) tblSachChon.getValueAt(i, 0);
                if (daChon) {
                    int maSach = Integer.parseInt(tblSachChon.getValueAt(i, 1).toString());
                    int soLuong;

                    try {
                        soLuong = Integer.parseInt(tblSachChon.getValueAt(i, 3).toString());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ tại dòng " + (i + 1));
                        return;
                    }

                    if (soLuong <= 0 || soLuong > 3) {
                        JOptionPane.showMessageDialog(this, "Mỗi đầu sách chỉ được mượn từ 1 đến 3 cuốn! (Dòng " + (i + 1) + ")");
                        return;
                    }

                    ChiTietPhieu ct = new ChiTietPhieu();
                    ct.setMaPhieu(p.getMaPhieu());
                    ct.setMaSach(maSach);
                    ct.setSoLuongMuon(soLuong);

                    String ngayMuon = txtNgayMuon.getText().trim();
                    String ngayTra = txtNgayTra.getText().trim();
                    java.time.LocalDate ngayMuonDate = java.time.LocalDate.parse(ngayMuon);

                    if (ngayTra.isEmpty()) {
                        ct.setNgayTra(ngayMuonDate.plusDays(7).toString());
                    } else {
                        java.time.LocalDate ngayTraDate = java.time.LocalDate.parse(ngayTra);

                        if (ngayTraDate.isBefore(ngayMuonDate)) {
                            JOptionPane.showMessageDialog(this, "Ngày trả không được trước ngày mượn! (Dòng " + (i + 1) + ")");
                            return;
                        }

                        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(ngayMuonDate, ngayTraDate);
                        if (daysBetween > 14) {
                            JOptionPane.showMessageDialog(this, "Không được mượn quá 14 ngày! (Dòng " + (i + 1) + ")");
                            return;
                        }

                        ct.setNgayTra(ngayTra);
                    }

                    ctDao.insert(ct);
                }
            }

            JOptionPane.showMessageDialog(null, "Sửa phiếu thành công!");
            loadPhieuTable();
            loadChiTietPhieuTable();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi sửa phiếu!");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (txtMaPhieu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu cần xóa!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa phiếu này?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int maPhieu = Integer.parseInt(txtMaPhieu.getText());

                chitietphieuDAO ctDao = new chitietphieuDAO();
                ctDao.deleteByMaPhieu(maPhieu);

                phieuDAO dao = new phieuDAO();
                dao.delete(maPhieu);

                JOptionPane.showMessageDialog(this, "Xóa phiếu thành công!");
                loadPhieuTable();
                loadChiTietPhieuTable();

                txtMaPhieu.setText("");
                txtMaSV.setText("");
                txtNgayMuon.setText("");
                txtMaThuThu.setText("");
                txtNgayTra.setText("");
                rbtnDangMuon.setSelected(false);
                rbtnDaTra.setSelected(false);

                DefaultTableModel model = (DefaultTableModel) tblSachChon.getModel();
                model.setRowCount(0);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa phiếu!");
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSachActionPerformed
        // TODO add your handling code here:
        String tuKhoa = txtTimKiemSach.getText().trim().toLowerCase();
        if (tuKhoa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm!");
            return;
        }

        SachDAO dao = new SachDAO();
        List<SachDTO> danhSachTatCaSach = dao.selectAll();

        List<SachDTO> ketQua = new ArrayList<>();
        for (SachDTO s : danhSachTatCaSach) {
            if (s.getTenSach().toLowerCase().contains(tuKhoa)) {
                ketQua.add(s);
            }
        }

        hienThiKetQuaLenBang(ketQua);
    }//GEN-LAST:event_btnTimSachActionPerformed

    private void txtMaPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPhieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaPhieuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimSach;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rbtnDaTra;
    private javax.swing.JRadioButton rbtnDangMuon;
    private javax.swing.JTable tblChiTietPhieu;
    private javax.swing.JTable tblPhieu;
    private javax.swing.JTable tblSachChon;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JTextField txtMaThuThu;
    private javax.swing.JTextField txtNgayMuon;
    private javax.swing.JTextField txtNgayTra;
    private javax.swing.JTextField txtTimKiemSach;
    // End of variables declaration//GEN-END:variables
}
