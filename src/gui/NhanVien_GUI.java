package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.NhanVien_DAO;

public class NhanVien_GUI extends JPanel implements ActionListener, MouseListener {

    private JTextField txtMaNV, txtHoTen, txtSoDT, txtEmail;
    private JRadioButton rdoNam, rdoNu, rdoQuanLy, rdoNhanVien, rdoHoatDong, rdoNghi;
    private JTable table;
    private JDateChooser dateNgaySinh, dateNgayTao;
    NhanVien_DAO dsnv = new NhanVien_DAO();
	private DefaultTableModel dl;

    public NhanVien_GUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // ==== TITLE ====
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setForeground(new Color(30, 60, 114));
        add(lblTitle, BorderLayout.NORTH);

        // ==== THÔNG TIN NHÂN VIÊN ====
        JPanel infoPanel = new JPanel(new BorderLayout(10, 10));
        infoPanel.setBorder(new TitledBorder("Thông tin nhân viên"));
        infoPanel.setBackground(Color.WHITE);
        add(infoPanel, BorderLayout.NORTH);

        // === FORM BÊN TRÁI ===
        JPanel formPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        formPanel.setBackground(Color.WHITE);

        // Hàng 1
        formPanel.add(new JLabel("Mã nhân viên:"));
        txtMaNV = new JTextField();
        txtMaNV.setEditable(false);
        formPanel.add(txtMaNV);

        formPanel.add(new JLabel("Họ tên:"));
        txtHoTen = new JTextField();
        formPanel.add(txtHoTen);

        // Hàng 2 - Giới tính
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

        // Mặc định chọn Nữ
        rdoNu.setSelected(true);

        formPanel.add(new JLabel("Ngày sinh:"));
        dateNgaySinh = new JDateChooser();
        dateNgaySinh.setDateFormatString("dd/MM/yyyy");
        formPanel.add(dateNgaySinh);

        // Hàng 3
        formPanel.add(new JLabel("Số điện thoại:"));
        txtSoDT = new JTextField();
        formPanel.add(txtSoDT);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        // Hàng 4 - Chức vụ
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

        // Mặc định chọn Nhân viên
        rdoNhanVien.setSelected(true);

        formPanel.add(new JLabel("Ngày tạo:"));
        dateNgayTao = new JDateChooser();
        dateNgayTao.setDateFormatString("dd/MM/yyyy");
        dateNgayTao.setDate(new Date()); // mặc định ngày hiện tại
        formPanel.add(dateNgayTao);

        // Hàng 5 - Trạng thái
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

        // Mặc định chọn Đang làm
        rdoHoatDong.setSelected(true);

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

        add(tablePanel, BorderLayout.CENTER);
    }

    private void btnColumnStyle(JButton btn) {
        btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn.setBackground(new Color(220, 230, 250));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 200)));
    }

    // Test panel độc lập
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame testFrame = new JFrame("Test Panel NhanVien");
            testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            testFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            testFrame.add(new NhanVien_GUI());
            testFrame.setVisible(true);
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
