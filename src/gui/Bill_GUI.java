package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import dao.HoaDon_DAO;
import entity.HoaDon;

public class Bill_GUI extends JFrame implements ActionListener{
	private JButton btnThanhToan;
    public Bill_GUI(String maHoaDon) {
    	HoaDon_DAO hdDAO = new HoaDon_DAO();
    	HoaDon hoaDon = hdDAO.tinhTienHoaDon(maHoaDon);
        HashMap<String, Object> hd = hdDAO.getThongTinChiTietHoaDon(maHoaDon);
        ArrayList<Object[]> dsPhong = hdDAO.getChiTietPhongTheoHoaDon(maHoaDon);
        ArrayList<Object[]> dsPhi = hdDAO.getChiTietChiPhiTheoHoaDon(maHoaDon);
    	
        setTitle("Hóa đơn thanh toán");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(25, 50, 25, 50));
        add(mainPanel);

        // ====== PHẦN TIÊU ĐỀ ======
        JLabel lblTitle = new JLabel("HÓA ĐƠN THANH TOÁN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // ====== PHẦN TRUNG TÂM ======
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // --- Thông tin hóa đơn ---
        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.add(new JLabel("Mã hóa đơn: " + hd.get("maHoaDon"), SwingConstants.LEFT));
        infoPanel.add(new JLabel("Tên khách hàng: " + hd.get("tenKhachHang"), SwingConstants.LEFT));
        infoPanel.add(new JLabel("Ngày nhận: " + hd.get("ngayNhan"), SwingConstants.LEFT));
        infoPanel.add(new JLabel("Ngày trả: " + hd.get("ngayTra"), SwingConstants.LEFT));
        infoPanel.add(new JLabel("Mã nhân viên: " + hd.get("maNhanVien"), SwingConstants.LEFT));
        infoPanel.add(new JLabel("Trạng thái: " + hd.get("trangThai"), SwingConstants.LEFT));
        infoPanel.add(new JLabel("Mã phiếu đặt phòng: " + hd.get("maPhieuDatPhong"), SwingConstants.LEFT));
        infoPanel.add(new JLabel("Phương thức thanh toán: " + hd.get("phuongThucThanhToan"), SwingConstants.LEFT));
        
        infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        centerPanel.add(infoPanel);
        centerPanel.add(Box.createVerticalStrut(10));

        // --- Chi tiết phòng thuê ---
        JLabel lblRoom = new JLabel("Chi tiết phòng thuê:");
        lblRoom.setFont(new Font("Segoe UI", Font.BOLD, 14));
        centerPanel.add(lblRoom);

        String[] colPhong = {"STT", "Mã phòng", "Loại phòng", "Giá", "Thành tiền"};
        DefaultTableModel modelPhong = new DefaultTableModel(colPhong, 0);

        for (Object[] row : dsPhong) {
            modelPhong.addRow(row);
        }

        JTable tablePhong = new JTable(modelPhong);
        centerPanel.add(new JScrollPane(tablePhong));

        centerPanel.add(Box.createVerticalStrut(15));

        // --- Chi tiết chi phí phát sinh ---
        JLabel lblPhi = new JLabel("Chi tiết chi phí phát sinh:");
        lblPhi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        centerPanel.add(lblPhi);

        String[] colPhi = {"STT", "Tên dịch vụ", "Số lượng", "Đơn giá", "Thành tiền"};
        DefaultTableModel modelPhi = new DefaultTableModel(colPhi, 0);

        for (Object[] row : dsPhi) {
            modelPhi.addRow(row);
        }

        JTable tblPhi = new JTable(modelPhi);
        centerPanel.add(new JScrollPane(tblPhi));

        centerPanel.add(Box.createVerticalStrut(15));

     // ====== PHẦN DƯỚI BẢNG (CỘNG TIỀN + NÚT) ======
        JPanel bottomPanel = new JPanel(new BorderLayout(0, 10));
        bottomPanel.setBackground(Color.WHITE);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);

        Font fLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fBold = new Font("Segoe UI", Font.BOLD, 14);

        // === Panel chứa các dòng tiền (BoxLayout dọc) ===
        JPanel moneyBox = new JPanel();
        moneyBox.setLayout(new BoxLayout(moneyBox, BoxLayout.Y_AXIS));
        moneyBox.setBackground(Color.WHITE);

        DefaultTableModel model = (DefaultTableModel) tablePhong.getModel();


        double tongTienPhong = 0;
        int colThanhTien = model.findColumn("Thành tiền"); 
        if (colThanhTien >= 0) {
            for (int i = 0; i < model.getRowCount(); i++) {
                Object value = model.getValueAt(i, colThanhTien);
                if (value != null && !value.toString().isEmpty()) {
                    // Loại bỏ dấu phẩy (,) nếu có
                    String strValue = value.toString().replace(",", "");
                    try {
                        tongTienPhong += Double.parseDouble(strValue);
                    } catch (NumberFormatException e) {
                        // bỏ qua nếu ô không hợp lệ
                    }
                }
            }
        }
        double khuyenMai = 0;
        double tongThanhToan = tongTienPhong - khuyenMai;
        
        DecimalFormat df = new DecimalFormat("#,##0");
        moneyBox.add(createMoneyLine("Tổng tiền phòng:", df.format(tongTienPhong) + " VND", fLabel, fBold));
        moneyBox.add(createMoneyLine("Khuyến mãi:", "0 VND", fLabel, fBold));
        moneyBox.add(createMoneyLine("Tổng thanh toán:", df.format(tongThanhToan) + " VND", fLabel, fBold));


        moneyBox.add(Box.createVerticalStrut(8));


     // === Panel tiền khách ===
        Box tienKhachBox = Box.createHorizontalBox();
        JLabel lblTienKhach = new JLabel("Tiền khách đưa:");
        lblTienKhach.setFont(fLabel);

        JTextField txtTienKhach = new JTextField(10);
        txtTienKhach.setMaximumSize(new Dimension(150, 28));
        tienKhachBox.add(lblTienKhach);
        tienKhachBox.add(Box.createHorizontalStrut(10));
        tienKhachBox.add(txtTienKhach);
        moneyBox.add(tienKhachBox);
        moneyBox.add(Box.createVerticalStrut(5));

        JPanel moneyWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        moneyWrapper.setBackground(Color.WHITE);
        moneyWrapper.add(moneyBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.setBackground(Color.WHITE);

        btnThanhToan = new JButton("Xác nhận thanh toán");
        btnThanhToan.setBackground(new Color(27, 94, 32));
        btnThanhToan.setForeground(Color.WHITE);
        btnThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnThanhToan.setPreferredSize(new Dimension(200, 40));
        btnThanhToan.setFocusPainted(false);
        buttonPanel.add(btnThanhToan);

        bottomPanel.add(moneyWrapper, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        btnThanhToan.addActionListener(this);

    }

    private JPanel createMoneyLine(String label, String value, Font f1, Font f2) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        JLabel lbl1 = new JLabel(label);
        lbl1.setFont(f1);
        JLabel lbl2 = new JLabel(value);
        lbl2.setFont(f2);
        p.add(lbl1, BorderLayout.WEST);
        p.add(lbl2, BorderLayout.EAST);
        return p;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == btnThanhToan) {
            // Đóng cửa sổ Bill_GUI hiện tại
            this.dispose();
        }
    }
}
