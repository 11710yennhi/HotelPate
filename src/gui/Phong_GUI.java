package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Phong_GUI extends JPanel implements ActionListener, MouseListener {

    private JTextField textFieldMaPhong, textFieldTrangThai;
    private JComboBox<String> comboLoaiPhong;
    private JTable table;
    private JButton btnChiTietLoaiPhong;

    public Phong_GUI() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // === Tiêu đề ===
        JLabel lblTitle = new JLabel("QUẢN LÝ PHÒNG", SwingConstants.LEFT);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 28));
        add(lblTitle, BorderLayout.NORTH);

        // === Panel chính giữa: trái (form) - phải (ảnh) ===
        JPanel centerPanel = new JPanel(new BorderLayout(30, 0));
        centerPanel.setBackground(new Color(245, 245, 245));
        add(centerPanel, BorderLayout.CENTER);

        // ==== Form bên trái ====
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(245, 245, 245));

        Font labelFont = new Font("Tahoma", Font.PLAIN, 16);

        // --- Hàng Mã Phòng ---
        JPanel rowMaPhong = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
        rowMaPhong.setBackground(new Color(245, 245, 245));
        JLabel lblMaPhong = new JLabel("Mã Phòng: ");
        lblMaPhong.setFont(labelFont);
        textFieldMaPhong = new JTextField(50);
        textFieldMaPhong.setPreferredSize(new Dimension(200, 25));
        rowMaPhong.add(lblMaPhong);
        rowMaPhong.add(textFieldMaPhong);
        formPanel.add(rowMaPhong);

        // --- Hàng Trạng Thái ---
        JPanel rowTrangThai = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
        rowTrangThai.setBackground(new Color(245, 245, 245));
        JLabel lblTrangThai = new JLabel("Trạng Thái:");
        lblTrangThai.setFont(labelFont);
        textFieldTrangThai = new JTextField(50);
        textFieldTrangThai.setPreferredSize(new Dimension(200, 25));
        rowTrangThai.add(lblTrangThai);
        rowTrangThai.add(textFieldTrangThai);
        formPanel.add(rowTrangThai);

        // --- Hàng Loại Phòng ---
        JPanel rowLoaiPhong = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        rowLoaiPhong.setBackground(new Color(245, 245, 245));
        JLabel lblLoaiPhong = new JLabel("Loại Phòng:           ");
        lblLoaiPhong.setFont(labelFont);
        comboLoaiPhong = new JComboBox<>(new String[]{"Standard", "Deluxe", "Family"});
        comboLoaiPhong.setPreferredSize(new Dimension(500, 25));

        // 🔍 Nút biểu tượng chi tiết
        btnChiTietLoaiPhong = new JButton();
        btnChiTietLoaiPhong.setToolTipText("Xem chi tiết loại phòng");
        btnChiTietLoaiPhong.setFocusPainted(false);
        btnChiTietLoaiPhong.setBackground(Color.WHITE);
        btnChiTietLoaiPhong.setBorder(BorderFactory.createEmptyBorder());
        btnChiTietLoaiPhong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Gán icon (nếu có sẵn ảnh trong src/image/)
        ImageIcon iconSearch = new ImageIcon("src/image/icon_detail.png"); // bạn có thể thay bằng ảnh khác
        if (iconSearch.getIconWidth() <= 0) {
            // fallback: nếu không có ảnh thì dùng emoji
            btnChiTietLoaiPhong.setText("🔍");
            btnChiTietLoaiPhong.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        } else {
            btnChiTietLoaiPhong.setIcon(
                    new ImageIcon(iconSearch.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH))
            );
        }

        btnChiTietLoaiPhong.addActionListener(this);

        rowLoaiPhong.add(lblLoaiPhong);
        rowLoaiPhong.add(comboLoaiPhong);
        rowLoaiPhong.add(btnChiTietLoaiPhong);
        formPanel.add(rowLoaiPhong);

        // ==== Hàng Nút ====
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        btnPanel.setBackground(new Color(245, 245, 245));
        JButton btnThem = new JButton("Thêm");
        JButton btnLuu = new JButton("Lưu");
        JButton btnTim = new JButton("Tìm kiếm mã phòng");
        btnPanel.add(btnThem);
        btnPanel.add(btnLuu);
        btnPanel.add(btnTim);
        formPanel.add(btnPanel);

        centerPanel.add(formPanel, BorderLayout.CENTER);

        // ==== Ảnh bên phải ====
        JLabel lblAnh = new JLabel();
        lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
        lblAnh.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblAnh.setPreferredSize(new Dimension(500, 300));
        lblAnh.setIcon(new ImageIcon(
                new ImageIcon("src/image/room_main.jpg").getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH)
        ));

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(245, 245, 245));
        imagePanel.add(lblAnh, BorderLayout.NORTH);
        centerPanel.add(imagePanel, BorderLayout.EAST);

        // ==== Bảng danh sách ====
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(245, 245, 245));
        JLabel lblDS = new JLabel("DANH SÁCH PHÒNG", SwingConstants.CENTER);
        lblDS.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblDS.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        tablePanel.add(lblDS, BorderLayout.NORTH);

        String[] columns = {"STT", "Mã Phòng", "Loại Phòng", "Trạng Thái", "Sức Chứa", "Giá Phòng"};
        Object[][] data = {};
        table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.SOUTH);
    }

    // ==== Xử lý sự kiện ====
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == btnChiTietLoaiPhong) {
            String loai = (String) comboLoaiPhong.getSelectedItem();
            JOptionPane.showMessageDialog(this,
                    "Mở chi tiết loại phòng: " + loai,
                    "Chi tiết loại phòng",
                    JOptionPane.INFORMATION_MESSAGE);
            // TODO: sau này bạn có thể mở form LoaiPhong_GUI ở đây
        }
    }

    // ==== MouseListener ====
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    // ==== Test panel độc lập ====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Panel Phong");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.add(new Phong_GUI());
            frame.setVisible(true);
        });
    }
}
