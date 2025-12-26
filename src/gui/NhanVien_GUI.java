//
////package gui;
////
////import java.awt.*;
////import java.awt.event.*;
////import java.time.LocalDate;
////import java.util.ArrayList;
////import java.util.Date;
////import javax.swing.*;
////import javax.swing.border.TitledBorder;
////import javax.swing.table.DefaultTableModel;
////
////import com.toedter.calendar.JDateChooser;
////
////import connectDB.ConnectDB;
////import dao.NhanVien_DAO;
////import entity.NhanVien;
////
////public class NhanVien_GUI extends JPanel implements ActionListener, MouseListener {
////
////    private JTextField txtMaNV, txtHoTen, txtSoDT, txtEmail;
////    private JRadioButton rdoNam, rdoNu, rdoQuanLy, rdoNhanVien, rdoHoatDong, rdoNghi;
////    private JTable table;
////    private JDateChooser dateNgaySinh, dateNgayTao;
////    private JButton btnThem, btnSua, btnXoaRong;
////    private DefaultTableModel modelNV;
////
////    private NhanVien_DAO nvDAO = new NhanVien_DAO();
////
////    public NhanVien_GUI() {
////        setLayout(new BorderLayout(10, 10));
////        setBackground(Color.WHITE);
////
////        // ===== TIÊU ĐỀ =====
////        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN", SwingConstants.CENTER);
////        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
////        lblTitle.setForeground(new Color(30, 60, 114));
////        add(lblTitle, BorderLayout.NORTH);
////
////        // ===== KHỞI TẠO GIAO DIỆN =====
////        initForm();
////
////        // ===== KẾT NỐI SQL & LOAD DỮ LIỆU =====
////        try {
////            ConnectDB.getInstance().connect();
////            loadNhanVienToTable();
////        } catch (Exception e) {
////            JOptionPane.showMessageDialog(this, "Không thể kết nối CSDL: " + e.getMessage());
////        }
////    }
////
////    private void initForm() {
////        JPanel infoPanel = new JPanel(new BorderLayout(10, 10));
////        infoPanel.setBorder(new TitledBorder("Thông tin nhân viên"));
////        infoPanel.setBackground(Color.WHITE);
////        add(infoPanel, BorderLayout.NORTH);
////
////        JPanel formPanel = new JPanel(new GridLayout(5, 4, 10, 10));
////        formPanel.setBackground(Color.WHITE);
////
////        // Hàng 1
////        formPanel.add(new JLabel("Mã nhân viên:"));
////        txtMaNV = new JTextField();
////        formPanel.add(txtMaNV);
////
////        formPanel.add(new JLabel("Họ tên:"));
////        txtHoTen = new JTextField();
////        formPanel.add(txtHoTen);
////
////        // Hàng 2
////        formPanel.add(new JLabel("Giới tính:"));
////        JPanel gtPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
////        rdoNam = new JRadioButton("Nam");
////        rdoNu = new JRadioButton("Nữ");
////        ButtonGroup gtGroup = new ButtonGroup();
////        gtGroup.add(rdoNam);
////        gtGroup.add(rdoNu);
////        gtPanel.add(rdoNam);
////        gtPanel.add(rdoNu);
////        gtPanel.setBackground(Color.WHITE);
////        rdoNu.setSelected(true);
////        formPanel.add(gtPanel);
////
////        formPanel.add(new JLabel("Ngày sinh:"));
////        dateNgaySinh = new JDateChooser();
////        dateNgaySinh.setDateFormatString("dd/MM/yyyy");
////        formPanel.add(dateNgaySinh);
////
////        // Hàng 3
////        formPanel.add(new JLabel("Số điện thoại:"));
////        txtSoDT = new JTextField();
////        formPanel.add(txtSoDT);
////
////        formPanel.add(new JLabel("Email:"));
////        txtEmail = new JTextField();
////        formPanel.add(txtEmail);
////
////        // Hàng 4
////        formPanel.add(new JLabel("Chức vụ:"));
////        JPanel cvPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
////        rdoQuanLy = new JRadioButton("Quản lý");
////        rdoNhanVien = new JRadioButton("Nhân viên");
////        ButtonGroup cvGroup = new ButtonGroup();
////        cvGroup.add(rdoQuanLy);
////        cvGroup.add(rdoNhanVien);
////        cvPanel.add(rdoQuanLy);
////        cvPanel.add(rdoNhanVien);
////        cvPanel.setBackground(Color.WHITE);
////        rdoNhanVien.setSelected(true);
////        formPanel.add(cvPanel);
////
////        formPanel.add(new JLabel("Ngày tạo:"));
////        dateNgayTao = new JDateChooser();
////        dateNgayTao.setDateFormatString("dd/MM/yyyy");
////        dateNgayTao.setDate(new Date());
////        formPanel.add(dateNgayTao);
////
////        // Hàng 5
////        formPanel.add(new JLabel("Trạng thái:"));
////        JPanel ttPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
////        rdoHoatDong = new JRadioButton("Đang làm");
////        rdoNghi = new JRadioButton("Nghỉ");
////        ButtonGroup ttGroup = new ButtonGroup();
////        ttGroup.add(rdoHoatDong);
////        ttGroup.add(rdoNghi);
////        ttPanel.add(rdoHoatDong);
////        ttPanel.add(rdoNghi);
////        ttPanel.setBackground(Color.WHITE);
////        rdoHoatDong.setSelected(true);
////        formPanel.add(ttPanel);
////        formPanel.add(new JLabel(""));
////        formPanel.add(new JLabel(""));
////
////        infoPanel.add(formPanel, BorderLayout.CENTER);
////
////        // Cột nút
////        JPanel buttonCol = new JPanel();
////        buttonCol.setLayout(new BoxLayout(buttonCol, BoxLayout.Y_AXIS));
////        buttonCol.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
////        buttonCol.setBackground(Color.WHITE);
////
////        btnThem = new JButton("Thêm");
////        btnSua = new JButton("Lưu");
////        btnXoaRong = new JButton("Xóa Rỗng");
////
////        Dimension btnSize = new Dimension(120, 35);
////
//=======
//package gui;
//
//import java.awt.*;
//import java.awt.event.*;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Date;
//import javax.swing.*;
//import javax.swing.border.TitledBorder;
//import javax.swing.table.DefaultTableModel;
//
//import com.toedter.calendar.JDateChooser;
//
//import connectDB.ConnectDB;
//import dao.NhanVien_DAO;
//import entity.NhanVien;
//
//public class NhanVien_GUI extends JPanel implements ActionListener, MouseListener {
//
//    private JTextField txtMaNV, txtHoTen, txtSoDT, txtEmail;
//    private JRadioButton rdoNam, rdoNu, rdoQuanLy, rdoNhanVien, rdoHoatDong, rdoNghi;
//    private JTable table;
//    private JDateChooser dateNgaySinh, dateNgayTao;
//    private JButton btnThem, btnSua, btnXoaRong;
//    private DefaultTableModel modelNV;
//
//    private NhanVien_DAO nvDAO = new NhanVien_DAO();
//
//    public NhanVien_GUI() {
//        setLayout(new BorderLayout(10, 10));
//        setBackground(Color.WHITE);
//
//        // ===== TIÊU ĐỀ =====
//        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN", SwingConstants.CENTER);
//        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
//        lblTitle.setForeground(new Color(30, 60, 114));
//        add(lblTitle, BorderLayout.NORTH);
//
//        // ===== KHỞI TẠO GIAO DIỆN =====
//        initForm();
//
//        // ===== KẾT NỐI SQL & LOAD DỮ LIỆU =====
//        try {
//            ConnectDB.getInstance().connect();
//            loadNhanVienToTable();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Không thể kết nối CSDL: " + e.getMessage());
//        }
//    }
//
//    private void initForm() {
//        JPanel infoPanel = new JPanel(new BorderLayout(10, 10));
//        infoPanel.setBorder(new TitledBorder("Thông tin nhân viên"));
//        infoPanel.setBackground(Color.WHITE);
//        add(infoPanel, BorderLayout.NORTH);
//
//        JPanel formPanel = new JPanel(new GridLayout(5, 4, 10, 10));
//        formPanel.setBackground(Color.WHITE);
//
//        // Hàng 1
//        formPanel.add(new JLabel("Mã nhân viên:"));
//        txtMaNV = new JTextField();
//        formPanel.add(txtMaNV);
//
//        formPanel.add(new JLabel("Họ tên:"));
//        txtHoTen = new JTextField();
//        formPanel.add(txtHoTen);
//
//        // Hàng 2
//        formPanel.add(new JLabel("Giới tính:"));
//        JPanel gtPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        rdoNam = new JRadioButton("Nam");
//        rdoNu = new JRadioButton("Nữ");
//        ButtonGroup gtGroup = new ButtonGroup();
//        gtGroup.add(rdoNam);
//        gtGroup.add(rdoNu);
//        gtPanel.add(rdoNam);
//        gtPanel.add(rdoNu);
//        gtPanel.setBackground(Color.WHITE);
//        rdoNu.setSelected(true);
//        formPanel.add(gtPanel);
//
//        formPanel.add(new JLabel("Ngày sinh:"));
//        dateNgaySinh = new JDateChooser();
//        dateNgaySinh.setDateFormatString("dd/MM/yyyy");
//        formPanel.add(dateNgaySinh);
//
//        // Hàng 3
//        formPanel.add(new JLabel("Số điện thoại:"));
//        txtSoDT = new JTextField();
//        formPanel.add(txtSoDT);
//
//        formPanel.add(new JLabel("Email:"));
//        txtEmail = new JTextField();
//        formPanel.add(txtEmail);
//
//        // Hàng 4
//        formPanel.add(new JLabel("Chức vụ:"));
//        JPanel cvPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        rdoQuanLy = new JRadioButton("Quản lý");
//        rdoNhanVien = new JRadioButton("Nhân viên");
//        ButtonGroup cvGroup = new ButtonGroup();
//        cvGroup.add(rdoQuanLy);
//        cvGroup.add(rdoNhanVien);
//        cvPanel.add(rdoQuanLy);
//        cvPanel.add(rdoNhanVien);
//        cvPanel.setBackground(Color.WHITE);
//        rdoNhanVien.setSelected(true);
//        formPanel.add(cvPanel);
//
//        formPanel.add(new JLabel("Ngày tạo:"));
//        dateNgayTao = new JDateChooser();
//        dateNgayTao.setDateFormatString("dd/MM/yyyy");
//        dateNgayTao.setDate(new Date());
//        formPanel.add(dateNgayTao);
//
//        // Hàng 5
//        formPanel.add(new JLabel("Trạng thái:"));
//        JPanel ttPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        rdoHoatDong = new JRadioButton("Đang làm");
//        rdoNghi = new JRadioButton("Nghỉ");
//        ButtonGroup ttGroup = new ButtonGroup();
//        ttGroup.add(rdoHoatDong);
//        ttGroup.add(rdoNghi);
//        ttPanel.add(rdoHoatDong);
//        ttPanel.add(rdoNghi);
//        ttPanel.setBackground(Color.WHITE);
//        rdoHoatDong.setSelected(true);
//        formPanel.add(ttPanel);
//        formPanel.add(new JLabel(""));
//        formPanel.add(new JLabel(""));
//
//        infoPanel.add(formPanel, BorderLayout.CENTER);
//
//        // Cột nút
//        JPanel buttonCol = new JPanel();
//        buttonCol.setLayout(new BoxLayout(buttonCol, BoxLayout.Y_AXIS));
//        buttonCol.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//        buttonCol.setBackground(Color.WHITE);
//
//        btnThem = new JButton("Thêm");
//        btnSua = new JButton("Lưu");
//        btnXoaRong = new JButton("Xóa Rỗng");
//
//        Dimension btnSize = new Dimension(120, 35);
//
//        for (JButton btn : new JButton[]{btnThem, btnSua, btnXoaRong}) {
//            btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
//            btn.setBackground(new Color(220, 230, 250));
//            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
//            btn.setPreferredSize(btnSize);
//            btn.setMaximumSize(btnSize);
//            btn.setMinimumSize(btnSize); // (optional) để chắc chắn layout không co lại
//            buttonCol.add(btn);
//            buttonCol.add(Box.createVerticalStrut(10));
//            btn.addActionListener(this);
//        }
//>>>>>>> 9f14c2473b645f98db78a8eaf804491cde972697
////        for (JButton btn : new JButton[]{btnThem, btnSua, btnXoaRong}) {
////            btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
////            btn.setBackground(new Color(220, 230, 250));
////            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
//<<<<<<< HEAD
////            btn.setPreferredSize(btnSize);
////            btn.setMaximumSize(btnSize);
////            btn.setMinimumSize(btnSize); // (optional) để chắc chắn layout không co lại
//=======
//>>>>>>> 9f14c2473b645f98db78a8eaf804491cde972697
////            buttonCol.add(btn);
////            buttonCol.add(Box.createVerticalStrut(10));
////            btn.addActionListener(this);
////        }
//<<<<<<< HEAD
//////        for (JButton btn : new JButton[]{btnThem, btnSua, btnXoaRong}) {
//////            btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
//////            btn.setBackground(new Color(220, 230, 250));
//////            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
//////            buttonCol.add(btn);
//////            buttonCol.add(Box.createVerticalStrut(10));
//////            btn.addActionListener(this);
//////        }
////
////        infoPanel.add(buttonCol, BorderLayout.EAST);
////
////        // Bảng dữ liệu
////        String[] cols = {"STT", "Mã NV", "Họ Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Email", "Chức Vụ", "Ngày Tạo", "Trạng Thái"};
////        modelNV = new DefaultTableModel(cols, 0);
////        table = new JTable(modelNV);
////        table.addMouseListener(this);
////        add(new JScrollPane(table), BorderLayout.CENTER);
////    }
////
////    private void loadNhanVienToTable() {
////        modelNV.setRowCount(0);
////        ArrayList<NhanVien> list = (ArrayList<NhanVien>) nvDAO.docTuBang();
////        int stt = 1;
////        for (NhanVien nv : list) {
////            modelNV.addRow(new Object[]{
////                    stt++,
////                    nv.getMaNhanVien(),
////                    nv.getHoten(),
////                    nv.isGioiTinh() ? "Nam" : "Nữ",
////                    nv.getNgaySinh(),
////                    nv.getSoDienThoai(),
////                    nv.getEmail(),
////                    nv.isChucVu() ? "Quản lý" : "Nhân viên",
////                    nv.getNgayTao(),
////                    nv.isTrangThai() ? "Đang làm" : "Nghỉ"
////            });
////        }
////    }
////
////    private void clearForm() {
////        txtMaNV.setText("");
////        txtHoTen.setText("");
////        txtSoDT.setText("");
////        txtEmail.setText("");
////        rdoNu.setSelected(true);
////        rdoNhanVien.setSelected(true);
////        rdoHoatDong.setSelected(true);
////        dateNgaySinh.setDate(null);
////        dateNgayTao.setDate(new Date());
////    }
////
////    private NhanVien getFormData() {
////        try {
////            // Lấy dữ liệu từ form
////            String ma = txtMaNV.getText().trim();
////            String ten = txtHoTen.getText().trim();
////            String sdt = txtSoDT.getText().trim();
////            String email = txtEmail.getText().trim();
////            boolean gt = rdoNam.isSelected();
////            boolean cv = rdoQuanLy.isSelected();
////            boolean tt = rdoHoatDong.isSelected();
////
////            LocalDate ngaySinh = null, ngayTao = null;
//////            if (dateNgaySinh.getDate() != null)
//////                ngaySinh = new java.sql.Date(dateNgaySinh.getDate().getTime()).toLocalDate();
//////            if (dateNgayTao.getDate() != null)
//////                ngayTao = new java.sql.Date(dateNgayTao.getDate().getTime()).toLocalDate();
//////
//////            // Kiểm tra mã nhân viên: Phải có định dạng NV+NgàyThángNăm+MãSố
//////            if (ma.isEmpty()) {
//////                JOptionPane.showMessageDialog(this, "Mã nhân viên không được rỗng!");
//////                return null;
//////            }
////            
////            // Kiểm tra ngày sinh: không được để trống
////           
////
////            if (dateNgayTao.getDate() != null)
////                ngayTao = new java.sql.Date(dateNgayTao.getDate().getTime()).toLocalDate();
////            if (!ma.matches("^NV\\d{8}\\d{3}$")) { // Định dạng NVddMMyyyyXXX
////                JOptionPane.showMessageDialog(this, "Mã nhân viên phải có định dạng NVddMMyyyyXXX (Ví dụ: NV20092025001)");
////                return null;
////            }
////
////         // Kiểm tra họ tên: Chỉ cho phép ký tự chữ (không có dấu) và khoảng trắng, tối đa 50 ký tự và không được rỗng
////            if (ten.isEmpty() || ten.length() > 50 || !ten.matches("^[a-zA-Z\\s]{1,50}$")) {
////                JOptionPane.showMessageDialog(this, "Họ tên không hợp lệ! (Chỉ được phép chứa chữ cái không dấu và khoảng trắng, không quá 50 ký tự)");
////                return null;
////            }
////
////
////            // Kiểm tra số điện thoại: Phải có 10 chữ số
////            if (sdt.isEmpty() || !sdt.matches("\\d{10}")) {
////                JOptionPane.showMessageDialog(this, "Số điện thoại phải là số và có 10 ký tự!");
////                return null;
////            }
////            
////            
////            if (dateNgaySinh.getDate() == null) {
////                JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống!");
////                return null;
////            } else {
////                ngaySinh = new java.sql.Date(dateNgaySinh.getDate().getTime()).toLocalDate();
////            }
////
////            // Kiểm tra ngày sinh: Phải lớn hơn 18 tuổi
////            LocalDate today = LocalDate.now();
////            if (ngaySinh != null && ngaySinh.plusYears(18).isAfter(today)) {
////                JOptionPane.showMessageDialog(this, "Người dùng phải trên 18 tuổi!");
////                return null;
////            }
////            
////            // Kiểm tra email: Phải có dạng chuẩn
////            if (email.isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
////                JOptionPane.showMessageDialog(this, "Email không hợp lệ (abc@gmail.com)!");
////                return null;
////            }
////
////            // Tạo đối tượng NhanVien với dữ liệu hợp lệ
////            return new NhanVien(ma, ten, sdt, email, cv, ngayTao, tt, gt, ngaySinh);
////        } catch (Exception e) {
////            JOptionPane.showMessageDialog(this, "Lỗi đọc dữ liệu từ form!");
////            return null;
////        }
////    }
////
////
////    // ===== XỬ LÝ SỰ KIỆN =====
////    @Override
////    public void actionPerformed(ActionEvent e) {
////        Object o = e.getSource();
////        if (o.equals(btnThem)) {
////            NhanVien nv = getFormData();
////            if (nv != null) {
////                if (nvDAO.create(nv)) {
////                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
////                    loadNhanVienToTable();
////                    clearForm();
////                } else {
////                    JOptionPane.showMessageDialog(this, "Thêm thất bại!");
////                }
////            }
////        } else if (o.equals(btnSua)) {
////            NhanVien nv = getFormData();
////            if (nv != null) {
////                if (nvDAO.update(nv)) {
////                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
////                    loadNhanVienToTable();
////                } else {
////                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
////                }
////            }
////        } else if (o.equals(btnXoaRong)) {
////            clearForm();
////        }
////    }
////
////    @Override
////    public void mouseClicked(MouseEvent e) {
////        int row = table.getSelectedRow();
////        if (row >= 0) {
////            txtMaNV.setText(modelNV.getValueAt(row, 1).toString());
////            txtHoTen.setText(modelNV.getValueAt(row, 2).toString());
////            String gt = modelNV.getValueAt(row, 3).toString();
////            rdoNam.setSelected(gt.equalsIgnoreCase("Nam"));
////            rdoNu.setSelected(gt.equalsIgnoreCase("Nữ"));
////
////            Object ns = modelNV.getValueAt(row, 4);
////            if (ns != null) dateNgaySinh.setDate(java.sql.Date.valueOf(ns.toString()));
////
////            txtSoDT.setText(modelNV.getValueAt(row, 5).toString());
////            txtEmail.setText(modelNV.getValueAt(row, 6).toString());
////            String cv = modelNV.getValueAt(row, 7).toString();
////            rdoQuanLy.setSelected(cv.equalsIgnoreCase("Quản lý"));
////            rdoNhanVien.setSelected(cv.equalsIgnoreCase("Nhân viên"));
////
////            Object nt = modelNV.getValueAt(row, 8);
////            if (nt != null) dateNgayTao.setDate(java.sql.Date.valueOf(nt.toString()));
////
////            String tt = modelNV.getValueAt(row, 9).toString();
////            rdoHoatDong.setSelected(tt.equalsIgnoreCase("Đang làm"));
////            rdoNghi.setSelected(tt.equalsIgnoreCase("Nghỉ"));
////        }
////    }
////
////    @Override public void mousePressed(MouseEvent e) {}
////    @Override public void mouseReleased(MouseEvent e) {}
////    @Override public void mouseEntered(MouseEvent e) {}
////    @Override public void mouseExited(MouseEvent e) {}
////
////    // ===== CHẠY THỬ =====
////    public static void main(String[] args) {
////        SwingUtilities.invokeLater(() -> {
////            JFrame f = new JFrame("Quản Lý Nhân Viên");
////            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////            f.setExtendedState(JFrame.MAXIMIZED_BOTH);
////            f.add(new NhanVien_GUI());
////            f.setVisible(true);
////        });
////    }
////}
//=======
//
//        infoPanel.add(buttonCol, BorderLayout.EAST);
//
//        // Bảng dữ liệu
//        String[] cols = {"STT", "Mã NV", "Họ Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Email", "Chức Vụ", "Ngày Tạo", "Trạng Thái"};
//        modelNV = new DefaultTableModel(cols, 0);
//        table = new JTable(modelNV);
//        table.addMouseListener(this);
//        add(new JScrollPane(table), BorderLayout.CENTER);
//    }
//
//    private void loadNhanVienToTable() {
//        modelNV.setRowCount(0);
//        ArrayList<NhanVien> list = (ArrayList<NhanVien>) nvDAO.docTuBang();
//        int stt = 1;
//        for (NhanVien nv : list) {
//            modelNV.addRow(new Object[]{
//                    stt++,
//                    nv.getMaNhanVien(),
//                    nv.getHoten(),
//                    nv.isGioiTinh() ? "Nam" : "Nữ",
//                    nv.getNgaySinh(),
//                    nv.getSoDienThoai(),
//                    nv.getEmail(),
//                    nv.isChucVu() ? "Quản lý" : "Nhân viên",
//                    nv.getNgayTao(),
//                    nv.isTrangThai() ? "Đang làm" : "Nghỉ"
//            });
//        }
//    }
//
//    private void clearForm() {
//        txtMaNV.setText("");
//        txtHoTen.setText("");
//        txtSoDT.setText("");
//        txtEmail.setText("");
//        rdoNu.setSelected(true);
//        rdoNhanVien.setSelected(true);
//        rdoHoatDong.setSelected(true);
//        dateNgaySinh.setDate(null);
//        dateNgayTao.setDate(new Date());
//    }
//
//    private NhanVien getFormData() {
//        try {
//            // Lấy dữ liệu từ form
//            String ma = txtMaNV.getText().trim();
//            String ten = txtHoTen.getText().trim();
//            String sdt = txtSoDT.getText().trim();
//            String email = txtEmail.getText().trim();
//            boolean gt = rdoNam.isSelected();
//            boolean cv = rdoQuanLy.isSelected();
//            boolean tt = rdoHoatDong.isSelected();
//
//            LocalDate ngaySinh = null, ngayTao = null;
////            if (dateNgaySinh.getDate() != null)
////                ngaySinh = new java.sql.Date(dateNgaySinh.getDate().getTime()).toLocalDate();
////            if (dateNgayTao.getDate() != null)
////                ngayTao = new java.sql.Date(dateNgayTao.getDate().getTime()).toLocalDate();
////
////            // Kiểm tra mã nhân viên: Phải có định dạng NV+NgàyThángNăm+MãSố
////            if (ma.isEmpty()) {
////                JOptionPane.showMessageDialog(this, "Mã nhân viên không được rỗng!");
////                return null;
////            }
//            
//            // Kiểm tra ngày sinh: không được để trống
//           
//
//            if (dateNgayTao.getDate() != null)
//                ngayTao = new java.sql.Date(dateNgayTao.getDate().getTime()).toLocalDate();
//            if (!ma.matches("^NV\\d{8}\\d{3}$")) { // Định dạng NVddMMyyyyXXX
//                JOptionPane.showMessageDialog(this, "Mã nhân viên phải có định dạng NVddMMyyyyXXX (Ví dụ: NV20092025001)");
//                return null;
//            }
//
//         // Kiểm tra họ tên: Chỉ cho phép ký tự chữ (không có dấu) và khoảng trắng, tối đa 50 ký tự và không được rỗng
//            if (ten.isEmpty() || ten.length() > 50 || !ten.matches("^[a-zA-Z\\s]{1,50}$")) {
//                JOptionPane.showMessageDialog(this, "Họ tên không hợp lệ! (Chỉ được phép chứa chữ cái không dấu và khoảng trắng, không quá 50 ký tự)");
//                return null;
//            }
//
//
//            // Kiểm tra số điện thoại: Phải có 10 chữ số
//            if (sdt.isEmpty() || !sdt.matches("\\d{10}")) {
//                JOptionPane.showMessageDialog(this, "Số điện thoại phải là số và có 10 ký tự!");
//                return null;
//            }
//            
//            
//            if (dateNgaySinh.getDate() == null) {
//                JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống!");
//                return null;
//            } else {
//                ngaySinh = new java.sql.Date(dateNgaySinh.getDate().getTime()).toLocalDate();
//            }
//
//            // Kiểm tra ngày sinh: Phải lớn hơn 18 tuổi
//            LocalDate today = LocalDate.now();
//            if (ngaySinh != null && ngaySinh.plusYears(18).isAfter(today)) {
//                JOptionPane.showMessageDialog(this, "Người dùng phải trên 18 tuổi!");
//                return null;
//            }
//            
//            // Kiểm tra email: Phải có dạng chuẩn
//            if (email.isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
//                JOptionPane.showMessageDialog(this, "Email không hợp lệ (abc@gmail.com)!");
//                return null;
//            }
//
//            // Tạo đối tượng NhanVien với dữ liệu hợp lệ
//            return new NhanVien(ma, ten, sdt, email, cv, ngayTao, tt, gt, ngaySinh);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Lỗi đọc dữ liệu từ form!");
//            return null;
//        }
//    }
//
//
//    // ===== XỬ LÝ SỰ KIỆN =====
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object o = e.getSource();
//        if (o.equals(btnThem)) {
//            NhanVien nv = getFormData();
//            if (nv != null) {
//                if (nvDAO.create(nv)) {
//                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
//                    loadNhanVienToTable();
//                    clearForm();
//                } else {
//                    JOptionPane.showMessageDialog(this, "Thêm thất bại!");
//                }
//            }
//        } else if (o.equals(btnSua)) {
//            NhanVien nv = getFormData();
//            if (nv != null) {
//                if (nvDAO.update(nv)) {
//                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
//                    loadNhanVienToTable();
//                } else {
//                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
//                }
//            }
//        } else if (o.equals(btnXoaRong)) {
//            clearForm();
//        }
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        int row = table.getSelectedRow();
//        if (row >= 0) {
//            txtMaNV.setText(modelNV.getValueAt(row, 1).toString());
//            txtHoTen.setText(modelNV.getValueAt(row, 2).toString());
//            String gt = modelNV.getValueAt(row, 3).toString();
//            rdoNam.setSelected(gt.equalsIgnoreCase("Nam"));
//            rdoNu.setSelected(gt.equalsIgnoreCase("Nữ"));
//
//            Object ns = modelNV.getValueAt(row, 4);
//            if (ns != null) dateNgaySinh.setDate(java.sql.Date.valueOf(ns.toString()));
//
//            txtSoDT.setText(modelNV.getValueAt(row, 5).toString());
//            txtEmail.setText(modelNV.getValueAt(row, 6).toString());
//            String cv = modelNV.getValueAt(row, 7).toString();
//            rdoQuanLy.setSelected(cv.equalsIgnoreCase("Quản lý"));
//            rdoNhanVien.setSelected(cv.equalsIgnoreCase("Nhân viên"));
//
//            Object nt = modelNV.getValueAt(row, 8);
//            if (nt != null) dateNgayTao.setDate(java.sql.Date.valueOf(nt.toString()));
//
//            String tt = modelNV.getValueAt(row, 9).toString();
//            rdoHoatDong.setSelected(tt.equalsIgnoreCase("Đang làm"));
//            rdoNghi.setSelected(tt.equalsIgnoreCase("Nghỉ"));
//        }
//    }
//
//    @Override public void mousePressed(MouseEvent e) {}
//    @Override public void mouseReleased(MouseEvent e) {}
//    @Override public void mouseEntered(MouseEvent e) {}
//    @Override public void mouseExited(MouseEvent e) {}
//
//    // ===== CHẠY THỬ =====
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame f = new JFrame("Quản Lý Nhân Viên");
//            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            f.setExtendedState(JFrame.MAXIMIZED_BOTH);
//            f.add(new NhanVien_GUI());
//            f.setVisible(true);
//        });
//    }
//}
//>>>>>>> 9f14c2473b645f98db78a8eaf804491cde972697
