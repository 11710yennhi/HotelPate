package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DangNhap_GUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField txtTaiKhoan;
    private JPasswordField txtMatKhau;
    private JButton btnDangNhap, btnThoat;

    public DangNhap_GUI() {
        // === Cấu hình Frame ===
        setTitle("Đăng nhập hệ thống - Pate Hotel");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // === Màu sắc ===
        Color mauXanhDam = new Color(30, 61, 89);
        Color mauVangDong = new Color(212, 175, 55);

        // === Panel chính ===
        JPanel pMain = new JPanel();
        pMain.setBackground(Color.WHITE);
        pMain.setLayout(new BoxLayout(pMain, BoxLayout.Y_AXIS));
        pMain.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        // === Tiêu đề ===
        JLabel lblTitle = new JLabel("🏨 Pate Hotel", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setForeground(mauXanhDam);

        // === Hàng tài khoản ===
        Box pUser = Box.createHorizontalBox();
        JLabel lblUser = new JLabel("Tài khoản:");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUser.setPreferredSize(new Dimension(90, 30));
        txtTaiKhoan = new JTextField(20);
        txtTaiKhoan.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        pUser.add(lblUser);
        pUser.add(Box.createHorizontalStrut(10));
        pUser.add(txtTaiKhoan);

        // === Hàng mật khẩu ===
        Box pPass = Box.createHorizontalBox();
        JLabel lblPass = new JLabel("Mật khẩu:");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPass.setPreferredSize(new Dimension(90, 30));
        txtMatKhau = new JPasswordField(20);
        txtMatKhau.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        pPass.add(lblPass);
        pPass.add(Box.createHorizontalStrut(10));
        pPass.add(txtMatKhau);

        // === Nút bấm ===
        btnDangNhap = new JButton("Đăng nhập");
        btnThoat = new JButton("Thoát");
        btnDangNhap.setBackground(mauXanhDam);
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.setFocusPainted(false);
        btnThoat.setBackground(mauVangDong);
        btnThoat.setForeground(mauXanhDam);
        btnThoat.setFocusPainted(false);
        btnDangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnThoat.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPanel pButton = new JPanel();
        pButton.setBackground(Color.WHITE);
        pButton.add(btnDangNhap);
        pButton.add(Box.createHorizontalStrut(15));
        pButton.add(btnThoat);

        // === Thêm vào panel chính ===
        pMain.add(lblTitle);
        pMain.add(Box.createVerticalStrut(20));
        pMain.add(pUser);
        pMain.add(Box.createVerticalStrut(15));
        pMain.add(pPass);
        pMain.add(Box.createVerticalStrut(25));
        pMain.add(pButton);

        add(pMain);

        // === Sự kiện ===
        btnDangNhap.addActionListener(this);
        btnThoat.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnThoat) {
            System.exit(0);
        } else if (src == btnDangNhap) {
            String user = txtTaiKhoan.getText().trim();
            String pass = new String(txtMatKhau.getPassword());

            if (user.equals("admin") && pass.equals("123")) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                new TrangChinh_GUI().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DangNhap_GUI().setVisible(true));
    }
}
