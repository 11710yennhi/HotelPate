package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Phong_GUI extends JPanel implements ActionListener, MouseListener{

    private JTextField textFieldMaKH, textFieldTrangThai;
    private JComboBox<String> comboLoaiPhong;
    private JTable table;

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

        // --- Hàng Mã Khách Hàng ---
        JPanel rowMaKH = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
        rowMaKH.setBackground(new Color(245, 245, 245));
        JLabel lblMaKH = new JLabel("Mã Khách Hàng:");
        lblMaKH.setFont(labelFont);
        textFieldMaKH = new JTextField(60);
        textFieldMaKH.setPreferredSize(new Dimension(200, 25));
        rowMaKH.add(lblMaKH);
        rowMaKH.add(textFieldMaKH);
        formPanel.add(rowMaKH);

        // --- Hàng Trạng Thái ---
        JPanel rowTrangThai = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
        rowTrangThai.setBackground(new Color(245, 245, 245));
        JLabel lblTrangThai = new JLabel("Trạng Thái:      ");
        lblTrangThai.setFont(labelFont);
        textFieldTrangThai = new JTextField(60);
        textFieldTrangThai.setPreferredSize(new Dimension(200, 25));
        rowTrangThai.add(lblTrangThai);
        rowTrangThai.add(textFieldTrangThai);
        formPanel.add(rowTrangThai);

        // --- Hàng Loại Phòng ---
        JPanel rowLoaiPhong = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 10));
        rowLoaiPhong.setBackground(new Color(245, 245, 245));
        JLabel lblLoaiPhong = new JLabel("Loại Phòng:");
        lblLoaiPhong.setFont(labelFont);
        comboLoaiPhong = new JComboBox<>(new String[]{"Standard", "Deluxe", "Family"});
        comboLoaiPhong.setPreferredSize(new Dimension(600, 25));
        rowLoaiPhong.add(lblLoaiPhong);
        rowLoaiPhong.add(comboLoaiPhong);
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

    // Test panel độc lập
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Panel Phong");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.add(new Phong_GUI());
            frame.setVisible(true);
        });
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
