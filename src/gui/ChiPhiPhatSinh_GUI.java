package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ChiPhiPhatSinh_GUI {

    private JFrame frame;
    private JTextField txtMaCP, txtGia, txtTenCP;
    private JTable table;
    private JComboBox<String> cboLoai;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ChiPhiPhatSinh_GUI window = new ChiPhiPhatSinh_GUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ChiPhiPhatSinh_GUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Chi Phí Phát Sinh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // ==== MAIN PANEL ====
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        // ==== TITLE ====
        JLabel lblTitle = new JLabel("CHI PHÍ PHÁT SINH", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setForeground(new Color(30, 60, 114));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // ==== PANEL THÔNG TIN ====
        JPanel infoPanel = new JPanel(new BorderLayout(10, 10));
        infoPanel.setBorder(new TitledBorder("Thông tin chi phí phát sinh"));
        mainPanel.add(infoPanel, BorderLayout.NORTH);

        // ==== FORM ====
        JPanel formPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        formPanel.setBackground(Color.WHITE);

        // === Hàng 1: Mã và Giá ===
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 30));
        JLabel lblMa = new JLabel("Mã chi phí phát sinh: ");
        txtMaCP = new JTextField(40);
        txtMaCP.setEditable(false);
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
        buttonColumn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
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
        JLabel lblDS = new JLabel("Danh sách chi phí phát sinh");
        lblDS.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDS.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tablePanel.add(lblDS, BorderLayout.NORTH);

        String[] columns = {"STT", "Mã Chi Phí Phát Sinh", "Tên Chi Phí Phát Sinh", "Giá ", "Loại Chi Phí"};
        Object[][] data = {};
        table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.CENTER);
    }

    private void btnColumnStyle(JButton btn) {
        btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn.setBackground(new Color(220, 230, 250));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 200)));
    }
}
