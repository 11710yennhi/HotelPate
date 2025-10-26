package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ChiPhiPhatSinh_GUI extends JPanel implements ActionListener, MouseListener {

    private JTextField txtMaCP, txtGia, txtTenCP;
    private JTable table;
    private JComboBox<String> cboLoai;

    public ChiPhiPhatSinh_GUI() {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // ==== TITLE ====
        JLabel lblTitle = new JLabel("CHI PHÍ PHÁT SINH", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setForeground(new Color(30, 60, 114));
        add(lblTitle, BorderLayout.NORTH);

        // ==== PANEL THÔNG TIN ====
        JPanel infoPanel = new JPanel(new BorderLayout(10, 10));
        infoPanel.setBorder(new TitledBorder("Thông tin chi phí phát sinh"));
        infoPanel.setBackground(Color.WHITE);
        add(infoPanel, BorderLayout.NORTH);

        // ==== FORM ====
        JPanel formPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        formPanel.setBackground(Color.WHITE);

        // === Hàng 1: Mã và Giá ===
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 30));
        JLabel lblMa = new JLabel("Mã chi phí phát sinh: ");
        txtMaCP = new JTextField(40);
        JLabel lblGia = new JLabel("Giá:              ");
        txtGia = new JTextField(40);
        row1.add(lblMa);
        row1.add(txtMaCP);
        row1.add(lblGia);
        row1.add(txtGia);
        row1.setBackground(Color.WHITE);
        formPanel.add(row1);

        // === Hàng 2: Tên và Loại ===
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        JLabel lblTen = new JLabel("Tên chi phí phát sinh:");
        txtTenCP = new JTextField(40);
        JLabel lblLoai = new JLabel("Loại chi phí:");
        cboLoai = new JComboBox<>(new String[]{"Dịch vụ", "Phạt"});
        cboLoai.setPreferredSize(new Dimension(400, 25));
        row2.add(lblTen);
        row2.add(txtTenCP);
        row2.add(lblLoai);
        row2.add(cboLoai);
        row2.setBackground(Color.WHITE);
        formPanel.add(row2);

        infoPanel.add(formPanel, BorderLayout.CENTER);

        // ==== CỘT NÚT BÊN PHẢI ====
        JPanel buttonColumn = new JPanel();
        buttonColumn.setLayout(new BoxLayout(buttonColumn, BoxLayout.Y_AXIS));
        buttonColumn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 100));
        buttonColumn.setBackground(Color.WHITE);

        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Lưu");
        JButton btnTim = new JButton("Tìm kiếm");

        Dimension btnSize = new Dimension(120, 35);
        for (JButton btn : new JButton[]{btnThem, btnSua, btnTim}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(btnSize);
            btn.setPreferredSize(btnSize);
            btnColumnStyle(btn);
            buttonColumn.add(btn);
            buttonColumn.add(Box.createVerticalStrut(10));
        }

        infoPanel.add(buttonColumn, BorderLayout.EAST);

        // ==== BẢNG DỮ LIỆU ====
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        JLabel lblDS = new JLabel("Danh sách chi phí phát sinh", SwingConstants.CENTER);
        lblDS.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDS.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tablePanel.add(lblDS, BorderLayout.NORTH);

        String[] columns = {"STT", "Mã Chi Phí Phát Sinh", "Tên Chi Phí Phát Sinh", "Giá", "Loại Chi Phí"};
        Object[][] data = {};
        table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);
    }

    private void btnColumnStyle(JButton btn) {
        btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn.setBackground(new Color(220, 230, 250));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 200)));
    }

    // ==== Test panel độc lập ====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Panel Chi Phí Phát Sinh");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.add(new ChiPhiPhatSinh_GUI());
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
