package gui;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import dao.ChiPhiPhatSinh_DAO;
import dao.ChiTietChiPhiPhatSinh_DAO;
import dao.ChiTietPhieuDatPhong_DAO;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import dao.NhanVien_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.ChiPhiPhatSinh;
import entity.ChiTietPhieuDatPhong;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

public class TaoPhieuDatPhong_GUI extends JPanel implements ActionListener, MouseListener {
    private static final long serialVersionUID = 1L;

    // ====== KHAI BÁO TOÀN CỤC ======
    private JDateChooser dateNgayNhan, dateNgayTra;
    private JTextField txtSDT, txtTenKH, txtNgayTao, txtTienCoc, txtNV, txtTimPhong, txtMKH, txtMPDP;
    private JCheckBox chkVN;
    // <-- DefaultTableModel toàn cục (đã tách ra)
    private DefaultTableModel dlp, dlctps;
    private JComboBox<String> cboTrangThai, cboChiPhi;
    private JButton btnInPhieu, btnThem, btnXoa, btnThemCP, btnXoaCP, btnLuu, btnHuy, btnTT, btnThoat, btnCapNhat,btn;
    private JTable tblPhong, tblChiPhi;
    private JLabel lblTongTien, lblTongCP, lblTongTatCa;
    private Color mauXanhDam, mauVangDong;
    private JPanel pDanhSachPhong,pCapNhat;
    private LoaiPhong_DAO dslp;
    private Phong_DAO dsp;
    private ChiTietPhieuDatPhong_DAO dsctpdp;
    private PhieuDatPhong_DAO pdp;
    private KhachHang_DAO khd;
    private NhanVien_DAO nvd;
    private ChiPhiPhatSinh_DAO cppsd;
    private ChiTietChiPhiPhatSinh_DAO ctcppsd;
    private LoaiPhong_DAO lpd;
    

    public TaoPhieuDatPhong_GUI(String maNV) {
        setLayout(new BorderLayout());
        setBackground(new Color(249, 249, 249));

         mauXanhDam = new Color(30, 61, 89);
         mauVangDong = new Color(212, 175, 55);
        Font fontTieuDe = new Font("Segoe UI Semibold", Font.PLAIN, 16);
        
        dslp= new LoaiPhong_DAO();
        dsp= new Phong_DAO();
        dsctpdp= new ChiTietPhieuDatPhong_DAO();
        pdp= new PhieuDatPhong_DAO();
        khd= new KhachHang_DAO();
        nvd= new NhanVien_DAO();
        cppsd= new ChiPhiPhatSinh_DAO();
        ctcppsd= new ChiTietChiPhiPhatSinh_DAO();
        lpd= new LoaiPhong_DAO();
        
        txtNgayTao= new JTextField();
        txtNV= new JTextField();
        txtSDT= new JTextField();
        txtTenKH= new JTextField();
        txtTienCoc= new JTextField("");
        txtTimPhong= new JTextField();
        txtMKH= new JTextField();
       txtMPDP= new JTextField();

        // ====== CHIA LÀM 2 PHẦN: TRÁI (70%) & PHẢI (30%) ======
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(0.7);
        splitPane.setResizeWeight(0.7);
        splitPane.setContinuousLayout(true);
        splitPane.setBorder(null);
        splitPane.setBackground(new Color(249, 249, 249));

        // ====== PANEL TRÁI ======
        JPanel pLeft = new JPanel();
        pLeft.setLayout(new BoxLayout(pLeft, BoxLayout.Y_AXIS));
        pLeft.setBackground(new Color(249, 249, 249));
        pLeft.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));

     // --- Khung thông tin phiếu đặt phòng ---
        JPanel pThongTin = new JPanel(new GridBagLayout());
        pThongTin.setBackground(Color.WHITE);
        pThongTin.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(mauXanhDam, 1, true),
                "Thông tin phiếu đặt phòng",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                fontTieuDe,
                mauXanhDam));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        Dimension inputSize = new Dimension(140, 28); // nhỏ lại chút cho vừa 3 phần

        // ====== HÀNG 0: Mã phiếu - Ngày nhận - Ngày trả ======
        gbc.gridy = 0;

        // Mã phiếu
        gbc.gridx = 0;
        pThongTin.add(new JLabel("Mã phiếu:"), gbc);

        gbc.gridx = 1;
        txtMPDP = new JTextField(taoMaPhieuDatPhongTuDong());
        txtMPDP.setPreferredSize(inputSize);
        txtMPDP.setEditable(false);
        pThongTin.add(txtMPDP, gbc);

        
     // Ngày nhận
        gbc.gridx = 2;
        pThongTin.add(new JLabel("Ngày nhận:"), gbc);

        gbc.gridx = 3;
        dateNgayNhan = new JDateChooser();
        dateNgayNhan.setPreferredSize(inputSize);
        dateNgayNhan.setDateFormatString("yyyy/MM/dd"); 
        pThongTin.add(dateNgayNhan, gbc);

        // Ngày trả
        gbc.gridx = 4;
        pThongTin.add(new JLabel("Ngày trả:"), gbc);

        gbc.gridx = 5;
        dateNgayTra = new JDateChooser();
        dateNgayTra.setPreferredSize(inputSize);
        dateNgayTra.setDateFormatString("yyyy/MM/dd"); 
        pThongTin.add(dateNgayTra, gbc);


        // ====== HÀNG 1: Mã KH - Tên KH ======
        gbc.gridy = 1;
        gbc.gridx = 0;
        pThongTin.add(new JLabel("Mã KH:"), gbc);

        gbc.gridx = 1;
        txtMKH = new JTextField(taoMaKhachHangTuDong());
        txtMKH.setEditable(false);
        pThongTin.add(txtMKH, gbc);

        gbc.gridx = 2;
        pThongTin.add(new JLabel("Tên KH:"), gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 1; // cho tên KH chiếm dài hơn
        txtTenKH = new JTextField();
        pThongTin.add(txtTenKH, gbc);
        gbc.gridwidth = 1; // reset lại

        // ====== HÀNG 2: Số điện thoại - Người Việt Nam ======
        gbc.gridy = 2;
        gbc.gridx = 0;
        pThongTin.add(new JLabel("Số điện thoại:"), gbc);

        gbc.gridx = 1;
        txtSDT = new JTextField();
        pThongTin.add(txtSDT, gbc);

        gbc.gridx = 2;
        pThongTin.add(new JLabel("Người Việt Nam:"), gbc);

        gbc.gridx = 3;
        chkVN = new JCheckBox("Có");
        chkVN.setBackground(Color.WHITE);
        chkVN.setSelected(true);
        pThongTin.add(chkVN, gbc);

        // ====== HÀNG 3: Ngày tạo - Tiền cọc ======
        gbc.gridy = 3;
        gbc.gridx = 0;
        pThongTin.add(new JLabel("Ngày tạo:"), gbc);

        gbc.gridx = 1;
        txtNgayTao = new JTextField(LocalDate.now().toString());
        txtNgayTao.setEditable(false);
        pThongTin.add(txtNgayTao, gbc);

        gbc.gridx = 2;
        pThongTin.add(new JLabel("Tiền cọc:"), gbc);

        gbc.gridx = 3;
        txtTienCoc = new JTextField();
        pThongTin.add(txtTienCoc, gbc);


        // ====== HÀNG 5: Trạng thái - Nút in phiếu ======
        gbc.gridy = 4;
        gbc.gridx = 0;
        pThongTin.add(new JLabel("Trạng thái:"), gbc);

        gbc.gridx = 1;
        cboTrangThai = new JComboBox<>(new String[]{"Đã đặt", "Đang ở", "Hoàn thành"});
        pThongTin.add(cboTrangThai, gbc);

        gbc.gridx = 3;
        btnInPhieu = new JButton("🖨 In phiếu");
        btnInPhieu.setBackground(mauVangDong);
        btnInPhieu.setForeground(mauXanhDam);
        btnInPhieu.setFocusPainted(false);
        pThongTin.add(btnInPhieu, gbc);

        // --- Khung chi tiết phòng thuê ---
        JPanel pChiTietPhong = new JPanel(new BorderLayout());
        pChiTietPhong.setBackground(Color.WHITE);
        pChiTietPhong.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(mauXanhDam, 1, true),
                "Chi tiết phòng thuê",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                fontTieuDe,
                mauXanhDam));

        // ====== KHỞI TẠO DefaultTableModel TOÀN CỤC và JTable sử dụng model này ======
        String[] cols = {"STT", "Mã phòng", "Loại phòng", "Ngày nhận thực", "Ngày trả thực", "Số đêm", "Giá", "Thành tiền"};
        dlp = new DefaultTableModel(cols, 0);
        tblPhong = new JTable(dlp);
        pChiTietPhong.add(new JScrollPane(tblPhong), BorderLayout.CENTER);

        JPanel pPhongBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pPhongBtn.setBackground(Color.WHITE);
        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xóa");
        lblTongTien = new JLabel("Tổng tiền: 0 VNĐ");
        pPhongBtn.add(btnThem);
        pPhongBtn.add(btnXoa);
        pPhongBtn.add(lblTongTien);
        pChiTietPhong.add(pPhongBtn, BorderLayout.SOUTH);

        pLeft.add(pThongTin);
        pLeft.add(Box.createVerticalStrut(10));
        pLeft.add(pChiTietPhong);

        // ====== PANEL PHẢI ======
        JPanel pRight = new JPanel();
        pRight.setLayout(new BoxLayout(pRight, BoxLayout.Y_AXIS));
        pRight.setBackground(new Color(249, 249, 249));
        pRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 15));

        // --- Danh sách phòng trống ---
        pDanhSachPhong = new JPanel();
        pDanhSachPhong.setLayout(new BoxLayout(pDanhSachPhong, BoxLayout.Y_AXIS));
        pDanhSachPhong.setBackground(Color.WHITE);
        pDanhSachPhong.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(mauXanhDam, 1, true),
                "Danh sách phòng trống",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                fontTieuDe,
                mauXanhDam));

        // 🔹 Thanh tiêu đề có nút “Cập nhật”
        pCapNhat = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        pCapNhat.setBackground(Color.WHITE);
        btnCapNhat = new JButton("🔄 Cập nhật");
        btnCapNhat.setFocusPainted(false);
        btnCapNhat.setBackground(mauVangDong);
        btnCapNhat.setForeground(mauXanhDam);
        pCapNhat.add(btnCapNhat);
        pDanhSachPhong.add(pCapNhat);
        
        
       

        JScrollPane scrPhongTrong = new JScrollPane(pDanhSachPhong);
        scrPhongTrong.setBorder(null);
        scrPhongTrong.getVerticalScrollBar().setUnitIncrement(16);
        
        scrPhongTrong.setPreferredSize(new Dimension(0, 350));

        // --- Chi phí phát sinh ---
        JPanel pChiPhi = new JPanel(new BorderLayout());
        pChiPhi.setBackground(Color.WHITE);
        pChiPhi.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(mauXanhDam, 1, true),
                "Chi phí phát sinh",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                fontTieuDe,
                mauXanhDam));
       
        JPanel pChiPhiTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pChiPhi.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200)); // chỉ cao 250px
        pChiPhiTop.setBackground(Color.WHITE);
        cboChiPhi = new JComboBox<String>();
        btnThemCP = new JButton("Thêm");
        btnXoaCP = new JButton("Xóa");
        pChiPhiTop.add(new JLabel("Loại chi phí:"));
        pChiPhiTop.add(cboChiPhi);
        pChiPhiTop.add(btnThemCP);
        pChiPhiTop.add(btnXoaCP);
        pChiPhi.add(pChiPhiTop, BorderLayout.NORTH);

        // ====== KHỞI TẠO DefaultTableModel cho chi phí phát sinh (toàn cục) ======
        dlctps = new DefaultTableModel(new String[]{"Tên chi phí", "Giá", "Số lượng", "Thành tiền","Ma"}, 0);
        tblChiPhi = new JTable(dlctps);
        
        tblChiPhi.getColumnModel().getColumn(4).setMinWidth(0);
        tblChiPhi.getColumnModel().getColumn(4).setMaxWidth(0);
        tblChiPhi.getColumnModel().getColumn(4).setWidth(0);
        
        pChiPhi.add(new JScrollPane(tblChiPhi), BorderLayout.CENTER);

        lblTongCP = new JLabel("Tổng chi phí: 0 VNĐ", SwingConstants.RIGHT);
        lblTongCP.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pChiPhi.add(lblTongCP, BorderLayout.SOUTH);

        // --- Khu vực nhân viên & tổng tiền cuối ---
        JPanel pBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        pBottom.setBackground(new Color(249, 249, 249));
        JLabel lblNV = new JLabel("Nhân viên: ");
        txtNV = new JTextField(maNV);
        txtNV.setEditable(false);
        btnLuu = new JButton("💾 Lưu");
        btnHuy = new JButton("❌ Hủy đặt phòng");
        btnTT = new JButton("💳 Thanh toán");
        btnThoat = new JButton("🚪 Thoát");
        lblTongTatCa = new JLabel("Tổng thanh toán: 0 VNĐ");

        pBottom.add(lblNV);
        pBottom.add(txtNV);
        pBottom.add(btnLuu);
        pBottom.add(btnHuy);
        pBottom.add(btnTT);
        pBottom.add(btnThoat);
        pBottom.add(lblTongTatCa);

        // add các phần vào pRight
        pRight.add(scrPhongTrong);
        pRight.add(Box.createVerticalStrut(10));
        scrPhongTrong.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400)); // hoặc 350, tuỳ chiều cao bạn muốn
        pRight.add(pChiPhi);

        // ====== ADD 2 PANEL VÀO SPLITPANE ======
        splitPane.setLeftComponent(pLeft);
        splitPane.setRightComponent(pRight);

        // ====== ADD TO MAIN PANEL ======
        add(splitPane, BorderLayout.CENTER);
        add(pBottom, BorderLayout.SOUTH);
        
        
        
        getDSLP();
//        xuLyChonNgay();
        capNhatCBB();
       
        
//        btn.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnHuy.addActionListener(this);
        btnInPhieu.addActionListener(this);
        btnLuu.addActionListener(this);
        btnThem.addActionListener(this);
        btnThemCP.addActionListener(this);
        btnThoat.addActionListener(this);
        btnTT.addActionListener(this);
        btnXoa.addActionListener(this);
        hienThiTienCocVaTienTongTienPhong();

    }

   
  

    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(btnLuu)) {
            boolean tonTai = false;
            int kiemTra=0;
            // Kiểm tra khách hàng đã tồn tại chưa
            for (KhachHang khh : khd.getAllKhachHang()) {
            	if (txtMKH.getText().equals(khh.getMaKhachHang())) {
            	    tonTai = true;

            	    String maKH = txtMKH.getText();
            	    String tenKH = txtTenKH.getText();
            	    String sdt = txtSDT.getText();
            	    boolean vn = chkVN.isSelected();

            	    KhachHang khb = new KhachHang(maKH, tenKH, sdt, vn);
            	    khd.capNhatKhachHang(khb);

            	    JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thành công!");
            	    kiemTra=1;
            	    return; 
            	}
            	
            	

            }

//             Nếu chưa tồn tại thì thêm mới
            if (!tonTai && kiemTraDuLieuNhap()) {
                String maKH = taoMaKhachHangTuDong();
                String tenKH = txtTenKH.getText();
                String sdt = txtSDT.getText();
                boolean vn = chkVN.isSelected();

                KhachHang kh = new KhachHang(maKH, tenKH, sdt, vn);

                if (khd.themKhachHang(kh)) {
                	  kiemTra=1;
                    JOptionPane.showMessageDialog(null, "Thêm thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể thêm!");
                }
            }
            
            List<PhieuDatPhong> dspdptam= pdp.getAllPhieuDatPhong();
            String ma1= txtMPDP.getText();
            boolean trangThaiPhieu= false;
            for(PhieuDatPhong p1: dspdptam) {
            	if(ma1.equals(p1.getMaPhieuDatPhong())) {
            		PhieuDatPhong p11= new PhieuDatPhong(ma1,new KhachHang(txtMKH.getText()), new NhanVien(txtNV.getText()), LocalDate.now(), cboTrangThai.getSelectedItem().toString());
            		pdp.capNhatPhieuDatPhong(p11);
            		trangThaiPhieu= true;
            		JOptionPane.showMessageDialog(null,"Đã cập nhật phiếu đặt phòng!");
            	}
            }
            if(!trangThaiPhieu&&kiemTra==1) {
        		PhieuDatPhong p12= new PhieuDatPhong(taoMaPhieuDatPhongTuDong(),new KhachHang(txtMKH.getText()), new NhanVien(txtNV.getText()), LocalDate.now(), cboTrangThai.getSelectedItem().toString());
        		pdp.themPhieuDatPhong(p12);
        		JOptionPane.showMessageDialog(null,"Đã thêm phiếu đặt phòng!");
        		return;
            }

        }
        else if(o.equals(btnCapNhat)) {
        	getDSLP();
//        	JOptionPane.showMessageDialog(null,"Cap nhat thanh cong!");
        }
        else if(o.equals(btnThoat)) {  
//   			 int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//   		        if (confirm == JOptionPane.YES_OPTION) {
//   		        	System.exit(1);
//   		        }
        	  hienThiTienCocVaTienTongTienPhong();
   		
        }
        else if(o.equals(btnThemCP)) {
        	themCPPSXB();
        	
            
        }
        }
    

    // ========================== KIỂM TRA DỮ LIỆU NHẬP ==========================
    public boolean kiemTraDuLieuNhap() {
        if (dlp.getRowCount()==0) {
            JOptionPane.showMessageDialog(null, "Khách hàng chỉ được thêm chỉ khi phòng được thêm!");
            return false;
        }

        if (dateNgayTra.getDate().before(dateNgayNhan.getDate())) {
            JOptionPane.showMessageDialog(null, "Ngày trả không thể trước ngày nhận!");
            return false;
        }
        if(txtSDT.getText().trim().length()==0) {
        	JOptionPane.showMessageDialog(null,"Vui lòng nhạp số điện thoại!");
        	return false;
        }
        
        String sdt = txtSDT.getText().trim();
        if (!sdt.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại chỉ được chứa chữ số!");
            return false;
        } else if (sdt.length() != 10) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải có đúng 10 số!");
            return false;
        }

        
        for (KhachHang khh : khd.getAllKhachHang()) {
            if (!khh.getMaKhachHang().equals(txtMKH.getText()) &&
                khh.getSoDienThoai().equals(txtSDT.getText())) {
                JOptionPane.showMessageDialog(null, "Số điện thoại này đã tồn tại cho khách hàng khác!");
                return false;
            }
        }

        return true;
    }

    
    
    private void xuLyChonNgay() {
        // Lấy ngày nhận và ngày trả
        Date ngayNhan = dateNgayNhan.getDate();
        Date ngayTra = dateNgayTra.getDate();

        // Nếu chưa chọn đủ 2 ngày
        if (ngayNhan == null || ngayTra == null) {
            return;
        }

        // Lấy ngày hiện tại (bỏ phần giờ để so sánh chuẩn)
        LocalDate homNay = LocalDate.now();
        LocalDate nhan = ngayNhan.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate tra = ngayTra.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // 
        if (nhan.isBefore(homNay)) {
            JOptionPane.showMessageDialog(this,
                "Ngày nhận phải từ hôm nay trở đi!",
                "Lỗi chọn ngày",
                JOptionPane.ERROR_MESSAGE);
            dateNgayNhan.setDate(null);
            return;
        }

        // 
        if (!tra.isAfter(nhan)) {
            JOptionPane.showMessageDialog(this,
                "Ngày trả phải lớn hơn ngày nhận!",
                "Lỗi chọn ngày",
                JOptionPane.ERROR_MESSAGE);
            dateNgayTra.setDate(null);
            return;
        }

        //
        long khoangCach = ChronoUnit.DAYS.between(homNay, tra);
        if (khoangCach > 31) {
            JOptionPane.showMessageDialog(this,
                "Khách sạn chỉ cho phép đặt tối đa 31 ngày kể từ hôm nay!",
                "Lỗi chọn ngày",
                JOptionPane.ERROR_MESSAGE);
            dateNgayTra.setDate(null);
            return;
        }

        // ✅ Nếu hợp lệ — có thể gọi hàm tải phòng trống tại đây
        // loadDanhSachPhongTrong(nhan, tra);
    }

    
    
    
    public void capNhatCBB() {
    	List<ChiPhiPhatSinh> dscpps= cppsd.getAllChiPhiPhatSinh();
    	for(ChiPhiPhatSinh tam: dscpps) {
    		cboChiPhi.addItem(tam.getTenChiPhiPhatSinh() +"-"+ tam.getGia());
    	}
    }
    public void themCPPSXB() {
        // ✅ Lấy mục được chọn trong combobox
        String mucChon = (String) cboChiPhi.getSelectedItem();
        if (mucChon == null || mucChon.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chi phí cần thêm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ✅ Cắt tên chi phí và giá (định dạng: "TênChiPhi-Giá")
        String[] tach = mucChon.split("-");
        if (tach.length != 2) {
            JOptionPane.showMessageDialog(null, "Dữ liệu chi phí không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tenChiPhi = tach[0].trim();
        double gia = 0;
        try {
            gia = Double.parseDouble(tach[1].trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Giá chi phí không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ✅ Nhập số lượng
        String soLuongStr = JOptionPane.showInputDialog(null, "Nhập số lượng cho " + tenChiPhi + ":");
        if (soLuongStr == null) return; // người dùng bấm Cancel

        int soLuongMoi;
        try {
            soLuongMoi = Integer.parseInt(soLuongStr);
            if (soLuongMoi <= 0) {
                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ✅ Kiểm tra trùng chi phí trong bảng
        for (int i = 0; i < dlctps.getRowCount(); i++) {
            String ten = dlctps.getValueAt(i, 0).toString();
            if (ten.equalsIgnoreCase(tenChiPhi)) {
                // Nếu trùng thì cập nhật số lượng
                int soLuongHienTai = Integer.parseInt(dlctps.getValueAt(i, 2).toString());
                int tongSoLuong = soLuongHienTai + soLuongMoi;

                if (tongSoLuong > 50) {
                    JOptionPane.showMessageDialog(null,
                        "Tổng số lượng của " + tenChiPhi + " không được vượt quá 50!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Cập nhật lại số lượng và thành tiền
                double thanhTienMoi = gia * tongSoLuong;
                dlctps.setValueAt(tongSoLuong, i, 2);
                dlctps.setValueAt(thanhTienMoi, i, 3);

//                JOptionPane.showMessageDialog(null, "Đã cập nhật lại số lượng của " + tenChiPhi + " thành " + tongSoLuong + ".");
                return;
            }
        }

        // ✅ Nếu chưa có, thêm mới dòng
        if (soLuongMoi > 50) {
            JOptionPane.showMessageDialog(null, "Số lượng không được vượt quá 50!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ChiPhiPhatSinh tam= cppsd.getChiPhiTheoTen(tenChiPhi.trim());
        double thanhTien = gia * soLuongMoi;
        Object[] dongMoi = { tenChiPhi, gia, soLuongMoi, thanhTien,tam.getMaChiPhiPhatSinh()};
        dlctps.addRow(dongMoi);

//        JOptionPane.showMessageDialog(null, "Đã thêm chi phí: " + tenChiPhi + " thành công!");
    }


    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
    public void getDSLP() {
    	xuLyChonNgay();
        // Lấy danh sách loại phòng từ DB
        List<LoaiPhong> dslpp = dslp.getAllLoaiPhong();

        // Xóa panel cũ nếu có
        pDanhSachPhong.removeAll();
        pDanhSachPhong.add(pCapNhat);

        // Duyệt qua từng loại phòng
        for (LoaiPhong lp : dslpp) {
            // Lấy danh sách phòng thuộc loại đó
            List<Phong> dspp = dsp.getPhongTheoMaLoaiPhong(lp.getMaLoaiPhong());

            // Nếu loại này có phòng thì hiển thị
            if (!dspp.isEmpty()) {
                // Danh sách mã phòng
                List<String> mp = new ArrayList<>();
                for (Phong p : dspp) {
                	if(p.getTrangThai().equals("Trống"))
                    mp.add(p.getMaPhong()); 
                   
                }
               

                // Tạo tiêu đề cho nhóm phòng (theo tên loại + giá)
                String title = lp.getTenLoaiPhong() + " - " + lp.getGia() + " VND / 1 đêm";

                // Thêm section vào panel chính
                pDanhSachPhong.add(createRoomSection(title, mp, mauXanhDam, mauVangDong));
            }
        }

        // Cập nhật lại giao diện sau khi thêm các panel
        pDanhSachPhong.revalidate();
        pDanhSachPhong.repaint();
    }
    
    private void themSuKienChonPhong(JButton nutPhong, String maPhong) {
        nutPhong.addActionListener(e -> {
            // Lấy thông tin phòng theo mã
            Phong phong = dsp.timPhongTheoMa(maPhong); 
            if (phong == null) return;

            // Kiểm tra xem phòng đã tồn tại trong bảng chưa
            for (int i = 0; i < dlp.getRowCount(); i++) {
                String ma = dlp.getValueAt(i, 0).toString();
                if (ma.equals(maPhong)) {
                    JOptionPane.showMessageDialog(this, "Phòng này đã được chọn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    hienThiTienCocVaTienTongTienPhong();
                    return;
                }
            }

            // Thêm phòng vào bảng
            Object[] dongMoi = {
                phong.getMaPhong(),
                phong.getLoaiPhong().getTenLoaiPhong(),
                phong.getLoaiPhong().getGia(),
                phong.getLoaiPhong().getSucChua(),
                "Chưa nhận" // trạng thái mặc định
            };
            dlp.addRow(dongMoi);

            JOptionPane.showMessageDialog(this, "Đã thêm phòng " + maPhong + " vào danh sách!");
        });
    }


    // Sửa nhẹ: dùng model (dlp) để addRow thay vì gọi vào JTable
    public TaoPhieuDatPhong_GUI(String maPhieu, String maKhachHang, String maNV) {
    	this(maNV);
    	hienThiTienCocVaTienTongTienPhong();
        // đảm bảo dsctpdp đã được khởi tạo (nếu cần)
        dsctpdp = new ChiTietPhieuDatPhong_DAO();
        // nếu dlp chưa khởi tạo (trường hợp gọi constructor này trực tiếp), khởi tạo model và table
        if (dlp == null) {
            String[] cols = {"STT", "Mã phòng", "Loại phòng", "Ngày nhận thực", "Ngày trả thực", "Số đêm", "Giá", "Thành tiền"};
            dlp = new DefaultTableModel(cols, 0);
            tblPhong = new JTable(dlp);
        }

        List<ChiTietPhieuDatPhong> dsctpdpp = dsctpdp.getChiTietTheoMaPhieu(maPhieu);
        for (int i = 0; i < dsctpdpp.size(); i++) {
            ChiTietPhieuDatPhong ct = dsctpdpp.get(i);

            long soDemO = ChronoUnit.DAYS.between(ct.getNgayNhanThuc(), ct.getNgayTraThuc());

            // Sử dụng model dlp.addRow(...)
            dlp.addRow(new Object[] {
                i + 1,
                ct.getPhong().getMaPhong(),
                ct.getPhong().getLoaiPhong().getTenLoaiPhong(), 
                ct.getNgayNhanThuc(),
                ct.getNgayTraThuc(),
                soDemO,
                ct.getPhong().getLoaiPhong().getGia(),
                soDemO*ct.getPhong().getLoaiPhong().getGia()
            });
        }
        KhachHang kh= khd.getKhachHangTheoMa(maKhachHang);
        txtMKH.setText(kh.getMaKhachHang());
        txtTenKH.setText(kh.getHoTen());
        txtNV.setText(maNV);
        txtSDT.setText(kh.getSoDienThoai());
        txtMPDP.setText(maPhieu);
    }
    
    private JPanel createRoomSection(String title, List<String> dsMaPhong, Color mauXanhDam, Color mauVangDong) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);

        // ====== Tiêu đề loại phòng ======
        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setForeground(mauXanhDam);
        lbl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
        p.add(lbl, BorderLayout.NORTH);

        // ====== Grid chứa các phòng ======
        JPanel grid = new JPanel(new GridLayout(0, 8, 8, 8)); // 8 cột, khoảng cách 8px
        grid.setBackground(Color.WHITE);
        grid.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // ====== Tạo nút cho từng mã phòng ======
        for (String maPhong : dsMaPhong) {
            JButton btnPhong = new JButton(maPhong);
            btnPhong.setBackground(Color.WHITE);
            btnPhong.setFocusPainted(false);
            btnPhong.setBorder(BorderFactory.createLineBorder(mauXanhDam, 1, true));
            btnPhong.setForeground(mauXanhDam);
            btnPhong.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            btnPhong.setMargin(new Insets(2, 2, 2, 2));

            //  Hiệu ứng hover
            btnPhong.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnPhong.setBackground(mauVangDong);
                    btnPhong.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnPhong.setBackground(Color.WHITE);
                    btnPhong.setForeground(mauXanhDam);
                }
            });

            //  Sự kiện khi người dùng click vào phòng
            btnPhong.addActionListener(e -> {
                Phong phong = dsp.timPhongTheoMa(maPhong);
                if (phong == null) return;

                // ⚠️ Kiểm tra ngày nhận & ngày trả đã được chọn chưa
                if (dateNgayNhan.getDate() == null || dateNgayTra.getDate() == null) {
                    JOptionPane.showMessageDialog(null,
                        "Vui lòng chọn ngày nhận và ngày trả đúng trước khi chọn phòng!",
                        "Thiếu thông tin",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Chuyển sang LocalDate
                LocalDate ngayNhan = dateNgayNhan.getDate()
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate ngayTra = dateNgayTra.getDate()
                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                // Kiểm tra hợp lệ ngày nhận - ngày trả
                if (ngayTra.isBefore(ngayNhan)) {
                    JOptionPane.showMessageDialog(null,
                        "Ngày trả phải sau ngày nhận!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra trùng phòng trong bảng
                for (int i = 0; i < dlp.getRowCount(); i++) {
                    String ma = dlp.getValueAt(i, 1).toString(); // cột Mã phòng
                    if (ma.equals(maPhong)) {
                        JOptionPane.showMessageDialog(null,
                            "Phòng này đã được chọn!",
                            "Thông báo",
                            JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                //  Tính số đêm ở và thành tiền
                long soDemO = ChronoUnit.DAYS.between(ngayNhan, ngayTra);
                double gia = phong.getLoaiPhong().getGia();
                double thanhTien = soDemO * gia;

                //  Thêm dòng mới vào bảng
//                System.out.println("DEBUG: maPhong=" + phong.getMaPhong() + 
//                        ", tenLoaiPhong=" + phong.getLoaiPhong().getTenLoaiPhong()+ phong.getLoaiPhong().getGia());

                dlp.addRow(new Object[]{
                    dlp.getRowCount() + 1,                 // STT
                    phong.getMaPhong(),                    // Mã phòng
                    phong.getLoaiPhong().getTenLoaiPhong(),// Loại phòng
                    ngayNhan,                              // Ngày nhận
                    ngayTra,                               // Ngày trả
                    soDemO,                                // Số đêm ở
                    gia,                                   // Giá/đêm
                    thanhTien                              // Thành tiền
                });

                JOptionPane.showMessageDialog(null,
                    "Đã thêm phòng " + maPhong + " vào danh sách!");
            });

            grid.add(btnPhong);
        }

        // ====== Thêm grid vào panel ======
        p.add(grid, BorderLayout.CENTER);
        return p;
    }

    public String taoMaPhieuDatPhongTuDong() {
    	
        // Lấy ngày hiện tại
        LocalDate ngayHienTai = LocalDate.now();
        String ngay = String.format("%02d", ngayHienTai.getDayOfMonth());
        String thang = String.format("%02d", ngayHienTai.getMonthValue());
        String nam = String.valueOf(ngayHienTai.getYear());

        // Lấy danh sách phiếu hiện có (từ database hoặc list)
        List<PhieuDatPhong> danhSachPhieu = pdp.getAllPhieuDatPhong();

        // Đếm số phiếu trong ngày hiện tại
        int dem = 0;
        for (PhieuDatPhong p : danhSachPhieu) {
            if (p.getMaPhieuDatPhong().contains("PDP" + ngay + thang + nam)) {
                dem++;
            }
        }

        // Tăng số thứ tự lên 1
        dem++;

        // Ghép lại chuỗi mã theo định dạng
        String maPhieu = String.format("PDP%s%s%s%03d", ngay, thang, nam, dem);
        return maPhieu;
    }

    public String taoMaKhachHangTuDong() {
        // Lấy ngày hiện tại
        LocalDate ngayHienTai = LocalDate.now();
        String ngay = String.format("%02d", ngayHienTai.getDayOfMonth());
        String thang = String.format("%02d", ngayHienTai.getMonthValue());
        String nam = String.valueOf(ngayHienTai.getYear());

        // Lấy danh sách khách hàng hiện có (từ database hoặc DAO)
        List<KhachHang> danhSachKhachHang = khd.getAllKhachHang();

        // Đếm số khách hàng tạo trong ngày hiện tại
        int dem = 0;
        for (KhachHang kh : danhSachKhachHang) {
            if (kh.getMaKhachHang().contains("KH" + ngay + thang + nam)) {
                dem++;
            }
        }

        // Tăng số thứ tự
        dem++;

        // Ghép chuỗi mã theo định dạng KHddMMyyyy###
        String maKH = String.format("KH%s%s%s%03d", ngay, thang, nam, dem);
        return maKH;
    }
    public PhieuDatPhong doiTuongTongTienPhong() {
    	System.out.println("DEBUG: model dlp = " + dlp);
    	System.out.println("DEBUG: table model = " + tblPhong.getModel());
    	System.out.println("DEBUG: so dong dlp = " + dlp.getRowCount());
    	System.out.println("DEBUG: so dong table = " + tblPhong.getRowCount());
    	System.out.println(dlp.getRowCount());

        PhieuDatPhong tam = new PhieuDatPhong();

        for (int i = 0; i < dlp.getRowCount(); i++) {
        	String strNgayNhan = dlp.getValueAt(i, 3).toString(); // ví dụ cột 3 là ngày nhận
        	String strNgayTra  = dlp.getValueAt(i, 4).toString(); // ví dụ cột 4 là ngày trả
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        	LocalDate ngayNhan = LocalDate.parse(strNgayNhan, formatter);
        	LocalDate ngayTra  = LocalDate.parse(strNgayTra, formatter);

            String maPhong = dlp.getValueAt(i, 1).toString().trim();
            Phong p = dsp.timPhongTheoMa(maPhong);
            if(p==null) {
            	JOptionPane.showMessageDialog(null,"Tìm thấy");
            	System.out.println("Gia Phong"+ p.getLoaiPhong().getGia());
            	
            }
            if (p == null) {
                System.out.println("Không tìm thấy phòng cho mã: " + maPhong);
                
                continue;
            }


            String trangThai = cboTrangThai.getSelectedItem().toString();

            ChiTietPhieuDatPhong ct = new ChiTietPhieuDatPhong(
                tam,
                p,
                ngayNhan,
                ngayTra,
                trangThai
            );
            tam.themChiTiet(ct);
           
        }
        System.out.println(tam.getTienCoc());
        return tam;
    }


    public void hienThiTienCocVaTienTongTienPhong() {
        PhieuDatPhong phieu = doiTuongTongTienPhong();

        // Cập nhật giá trị hiển thị
        txtTienCoc.setText(String.format("%.0f", phieu.getTienCoc())); // hiển thị tiền cọc không có số thập phân
        lblTongTien.setText( ("Tổng tiền: "+String.format("%.0f", phieu.getTongTienPhong())));
    }

}
