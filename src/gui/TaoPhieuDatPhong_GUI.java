package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class TaoPhieuDatPhong_GUI extends JPanel {

    private static final long serialVersionUID = 1L;

    public TaoPhieuDatPhong_GUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(249, 249, 249));

        Color mauXanhDam = new Color(30, 61, 89);
        Color mauVangDong = new Color(212, 175, 55);
        Font fontTieuDe = new Font("Segoe UI Semibold", Font.PLAIN, 16);

        // ====== CHIA LÀM 2 PHẦN: TRÁI (60%) & PHẢI (40%) ======
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(0.6);
        splitPane.setResizeWeight(0.6);
        splitPane.setContinuousLayout(true);
        splitPane.setBorder(null);
        splitPane.setBackground(new Color(249, 249, 249));

        // ====== PANEL TRÁI ======
        JPanel pLeft = new JPanel();
        pLeft.setLayout(new BoxLayout(pLeft, BoxLayout.Y_AXIS));
        pLeft.setBackground(new Color(249, 249, 249));
        pLeft.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));

        // --- Khung thông tin phiếu đặt phòng ---
        JPanel pThongTin = new JPanel(new GridBagLayout());
        pThongTin.setBackground(Color.WHITE);
        pThongTin.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(mauXanhDam, 1, true),
                "Thông tin phiếu đặt phòng",
                TitledBorder.LEFT, TitledBorder.TOP,
                fontTieuDe, mauXanhDam));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

//        // Ngày nhận - Ngày trả
//        gbc.gridx = 0; gbc.gridy = 0;
//        pThongTin.add(new JLabel("Ngày nhận:"), gbc);
//        gbc.gridx = 1;
//        JTextField txtNgayNhan = new JTextField("📅"); // placeholder, thay bằng JDateChooser
//        pThongTin.add(txtNgayNhan, gbc);
//
//        gbc.gridx = 2;
//        pThongTin.add(new JLabel("Ngày trả:"), gbc);
//        gbc.gridx = 3;
//        JTextField txtNgayTra = new JTextField("📅");
//        pThongTin.add(txtNgayTra, gbc);
        
     // Ngày nhận - Ngày trả
        gbc.gridx = 0; gbc.gridy = 0;
        pThongTin.add(new JLabel("Ngày nhận:"), gbc);
        gbc.gridx = 1;
        com.toedter.calendar.JDateChooser dateNgayNhan = new com.toedter.calendar.JDateChooser();
        dateNgayNhan.setDateFormatString("dd/MM/yyyy");
        pThongTin.add(dateNgayNhan, gbc);

        gbc.gridx = 2;
        pThongTin.add(new JLabel("Ngày trả:"), gbc);
        gbc.gridx = 3;
        com.toedter.calendar.JDateChooser dateNgayTra = new com.toedter.calendar.JDateChooser();
        dateNgayTra.setDateFormatString("dd/MM/yyyy");
        pThongTin.add(dateNgayTra, gbc);


        // Số điện thoại - Tên khách hàng
        gbc.gridx = 0; gbc.gridy = 1;
        pThongTin.add(new JLabel("Số điện thoại:"), gbc);
        gbc.gridx = 1;
        pThongTin.add(new JTextField(), gbc);

        gbc.gridx = 2;
        pThongTin.add(new JLabel("Tên khách hàng:"), gbc);
        gbc.gridx = 3;
        pThongTin.add(new JTextField(), gbc);

        // Người Việt Nam - Ngày tạo
        gbc.gridx = 0; gbc.gridy = 2;
        JCheckBox chkVN = new JCheckBox("Người Việt Nam");
        chkVN.setBackground(Color.WHITE);
        pThongTin.add(chkVN, gbc);

        gbc.gridx = 2;
        pThongTin.add(new JLabel("Ngày tạo:"), gbc);
        gbc.gridx = 3;
        JTextField txtNgayTao = new JTextField(java.time.LocalDate.now().toString());
        txtNgayTao.setEditable(false);
        pThongTin.add(txtNgayTao, gbc);

        // Trạng thái - Tiền cọc
        gbc.gridx = 0; gbc.gridy = 3;
        pThongTin.add(new JLabel("Trạng thái:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> cboTrangThai = new JComboBox<>(new String[]{"Đã đặt", "Đang xử lý", "Hoàn thành"});
        pThongTin.add(cboTrangThai, gbc);

        gbc.gridx = 2;
        pThongTin.add(new JLabel("Tiền cọc:"), gbc);
        gbc.gridx = 3;
        pThongTin.add(new JTextField(), gbc);

        // Nút In phiếu
        gbc.gridx = 3; gbc.gridy = 4;
        JButton btnInPhieu = new JButton("🖨 In phiếu");
        btnInPhieu.setBackground(mauVangDong);
        btnInPhieu.setForeground(mauXanhDam);
        btnInPhieu.setFocusPainted(false);
        pThongTin.add(btnInPhieu, gbc);

        // --- Khung chi tiết phòng thuê ---
        JPanel pChiTietPhong = new JPanel(new BorderLayout());
        pChiTietPhong.setBackground(Color.WHITE);
        pChiTietPhong.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(mauXanhDam, 1, true),
                "Chi tiết phòng thuê",
                TitledBorder.LEFT, TitledBorder.TOP,
                fontTieuDe, mauXanhDam));

        String[] cols = {"STT", "Mã phòng", "Loại phòng", "Ngày nhận thực", "Ngày trả thực", "Số đêm", "Giá", "Thành tiền"};
        JTable tblPhong = new JTable(new DefaultTableModel(cols, 0));
        JScrollPane scrPhong = new JScrollPane(tblPhong);
        pChiTietPhong.add(scrPhong, BorderLayout.CENTER);

        JPanel pPhongBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnThem = new JButton("Thêm");
        JButton btnXoa = new JButton("Xóa");
        JLabel lblTongTien = new JLabel("Tổng tiền: 0 VNĐ");
        pPhongBtn.add(btnThem);
        pPhongBtn.add(btnXoa);
        pPhongBtn.add(lblTongTien);
        pPhongBtn.setBackground(Color.WHITE);
        pChiTietPhong.add(pPhongBtn, BorderLayout.SOUTH);

        // add 2 khung bên trái
        pLeft.add(pThongTin);
        pLeft.add(Box.createVerticalStrut(10));
        pLeft.add(pChiTietPhong);

        // ====== PANEL PHẢI ======
        JPanel pRight = new JPanel();
        pRight.setLayout(new BoxLayout(pRight, BoxLayout.Y_AXIS));
        pRight.setBackground(new Color(249, 249, 249));
        pRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 15));

        // --- Danh sách phòng trống ---
        JPanel pDanhSachPhong = new JPanel();
        pDanhSachPhong.setLayout(new BoxLayout(pDanhSachPhong, BoxLayout.Y_AXIS));
        pDanhSachPhong.setBackground(Color.WHITE);
        pDanhSachPhong.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(mauXanhDam, 1, true),
                "Danh sách phòng trống",
                TitledBorder.LEFT, TitledBorder.TOP,
                fontTieuDe, mauXanhDam));

        pDanhSachPhong.add(createRoomSection("Standard - 700.000 VND / 1 đêm", 15, mauXanhDam, mauVangDong));
        pDanhSachPhong.add(createRoomSection("Deluxe - 1.000.000 VND / 1 đêm", 10, mauXanhDam, mauVangDong));
        pDanhSachPhong.add(createRoomSection("Family - 1.300.000 VND / 1 đêm", 5, mauXanhDam, mauVangDong));

        JScrollPane scrPhongTrong = new JScrollPane(pDanhSachPhong);
        scrPhongTrong.setBorder(null);
        scrPhongTrong.getVerticalScrollBar().setUnitIncrement(16);

        // --- Chi phí phát sinh ---
        JPanel pChiPhi = new JPanel(new BorderLayout());
        pChiPhi.setBackground(Color.WHITE);
        pChiPhi.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(mauXanhDam, 1, true),
                "Chi phí phát sinh",
                TitledBorder.LEFT, TitledBorder.TOP,
                fontTieuDe, mauXanhDam));

        JPanel pChiPhiTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pChiPhiTop.setBackground(Color.WHITE);
        JComboBox<String> cboChiPhi = new JComboBox<>(new String[]{"Giặt ủi", "Ăn sáng", "Dịch vụ khác"});
        JButton btnThemCP = new JButton("Thêm");
        JButton btnXoaCP = new JButton("Xóa");
        pChiPhiTop.add(new JLabel("Loại chi phí:"));
        pChiPhiTop.add(cboChiPhi);
        pChiPhiTop.add(btnThemCP);
        pChiPhiTop.add(btnXoaCP);
        pChiPhi.add(pChiPhiTop, BorderLayout.NORTH);

        JTable tblChiPhi = new JTable(new DefaultTableModel(new String[]{"Tên chi phí", "Giá", "Số lượng", "Thành tiền"}, 0));
        pChiPhi.add(new JScrollPane(tblChiPhi), BorderLayout.CENTER);

        JLabel lblTongCP = new JLabel("Tổng chi phí: 0 VNĐ", SwingConstants.RIGHT);
        lblTongCP.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pChiPhi.add(lblTongCP, BorderLayout.SOUTH);

        // --- Khu vực nhân viên & tổng tiền cuối ---
        JPanel pBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        pBottom.setBackground(new Color(249, 249, 249));
        JLabel lblNV = new JLabel("Nhân viên: ");
        JTextField txtNV = new JTextField("Nguyễn Văn A", 12);
        txtNV.setEditable(false);
        JButton btnLuu = new JButton("💾 Lưu");
        JButton btnHuy = new JButton("❌ Hủy đặt phòng");
        JButton btnTT = new JButton("💳 Thanh toán");
        JButton btnThoat = new JButton("🚪 Thoát");
        JLabel lblTongTatCa = new JLabel("Tổng thanh toán: 0 VNĐ");

        pBottom.add(lblNV);
        pBottom.add(txtNV);
        pBottom.add(btnLuu);
        pBottom.add(btnHuy);
        pBottom.add(btnTT);
        pBottom.add(btnThoat);
        pBottom.add(lblTongTatCa);

        // add các phần vào pRight
        pRight.add(scrPhongTrong);
        pRight.add(Box.createVerticalStrut(10));
        pRight.add(pChiPhi);

        // ====== ADD 2 PANEL VÀO SPLITPANE ======
        splitPane.setLeftComponent(pLeft);
        splitPane.setRightComponent(pRight);

        // ====== ADD TO MAIN PANEL ======
        add(splitPane, BorderLayout.CENTER);
        add(pBottom, BorderLayout.SOUTH);
    }

    private JPanel createRoomSection(String title, int roomCount, Color mauXanhDam, Color mauVangDong) {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setBackground(Color.WHITE);

        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setForeground(mauXanhDam);
        lbl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
        p.add(lbl, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(0, 6, 5, 5));
        grid.setBackground(Color.WHITE);
        for (int i = 1; i <= roomCount; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setBackground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(mauXanhDam, 1, true));
            btn.setForeground(mauXanhDam);
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(mauVangDong);
                    btn.setForeground(Color.WHITE);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(Color.WHITE);
                    btn.setForeground(mauXanhDam);
                }
            });
            grid.add(btn);
        }
        p.add(grid, BorderLayout.CENTER);
        return p;
    }
}
