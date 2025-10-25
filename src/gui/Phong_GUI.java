package gui;

import java.awt.*;
import javax.swing.*;

public class Phong_GUI extends JPanel{

    private JFrame frame;
    private JTextField textFieldMaKH;
    private JComboBox<String> comboLoaiPhong;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Phong_GUI window = new Phong_GUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Phong_GUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Quản Lý Phòng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frame.getContentPane().add(mainPanel);

        // === Tiêu đề ===
        JLabel lblTitle = new JLabel("QUẢN LÝ PHÒNG");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 28));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // === Panel chính giữa: trái (form) - phải (ảnh) ===
        JPanel centerPanel = new JPanel(new BorderLayout(30, 0));
        centerPanel.setBackground(new Color(245, 245, 245));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // ==== Form bên trái ====
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(245, 245, 245));

        Font labelFont = new Font("Tahoma", Font.PLAIN, 16);

        // Mã khách hàng
        JPanel maKHPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        maKHPanel.setBackground(new Color(245, 245, 245));
        JLabel lblMaKH = new JLabel("Mã Khách Hàng:");
        lblMaKH.setFont(labelFont);
        textFieldMaKH = new JTextField(60);
        textFieldMaKH.setEditable(false);
        maKHPanel.add(lblMaKH);
        maKHPanel.add(textFieldMaKH);
        formPanel.add(maKHPanel);

        // Loại phòng
        JPanel loaiPhongPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 20));
        loaiPhongPanel.setBackground(new Color(245, 245, 245));
        JLabel lblLoaiPhong = new JLabel("Loại Phòng:");
        lblLoaiPhong.setFont(labelFont);
        comboLoaiPhong = new JComboBox<>(new String[]{"Standard", "Deluxe", "Family"});
        comboLoaiPhong.setPreferredSize(new Dimension(600, 25));
        loaiPhongPanel.add(lblLoaiPhong);
        loaiPhongPanel.add(comboLoaiPhong);
        formPanel.add(loaiPhongPanel);

        // Các nút
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
                new ImageIcon("src/img/room_main.png").getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH)
        ));

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(245, 245, 245));
        imagePanel.add(lblAnh, BorderLayout.NORTH);
        centerPanel.add(imagePanel, BorderLayout.EAST);

        // === Dưới cùng: Bảng danh sách ===
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(245, 245, 245));

        JLabel lblDS = new JLabel("DANH SÁCH PHÒNG");
        lblDS.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblDS.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        tablePanel.add(lblDS, BorderLayout.NORTH);

        String[] columns = {"STT", "Mã Phòng", "Loại Phòng", "Sức Chứa", "Giá Phòng"};
        Object[][] data = {};
        table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.SOUTH);
    }
}
