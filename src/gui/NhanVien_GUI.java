package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.time.LocalDate;
import java.util.Date;

public class NhanVien_GUI {

    private JFrame frame;
    private JTextField txtMaNV, txtHoTen, txtNgaySinh, txtSoDT, txtEmail;
    private JRadioButton rdoNam, rdoNu, rdoQuanLy, rdoNhanVien, rdoHoatDong, rdoNghi;
    private JTable table;
    private JSpinner spnNgayTao;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                NhanVien_GUI window = new NhanVien_GUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public NhanVien_GUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Quản Lý Nhân Viên");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // ==== MAIN PANEL ====
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(Color.WHITE);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        // ==== TITLE ====
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setForeground(new Color(30, 60, 114));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // ==== THÔNG TIN NHÂN VIÊN ====
        JPanel infoPanel = new JPanel(new BorderLayout(10, 10));
        infoPanel.setBorder(new TitledBorder("Thông tin nhân viên"));
        mainPanel.add(infoPanel, BorderLayout.NORTH);

        // === FORM BÊN TRÁI ===
        JPanel formPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        formPanel.setBackground(Color.WHITE);

        // === Hàng 1 ===
        formPanel.add(new JLabel("Mã nhân viên:"));
        txtMaNV = new JTextField();
        txtMaNV.setEditable(false);
        formPanel.add(txtMaNV);

        formPanel.add(new JLabel("Họ tên:"));
        txtHoTen = new JTextField();
        formPanel.add(txtHoTen);

        // === Hàng 2 ===
        formPanel.add(new JLabel("Giới tính:"));
        JPanel gioiTinhPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        rdoNam = new JRadioButton("Nam");
        rdoNu = new JRadioButton("Nữ");
        ButtonGroup groupGT = new ButtonGroup();
        groupGT.add(rdoNam);
        groupGT.add(rdoNu);
        gioiTinhPanel.add(rdoNam);
        gioiTinhPanel.add(rdoNu);
        gioiTinhPanel.setBackground(Color.WHITE);
        formPanel.add(gioiTinhPanel);

        formPanel.add(new JLabel("Ngày sinh:"));
        txtNgaySinh = new JTextField();
        formPanel.add(txtNgaySinh);

        // === Hàng 3 ===
        formPanel.add(new JLabel("Số điện thoại:"));
        txtSoDT = new JTextField();
        formPanel.add(txtSoDT);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        // === Hàng 4 ===
        formPanel.add(new JLabel("Chức vụ:"));
        JPanel chucVuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        rdoQuanLy = new JRadioButton("Quản lý");
        rdoNhanVien = new JRadioButton("Nhân viên");
        ButtonGroup groupCV = new ButtonGroup();
        groupCV.add(rdoQuanLy);
        groupCV.add(rdoNhanVien);
        chucVuPanel.add(rdoQuanLy);
        chucVuPanel.add(rdoNhanVien);
        chucVuPanel.setBackground(Color.WHITE);
        formPanel.add(chucVuPanel);

        formPanel.add(new JLabel("Ngày tạo:"));
        spnNgayTao = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnNgayTao, "dd/MM/yyyy");
        spnNgayTao.setEditor(dateEditor);
        spnNgayTao.setValue(new Date());
        formPanel.add(spnNgayTao);

        // === Hàng 5 ===
        formPanel.add(new JLabel("Trạng thái:"));
        JPanel trangThaiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        rdoHoatDong = new JRadioButton("Đang làm");
        rdoNghi = new JRadioButton("Nghỉ");
        ButtonGroup groupTT = new ButtonGroup();
        groupTT.add(rdoHoatDong);
        groupTT.add(rdoNghi);
        trangThaiPanel.add(rdoHoatDong);
        trangThaiPanel.add(rdoNghi);
        trangThaiPanel.setBackground(Color.WHITE);
        formPanel.add(trangThaiPanel);

        // Cột trống để căn đều
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));

        infoPanel.add(formPanel, BorderLayout.CENTER);

        // === CỘT NÚT BÊN PHẢI ===
        JPanel buttonColumn = new JPanel();
        buttonColumn.setLayout(new BoxLayout(buttonColumn, BoxLayout.Y_AXIS));
        buttonColumn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonColumn.setBackground(Color.WHITE);

        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Lưu");
        JButton btnXoaRong = new JButton("Xóa Rỗng");


        Dimension btnSize = new Dimension(120, 35);
        for (JButton btn : new JButton[]{btnThem, btnSua, btnXoaRong}) {
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
        JLabel lblDS = new JLabel("Danh sách nhân viên");
        lblDS.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDS.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tablePanel.add(lblDS, BorderLayout.NORTH);

        String[] columns = {"STT", "Mã NV", "Họ Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Email", "Chức Vụ", "Ngày Tạo", "Trạng Thái"};
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
