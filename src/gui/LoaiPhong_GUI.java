package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class LoaiPhong_GUI {

    private JFrame frame;
    private JTextField txtMaLoai, txtTenLoai, txtSucChua, txtGia, txtMoTa;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoaiPhong_GUI window = new LoaiPhong_GUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoaiPhong_GUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Loại Phòng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        // ===== TIÊU ĐỀ =====
        JLabel lblTitle = new JLabel("QUẢN LÝ LOẠI PHÒNG", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setForeground(new Color(30, 60, 114));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // ===== PANEL THÔNG TIN =====
        JPanel infoPanel = new JPanel(new BorderLayout(10, 10));
        infoPanel.setBorder(new TitledBorder("Thông tin loại phòng"));
        infoPanel.setBackground(Color.WHITE);
        mainPanel.add(infoPanel, BorderLayout.NORTH);

        // ===== FORM =====
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);

        // Kích thước chung cho label và textfield
        Dimension lblSize = new Dimension(120, 25);
        Dimension txtSize = new Dimension(250, 25);

        // ==== HÀNG 1: Mã loại phòng + Sức chứa ====
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JLabel lblMa = new JLabel("Mã loại phòng:");
        lblMa.setPreferredSize(lblSize);
        txtMaLoai = new JTextField(40);
        txtMaLoai.setEditable(false);
        txtMaLoai.setPreferredSize(txtSize);

        JLabel lblSucChua = new JLabel("Sức chứa:");
        lblSucChua.setPreferredSize(lblSize);
        txtSucChua = new JTextField(40);
        txtSucChua.setPreferredSize(txtSize);

        row1.add(lblMa);
        row1.add(txtMaLoai);
        row1.add(lblSucChua);
        row1.add(txtSucChua);
        row1.setBackground(Color.WHITE);
        formPanel.add(row1);

        // ==== HÀNG 2: Tên loại phòng ====
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        JLabel lblTen = new JLabel("Tên loại phòng:");
        lblTen.setPreferredSize(lblSize);
        txtTenLoai = new JTextField(96);
        txtTenLoai.setPreferredSize(new Dimension(2 * txtSize.width + lblSize.width - 20, 25)); // rộng bằng 2 textfield
        row2.add(lblTen);
        row2.add(txtTenLoai);
        row2.setBackground(Color.WHITE);
        formPanel.add(row2);

        // ==== HÀNG 3: Giá + Mô tả ====
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JLabel lblGia = new JLabel("Giá:");
        lblGia.setPreferredSize(lblSize);
        txtGia = new JTextField(40);
        txtGia.setPreferredSize(txtSize);

        JLabel lblMoTa = new JLabel("Mô tả:");
        lblMoTa.setPreferredSize(lblSize);
        txtMoTa = new JTextField(40);
        txtMoTa.setPreferredSize(txtSize);

        row3.add(lblGia);
        row3.add(txtGia);
        row3.add(lblMoTa);
        row3.add(txtMoTa);
        row3.setBackground(Color.WHITE);
        formPanel.add(row3);

        infoPanel.add(formPanel, BorderLayout.CENTER);

        // ===== CỘT NÚT =====
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 100));
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

        mainPanel.add(tablePanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn.setBackground(new Color(220, 230, 250));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 200)));
    }
}
