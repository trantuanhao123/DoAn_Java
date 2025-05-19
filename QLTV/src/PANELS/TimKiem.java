/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PANELS;

import DAO.KetNoiCSDL;
import DAO.timKiemDao;
import MODELS.Phieu;
import MODELS.SachDTO;
import java.sql.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HAO
 */
public class TimKiem extends javax.swing.JPanel {

    /**
     * Creates new form TimKiem
     */
    public TimKiem() {
        initComponents();
    }

    private void loadSachToTable(String key) {
        timKiemDao dao = new timKiemDao();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 7) {
                    return ImageIcon.class;
                }
                return String.class;
            }
        };
        model.setColumnIdentifiers(new String[]{"Mã Sách", "Tên Sách", "Tác Giả", "Năm XB", "Tên NXB", "Tên Thể Loại", "Số Lượng", "Hình Ảnh"});
        try {
            List<SachDTO> list = dao.searchSach(key);
            try (Connection con = KetNoiCSDL.getConnection()) {
                for (SachDTO s : list) {
                    ImageIcon icon = null;
                    String hinhAnh = s.getHinhAnh();

                    if (hinhAnh != null && !hinhAnh.isEmpty()) {
                        try {
                            String imagePath = "src/IMAGES/" + hinhAnh;
                            File imageFile = new File(imagePath);

                            if (imageFile.exists()) {
                                try {
                                    BufferedImage img = ImageIO.read(imageFile);
                                    if (img != null) {
                                        Image scaledImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                                        icon = new ImageIcon(scaledImg);
                                    } else {
                                        ImageIcon tempIcon = new ImageIcon(imagePath);
                                        if (tempIcon.getIconWidth() > 0) {
                                            Image scaledImg = tempIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                                            icon = new ImageIcon(scaledImg);
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    // Lấy TenNXB từ MaNXB
                    String tenNXB = "Không xác định";
                    String sqlNXB = "SELECT TenNXB FROM NhaXuatBan WHERE MaNXB = ?";
                    try (PreparedStatement pstmtNXB = con.prepareStatement(sqlNXB)) {
                        pstmtNXB.setInt(1, s.getNXB());
                        try (ResultSet rsNXB = pstmtNXB.executeQuery()) {
                            if (rsNXB.next()) {
                                tenNXB = rsNXB.getString("TenNXB");
                            }
                        }
                    }

                    // Lấy TenTheLoai từ MaTheLoai
                    String tenTheLoai = "Không xác định";
                    String sqlTheLoai = "SELECT TenTheLoai FROM TheLoai WHERE MaTheLoai = ?";
                    try (PreparedStatement pstmtTheLoai = con.prepareStatement(sqlTheLoai)) {
                        pstmtTheLoai.setInt(1, s.getTheLoai());
                        try (ResultSet rsTheLoai = pstmtTheLoai.executeQuery()) {
                            if (rsTheLoai.next()) {
                                tenTheLoai = rsTheLoai.getString("TenTheLoai");
                            }
                        }
                    }

                    model.addRow(new Object[]{
                        s.getMaSach(), s.getTenSach(), s.getTacGia(), s.getNamXB(), tenNXB,
                        tenTheLoai, s.getSoLuong(), icon
                    });
                }
            }

            tblSearch.setModel(model);
            tblSearch.setRowHeight(80);
            tblSearch.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tblSearch.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblSearch.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblSearch.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblSearch.getColumnModel().getColumn(3).setPreferredWidth(70);
            tblSearch.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblSearch.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblSearch.getColumnModel().getColumn(6).setPreferredWidth(60);
            tblSearch.getColumnModel().getColumn(7).setPreferredWidth(90);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }

    private void loadPhieuToTable(String key) {
        timKiemDao dao = new timKiemDao();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã Phiếu", "Mã SV", "Tên SV", "Ngày Mượn", "Mã Thủ Thư", "Tên Thủ Thư", "Trạng Thái"});
        try {
            List<Phieu> list = dao.searchPhieuMuon(key);
            try (Connection con = KetNoiCSDL.getConnection()) {
                for (Phieu p : list) {
                    // Lấy TenSV từ MaSV
                    String tenSV = "Không xác định";
                    String sqlSV = "SELECT TenSV FROM SinhVien WHERE MaSV = ?";
                    try (PreparedStatement pstmSV = con.prepareStatement(sqlSV)) {
                        pstmSV.setInt(1, p.getMaSV());
                        try (ResultSet rsSV = pstmSV.executeQuery()) {
                            if (rsSV.next()) {
                                tenSV = rsSV.getString("TenSV");
                            }
                        }
                    }

                    // Lấy TenThuThu từ MaThuThu
                    String tenThuThu = "Không xác định";
                    String sqlThuThu = "SELECT TenThuThu FROM ThuThu WHERE MaThuThu = ?";
                    try (PreparedStatement pstmThuThu = con.prepareStatement(sqlThuThu)) {
                        pstmThuThu.setInt(1, p.getMaThuThu());
                        try (ResultSet rsThuThu = pstmThuThu.executeQuery()) {
                            if (rsThuThu.next()) {
                                tenThuThu = rsThuThu.getString("TenThuThu");
                            }
                        }
                    }

                    model.addRow(new Object[]{
                        p.getMaPhieu(), p.getMaSV(), tenSV, p.getNgayMuon(),
                        p.getMaThuThu(), tenThuThu, p.getTrangThai()
                    });
                }
            }

            tblSearch.setModel(model);
            tblSearch.setRowHeight(30);
            tblSearch.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tblSearch.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblSearch.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblSearch.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblSearch.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblSearch.getColumnModel().getColumn(4).setPreferredWidth(80);
            tblSearch.getColumnModel().getColumn(5).setPreferredWidth(150);
            tblSearch.getColumnModel().getColumn(6).setPreferredWidth(100);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
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

        txtKey = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        cbType = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSearch = new javax.swing.JTable();

        txtKey.setText("Tìm Kiếm Theo Tên");

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/search.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sách", "Phiếu Mượn", "Phiếu Trả" }));
        cbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTypeActionPerformed(evt);
            }
        });

        tblSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblSearch);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKey))
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch)
                    .addComponent(txtKey, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String key = txtKey.getText().trim();
        String selectedType = cbType.getSelectedItem().toString();
        if (selectedType.equals("Sách")) {
            try {
                loadSachToTable(key);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm sách: " + e.getMessage());
            }
        } else if (selectedType.equals("Phiếu Mượn")) {
            try {
                loadPhieuToTable(key);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm phiếu mượn: " + e.getMessage());
            }
        } else if (selectedType.equals("Phiếu Trả")) {
            try {
                loadPhieuDaTraToTable(key);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm phiếu trả: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed
    private void loadPhieuDaTraToTable(String key) {
        timKiemDao dao = new timKiemDao();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Mã Phiếu", "Mã SV", "Tên SV", "Ngày Mượn", "Mã Thủ Thư", "Tên Thủ Thư", "Trạng Thái"});
        try {
            List<Phieu> list = dao.searchPhieuTra(key);
            try (Connection con = KetNoiCSDL.getConnection()) {
                for (Phieu p : list) {
                    // Lấy TenSV từ MaSV
                    String tenSV = "Không xác định";
                    String sqlSV = "SELECT TenSV FROM SinhVien WHERE MaSV = ?";
                    try (PreparedStatement pstmSV = con.prepareStatement(sqlSV)) {
                        pstmSV.setInt(1, p.getMaSV());
                        try (ResultSet rsSV = pstmSV.executeQuery()) {
                            if (rsSV.next()) {
                                tenSV = rsSV.getString("TenSV");
                            }
                        }
                    }

                    // Lấy TenThuThu từ MaThuThu
                    String tenThuThu = "Không xác định";
                    String sqlThuThu = "SELECT TenThuThu FROM ThuThu WHERE MaThuThu = ?";
                    try (PreparedStatement pstmThuThu = con.prepareStatement(sqlThuThu)) {
                        pstmThuThu.setInt(1, p.getMaThuThu());
                        try (ResultSet rsThuThu = pstmThuThu.executeQuery()) {
                            if (rsThuThu.next()) {
                                tenThuThu = rsThuThu.getString("TenThuThu");
                            }
                        }
                    }

                    model.addRow(new Object[]{
                        p.getMaPhieu(), p.getMaSV(), tenSV, p.getNgayMuon(),
                        p.getMaThuThu(), tenThuThu, p.getTrangThai()
                    });
                }
            }

            tblSearch.setModel(model);
            tblSearch.setRowHeight(30);
            tblSearch.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tblSearch.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblSearch.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblSearch.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblSearch.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblSearch.getColumnModel().getColumn(4).setPreferredWidth(80);
            tblSearch.getColumnModel().getColumn(5).setPreferredWidth(150);
            tblSearch.getColumnModel().getColumn(6).setPreferredWidth(100);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        }
    }
    private void cbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTypeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSearch;
    private javax.swing.JTextField txtKey;
    // End of variables declaration//GEN-END:variables
}
