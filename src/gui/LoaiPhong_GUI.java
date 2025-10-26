package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class LoaiPhong_GUI extends JPanel implements ActionListener, MouseListener{

    private JTextField txtMaLoai, txtTenLoai, txtSucChua, txtGia, txtMoTa;
    private JTable table;

    public LoaiPhong_GUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // ===== TIÊU ĐỀ =====
        JLabel lblTitle = new JLabel("QUẢN LÝ LOẠI PHÒNG", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setForeground(new Color(30, 60, 114));
        add(lblTitle, BorderLayout.NORTH);

        // ===== PANEL THÔNG TIN =====
        JPanel infoPanel = new JPanel(new BorderLayout(10, 10));
        infoPanel.setBorder(new TitledBorder("Thông tin loại phòng"));
        infoPanel.setBackground(Color.WHITE);
        add(infoPanel, BorderLayout.NORTH);

        // ===== FORM =====
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);

        // Kích thước chung cho label và textfield
        Dimension lblSize = new Dimension(120, 25);
        Dimension txtSize = new Dimension(300, 25); // rộng hơn một chút

        // ==== HÀNG 1: Mã loại phòng + Sức chứa ====
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        row1.setBackground(Color.WHITE);

        JLabel lblMa = new JLabel("Mã loại phòng:");
        lblMa.setPreferredSize(lblSize);
        txtMaLoai = new JTextField();
        txtMaLoai.setPreferredSize(txtSize);

        JLabel lblSucChua = new JLabel("Sức chứa:");
        lblSucChua.setPreferredSize(lblSize);
        txtSucChua = new JTextField();
        txtSucChua.setPreferredSize(txtSize);
        txtSucChua.setMargin(new Insets(2, 30, 2, 30));

        row1.add(lblMa);
        row1.add(txtMaLoai);
        row1.add(lblSucChua);
        row1.add(txtSucChua);
        formPanel.add(row1);

        // ==== HÀNG 2: Tên loại phòng + Mô tả  ====
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        row2.setBackground(Color.WHITE);

        JLabel lblTen = new JLabel("Tên loại phòng:");
        lblTen.setPreferredSize(lblSize);
        txtTenLoai = new JTextField();
        txtTenLoai.setPreferredSize(txtSize); // bằng Mã loại và Giá
        
        JLabel lblMoTa = new JLabel("Mô tả:");
        lblMoTa.setPreferredSize(lblSize);
        txtMoTa = new JTextField();
        txtMoTa.setPreferredSize(txtSize);
        txtMoTa.setMargin(new Insets(2, 30, 2, 30));

        row2.add(lblTen);
        row2.add(txtTenLoai);
        row2.add(lblMoTa);
        row2.add(txtMoTa);
        formPanel.add(row2);

        // ==== HÀNG 3: Giá ====
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        row3.setBackground(Color.WHITE);

        JLabel lblGia = new JLabel("Giá:");
        lblGia.setPreferredSize(lblSize);
        txtGia = new JTextField();
        txtGia.setPreferredSize(txtSize);



        row3.add(lblGia);
        row3.add(txtGia);
        formPanel.add(row3);

        infoPanel.add(formPanel, BorderLayout.CENTER);

        // ===== CỘT NÚT =====
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 200));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnThem = new JButton("Thêm");
        JButton btnLuu = new JButton("Lưu");

        Dimension btnSize = new Dimension(120, 35);
        for (JButton btn : new JButton[]{btnThem, btnLuu}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(btnSize);
            btn.setPreferredSize(btnSize);
            styleButton(btn);
            buttonPanel.add(btn);
            buttonPanel.add(Box.createVerticalStrut(10));
        }

        infoPanel.add(buttonPanel, BorderLayout.EAST);

        // ===== BẢNG DỮ LIỆU =====
        JPanel tablePanel = new JPanel(new BorderLayout());
        JLabel lblDS = new JLabel("Danh sách loại phòng");
        lblDS.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDS.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tablePanel.add(lblDS, BorderLayout.NORTH);

        String[] columns = {"Mã loại phòng", "Tên loại phòng", "Sức chứa", "Giá", "Mô tả"};
        Object[][] data = {};
        table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn.setBackground(new Color(220, 230, 250));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 200)));
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

    // Test panel độc lập
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Test Panel LoaiPhong");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//            frame.add(new LoaiPhong_GUI());
//            frame.setVisible(true);
//        });
//    }
}
