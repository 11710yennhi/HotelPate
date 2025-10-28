package gui;

import javax.swing.*;


import dao.ChiTietPhieuDatPhong_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.PhieuDatPhong_DAO;
import entity.ChiTietPhieuDatPhong;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DanhSachPhieuDatPhong_GUI extends JPanel implements ActionListener, MouseListener {
    private JPanel pnlCards;
    private JTextField txtSDT, txtPDP;
    private JButton btsdt, btmp, btCapNhat;
    private PhieuDatPhong_DAO pdp;
    private KhachHang_DAO khd;
    private ChiTietPhieuDatPhong_DAO ctpdp;
    private NhanVien_DAO nvd;
    private List<PhieuDatPhong> dspdp;
    

    public DanhSachPhieuDatPhong_GUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        pdp = new PhieuDatPhong_DAO();
        khd= new KhachHang_DAO();
        ctpdp= new ChiTietPhieuDatPhong_DAO();
        nvd= new NhanVien_DAO();
        
        // --- Thanh tìm kiếm ---
        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.setBackground(new Color(240, 240, 240));
        pnlTop.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // --- Ô tìm kiếm ---
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        pnlSearch.setBackground(pnlTop.getBackground());
        
        pnlSearch.add(new JLabel("Số điện thoại:"));
        txtSDT = new JTextField(10);
        pnlSearch.add(txtSDT);
        btsdt = new JButton("Tìm");
        pnlSearch.add(btsdt);
        
        pnlSearch.add(new JLabel("Mã Phiếu đặt phòng:"));
        txtPDP = new JTextField(10);
        pnlSearch.add(txtPDP);
        btmp = new JButton("Tìm");
        pnlSearch.add(btmp);
        
        btCapNhat = new JButton("Cập nhật");
        pnlSearch.add(btCapNhat);
        
        pnlTop.add(pnlSearch, BorderLayout.WEST);

        // --- Chú thích trạng thái ---
        JPanel pnlLegend = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        pnlLegend.setBackground(pnlTop.getBackground());
        pnlLegend.add(createLegend(Color.RED, "Tới ngày nhận phòng"));
        pnlLegend.add(createLegend(new Color(102, 187, 106), "Đã đặt"));
        pnlLegend.add(createLegend(new Color(255, 215, 0), "Đang ở"));
        pnlLegend.add(createLegend(new Color(100, 149, 237), "Tới ngày trả phòng"));
        pnlTop.add(pnlLegend, BorderLayout.EAST);
        add(pnlTop, BorderLayout.NORTH);

        // --- Panel chứa card ---
        pnlCards = new JPanel();
        pnlCards.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15)); // ✅ gọn, 6 card/hàng
        pnlCards.setBackground(Color.WHITE);
        pnlCards.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        // --- Scroll ---
        JScrollPane scroll = new JScrollPane(pnlCards);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);

        
        CapNhatPhieuDatPhong();
        btsdt.addActionListener(this);
        btmp.addActionListener(this);
        btCapNhat.addActionListener(this);
    }

   
    
    @Override public void actionPerformed(ActionEvent e) {
    	Object o = e.getSource();
    	
    	if(o.equals(btCapNhat)) {
    		pnlCards.removeAll();
    		CapNhatPhieuDatPhong();
    		 pnlCards.revalidate();
    		    pnlCards.repaint();
    	}
    	if(o.equals(btmp)) {
    		int x=0;
    		List<PhieuDatPhong> dstam= pdp.getAllPhieuDatPhong();
    		String mp= txtPDP.getText().trim();
    		for(PhieuDatPhong p: dstam) {
    			if(mp.equals(p.getMaPhieuDatPhong().trim())) {
    				pnlCards.removeAll();
    				pnlCards.revalidate();
    				pnlCards.repaint();
    				x=1;
    			}
    		}
    		if(x==0) {
    			JOptionPane.showMessageDialog(null, "Không tìm thấy!");
    		}
    				
    	}
    	if (o.equals(btsdt)) {
    	    String sdt = txtSDT.getText().trim();
    	    if (sdt.isEmpty()) {
    	        JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại!");
    	        return;
    	    }

    	    List<KhachHang> dsKhach = khd.timKhachHangTheoSDTGanDung(sdt);

    	    if (dsKhach.isEmpty()) {
    	        JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng có số điện thoại: " + sdt);
    	        return;
    	    }

    	    // --- Lọc phiếu đặt phòng theo mã KH tìm được ---
    	    pnlCards.removeAll();
    	    dspdp = pdp.getAllPhieuDatPhong();

    	    for (PhieuDatPhong phieu : dspdp) {
    	        KhachHang kh = khd.getKhachHangTheoMa(phieu.getKhachHang().getMaKhachHang());

    	        // Kiểm tra xem khách hàng này có nằm trong danh sách kết quả không
    	        boolean khHopLe = dsKhach.stream()
    	                .anyMatch(k -> k.getMaKhachHang().equals(kh.getMaKhachHang()));

    	        if (khHopLe) {
    	            List<ChiTietPhieuDatPhong> dsctpdp = ctpdp.getChiTietTheoMaPhieu(phieu.getMaPhieuDatPhong());
    	            for (ChiTietPhieuDatPhong ct : dsctpdp) {
    	                NhanVien nv = nvd.getNhanVienTheoMa(phieu.getNhanVien().getMaNhanVien());
    	                Color bgColor = Color.LIGHT_GRAY;

    	                if (ct.getNgayNhanThuc().isBefore(LocalDate.now()) && ct.getNgayTraThuc().isAfter(LocalDate.now())) {
    	                    bgColor = new Color(255, 215, 0);
    	                } else if (ct.getNgayNhanThuc().isEqual(LocalDate.now())) {
    	                    bgColor = Color.RED;
    	                } else if (ct.getNgayTraThuc().isEqual(LocalDate.now())) {
    	                    bgColor = new Color(100, 149, 237);
    	                } else if (ct.getNgayNhanThuc().isAfter(LocalDate.now())) {
    	                    bgColor = new Color(102, 187, 106);
    	                }

    	                JButton btnPhieu = new JButton(
    	                        "<html>"
    	                        + "<b>Mã phiếu:</b> " + phieu.getMaPhieuDatPhong()
    	                        + "<br><b>Mã KH:</b> " + kh.getMaKhachHang()
    	                        + "<br><b>Tên KH:</b> " + kh.getHoTen()
    	                        + "<br><b>SĐT:</b> " + kh.getSoDienThoai()
    	                        + "<br><b>Mã NV:</b> " + nv.getMaNhanVien()
    	                        + "</html>"
    	                );

    	                btnPhieu.setPreferredSize(new Dimension(220, 140));
    	                btnPhieu.setBackground(bgColor);
    	                btnPhieu.setFocusPainted(false);
    	                btnPhieu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	                btnPhieu.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    	                btnPhieu.setCursor(new Cursor(Cursor.HAND_CURSOR));

    	                pnlCards.add(btnPhieu);
    	                break;
    	            }
    	        }
    	    }

    	    pnlCards.revalidate();
    	    pnlCards.repaint();
    	}

    }
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Danh sách phiếu đặt phòng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(new DanhSachPhieuDatPhong_GUI());
        frame.setVisible(true);
    }

    public void CapNhatPhieuDatPhong() {
    	
        pnlCards.removeAll();
         dspdp = pdp.getAllPhieuDatPhong();

        for (PhieuDatPhong phieu : dspdp) {
            List<ChiTietPhieuDatPhong> dsctpdp = ctpdp.getChiTietTheoMaPhieu(phieu.getMaPhieuDatPhong());
            for (ChiTietPhieuDatPhong ct : dsctpdp) {
                KhachHang kh = khd.getKhachHangTheoMa(phieu.getKhachHang().getMaKhachHang());
                NhanVien nv= nvd.getNhanVienTheoMa(phieu.getNhanVien().getMaNhanVien());
                Color bgColor = Color.LIGHT_GRAY;
                String trangThai = "";
                if (ct == null) continue; // tránh lỗi null
                
                //  Bỏ qua nếu phiếu đã hết hạn (ngày trả thực nhỏ hơn hôm nay)
                if (ct.getNgayTraThuc().isBefore(LocalDate.now())) {
                    continue;
                }

                if (ct.getNgayNhanThuc().isBefore(LocalDate.now()) && ct.getNgayTraThuc().isAfter(LocalDate.now())) {
                    bgColor = new Color(255, 215, 0);  // đang ở
                    trangThai = "Đang ở";
                } else if (ct.getNgayNhanThuc().isEqual(LocalDate.now())) {
                    bgColor = Color.RED;  // tới ngày nhận phòng
                    trangThai = "Tới nhận";
                } else if (ct.getNgayTraThuc().isEqual(LocalDate.now())) {
                    bgColor = new Color(100, 149, 237);  // tới ngày trả phòng
                    trangThai = "Trả phòng";
                } else if (ct.getNgayNhanThuc().isAfter(LocalDate.now())) {
                    bgColor = new Color(102, 187, 106);  // đã đặt
                    trangThai = "Đã đặt";
                }

                JButton btnPhieu = new JButton(
                	    "<html>"
                	    + "<b>Mã phiếu:</b> " + phieu.getMaPhieuDatPhong()
                	    + "<br><b>Mã KH:</b> " + kh.getMaKhachHang()
                	    + "<br><b>Tên KH:</b> " + kh.getHoTen()
                	    + "<br><b>SĐT:</b> " + kh.getSoDienThoai()
                	    + "<br><b>Mã NV:</b> " + nv.getMaNhanVien()
                	    + "</html>"
                	);

                	btnPhieu.setPreferredSize(new Dimension(220, 140));
                	btnPhieu.setBackground(bgColor);
                	btnPhieu.setFocusPainted(false);
                	btnPhieu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                	btnPhieu.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                	btnPhieu.setCursor(new Cursor(Cursor.HAND_CURSOR));



                btnPhieu.setPreferredSize(new Dimension(200, 120));
                btnPhieu.setBackground(bgColor);
                btnPhieu.setFocusPainted(false);
                btnPhieu.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                btnPhieu.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnPhieu.setFont(new Font("Segoe UI", Font.PLAIN, 13));

                // 🔹 Sự kiện click nút
                btnPhieu.addActionListener(e -> {
                    JFrame taoPhieuFrame = new JFrame("Tạo Phiếu Đặt Phòng");
                    taoPhieuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    taoPhieuFrame.setSize(1000, 700);
                    taoPhieuFrame.add(new TaoPhieuDatPhong_GUI(phieu.getMaPhieuDatPhong(), kh.getMaKhachHang(), nv.getMaNhanVien()));
                    taoPhieuFrame.setVisible(true);
                });



                pnlCards.add(btnPhieu);
                break;
            }
        }

    }
    private JPanel createLegend(Color color, String text) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JLabel box = new JLabel();
        box.setPreferredSize(new Dimension(20, 20));
        box.setOpaque(true);
        box.setBackground(color);
        p.add(box);

        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        p.add(lbl);
        p.setBackground(new Color(240, 240, 240));
        return p;
    }
    
    
}
