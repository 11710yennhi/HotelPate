package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_DAO {
	private ArrayList<KhachHang> dskh;

	public KhachHang_DAO() {
		dskh = new ArrayList<KhachHang>();
	}

	// Đọc toàn bộ bảng KhachHang
	public List<KhachHang> getAllKhachHang() {
		dskh.clear();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM KhachHang";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String maKH = rs.getString("maKhachHang");
				String hoTen = rs.getString("hoTen");
				String soDienThoai = rs.getString("soDienThoai");
				boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");

				KhachHang kh = new KhachHang(maKH, hoTen, soDienThoai, laNguoiVietNam);
				dskh.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dskh;
	}

	// Lấy khách hàng theo số điện thoại
	public KhachHang getKhachHangTheoSDT(String sdt) {
		KhachHang kh = null;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM KhachHang WHERE soDienThoai = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, sdt);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String maKhachHang = rs.getString("maKhachHang");
				String hoTen = rs.getString("hoTen");
				boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");
				kh = new KhachHang(maKhachHang, hoTen, sdt, laNguoiVietNam);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kh;
	}

	// Lấy khách hàng theo mã khách hàng
	public KhachHang getKhachHangTheoMa(String maKH) {
        KhachHang kh = null;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maKH);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hoTen = rs.getString("hoTen");
                String soDienThoai = rs.getString("soDienThoai");
                boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");

                kh = new KhachHang(maKH, hoTen, soDienThoai, laNguoiVietNam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kh;
    }

	public String taoMaKhachHangTuDong() {

		// Lấy ngày hiện tại
		LocalDate ngayHienTai = LocalDate.now();
		String ngay = String.format("%02d", ngayHienTai.getDayOfMonth());
		String thang = String.format("%02d", ngayHienTai.getMonthValue());
		String nam = String.valueOf(ngayHienTai.getYear());

		// Lấy danh sách phiếu hiện có (từ database hoặc list)
		List<KhachHang> dskh = getAllKhachHang();

		// Đếm số phiếu trong ngày hiện tại
		int dem = 0;
		for (KhachHang kh : dskh) {
			if (kh.getMaKhachHang().contains("KH" + ngay + thang + nam)) {
				dem++;
			}
		}

		// Tăng số thứ tự lên 1
		dem++;

		// Ghép lại chuỗi mã theo định dạng
		String maKH = String.format("KH%s%s%s%03d", ngay, thang, nam, dem);
		return maKH;
	}

	// Thêm mới khách hàng
	public boolean create(KhachHang kh) {
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "INSERT INTO KhachHang (maKhachHang, hoTen, soDienThoai, laNguoiVietNam) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, kh.getMaKhachHang());
			ps.setString(2, kh.getHoTen());
			ps.setString(3, kh.getSoDienThoai());
			ps.setBoolean(4, kh.LaNguoiVietNam());

			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

//    // Xóa khách hàng
//    public boolean delete(String maKH) {
//        int n = 0;
//        try {
//            ConnectDB.getInstance();
//            Connection con = ConnectDB.getConnection();
//            String sql = "DELETE FROM KhachHang WHERE maKhachHang = ?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, maKH);
//
//            n = ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return n > 0;
//    }

	// Cập nhật khách hàng
	public boolean update(KhachHang kh) {
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "UPDATE KhachHang SET hoTen = ?, laNguoiVietNam = ? WHERE soDienThoai = ?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, kh.getHoTen());
			ps.setBoolean(2, kh.LaNguoiVietNam());
			ps.setString(3, kh.getSoDienThoai());

			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
