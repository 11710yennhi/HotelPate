//package dao;
//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import java.sql.*;
//import java.time.LocalDate;
//import java.util.*;
//import connectDB.ConnectDB;
//import entity.KhachHang;
//
//public class KhachHang_DAO {
////
////    public KhachHang getKhachHangTheoMa(String maKH) {
////        KhachHang kh = null;
////        Connection con = null;
////        PreparedStatement ps = null;
////        ResultSet rs = null;
////
////        try {
////            con = ConnectDB.getConnection();
////            String sql = "SELECT maKhachHang, hoTen, soDienThoai, laNguoiVietNam FROM KhachHang WHERE maKhachHang = ?";
////            ps = con.prepareStatement(sql);
////            ps.setString(1, maKH);
////            rs = ps.executeQuery();
////
////            if (rs.next()) {
////                String maKhachHang = rs.getString("maKhachHang");
////                String hoTen = rs.getString("hoTen");
////                String soDienThoai = rs.getString("soDienThoai");
////                boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");
////
////                kh = new KhachHang(maKhachHang, hoTen, soDienThoai, laNguoiVietNam);
////            }
////
////        } catch (SQLException e) {
////            e.printStackTrace();
////        } finally {
////            try {
////                if (rs != null) rs.close();
////                if (ps != null) ps.close();
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        }
////
////        return kh;
////    }
//    public List<KhachHang> getKhachHangTheoSDT(String soDT) {
//        List<KhachHang> dsKhachHang = new ArrayList<>();
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            con = ConnectDB.getConnection();
//            String sql = "SELECT maKhachHang, hoTen, soDienThoai, laNguoiVietNam "
//                       + "FROM KhachHang WHERE soDienThoai LIKE ?";
//            ps = con.prepareStatement(sql);
//            ps.setString(1, "%" + soDT + "%"); // dùng LIKE để tìm gần đúng
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                String maKhachHang = rs.getString("maKhachHang");
//                String hoTen = rs.getString("hoTen");
//                String soDienThoai = rs.getString("soDienThoai");
//                boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");
//
//                KhachHang kh = new KhachHang(maKhachHang, hoTen, soDienThoai, laNguoiVietNam);
//                dsKhachHang.add(kh);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (ps != null) ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return dsKhachHang;
//    }
//    public List<KhachHang> getAllKhachHang() {
//        List<KhachHang> dsKhachHang = new ArrayList<>();
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            con = ConnectDB.getConnection();
//            String sql = "SELECT maKhachHang, hoTen, soDienThoai, laNguoiVietNam FROM KhachHang";
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                String maKhachHang = rs.getString("maKhachHang");
//                String hoTen = rs.getString("hoTen");
//                String soDienThoai = rs.getString("soDienThoai");
//                boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");
//
//                KhachHang kh = new KhachHang(maKhachHang, hoTen, soDienThoai, laNguoiVietNam);
//                dsKhachHang.add(kh);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (ps != null) ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return dsKhachHang;
//    }
//    public boolean themKhachHang(KhachHang kh) {
//        String sql = "INSERT INTO KhachHang (maKhachHang, hoTen, soDienThoai, laNguoiVietNam) VALUES (?, ?, ?, ?)";
//        Connection con = null;
//        PreparedStatement ps = null;
//
//        try {
//            con = ConnectDB.getConnection();
//            ps = con.prepareStatement(sql);
//
//            ps.setString(1, kh.getMaKhachHang());
//            ps.setString(2, kh.getHoTen());
//            ps.setString(3, kh.getSoDienThoai());
//            ps.setBoolean(4, kh.LaNguoiVietNam());
//
//            int result = ps.executeUpdate();
//            return result > 0; // trả về true nếu thêm thành công
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ps != null) ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return false;
//    }
//    public boolean capNhatKhachHang(KhachHang kh) {
//        String sql = "UPDATE KhachHang SET hoTen = ?, soDienThoai = ?, laNguoiVietNam = ? WHERE maKhachHang = ?";
//        Connection con = null;
//        PreparedStatement ps = null;
//
//        try {
//            con = ConnectDB.getConnection();
//            ps = con.prepareStatement(sql);
//
//            ps.setString(1, kh.getHoTen());
//            ps.setString(2, kh.getSoDienThoai());
//            ps.setBoolean(3, kh.LaNguoiVietNam());
//            ps.setString(4, kh.getMaKhachHang());
//
//            int result = ps.executeUpdate();
//            return result > 0; // ✅ true nếu cập nhật thành công
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ps != null) ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return false;
//    }
//
//	private ArrayList<KhachHang> dskh;
//
//	public KhachHang_DAO() {
//		dskh = new ArrayList<KhachHang>();
//	}
//
//	// Đọc toàn bộ bảng KhachHang
//	public List<KhachHang> getAllKhachHang() {
//		dskh.clear();
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "SELECT * FROM KhachHang";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//
//			while (rs.next()) {
//				String maKH = rs.getString("maKhachHang");
//				String hoTen = rs.getString("hoTen");
//				String soDienThoai = rs.getString("soDienThoai");
//				boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");
//
//				KhachHang kh = new KhachHang(maKH, hoTen, soDienThoai, laNguoiVietNam);
//				dskh.add(kh);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return dskh;
//	}
//
//	// Lấy khách hàng theo số điện thoại - khachhang
//	public KhachHang getKhachHangThSDTKH(String sdt) {
//		KhachHang kh = null;
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "SELECT * FROM KhachHang WHERE soDienThoai = ?";
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setString(1, sdt);
//			ResultSet rs = stmt.executeQuery();
//
//			if (rs.next()) {
//				String maKhachHang = rs.getString("maKhachHang");
//				String hoTen = rs.getString("hoTen");
//				boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");
//				kh = new KhachHang(maKhachHang, hoTen, sdt, laNguoiVietNam);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return kh;
//	}
//	// lấy kh theo sdt - phieudatphong
//	 public List<KhachHang> getKhachHangTheoSDT(String soDT) {
//	        List<KhachHang> dsKhachHang = new ArrayList<>();
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        ResultSet rs = null;
//
//	        try {
//	            con = ConnectDB.getConnection();
//	            String sql = "SELECT maKhachHang, hoTen, soDienThoai, laNguoiVietNam "
//	                       + "FROM KhachHang WHERE soDienThoai LIKE ?";
//	            ps = con.prepareStatement(sql);
//	            ps.setString(1, "%" + soDT + "%"); // dùng LIKE để tìm gần đúng
//	            rs = ps.executeQuery();
//
//	            while (rs.next()) {
//	                String maKhachHang = rs.getString("maKhachHang");
//	                String hoTen = rs.getString("hoTen");
//	                String soDienThoai = rs.getString("soDienThoai");
//	                boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");
//
//	                KhachHang kh = new KhachHang(maKhachHang, hoTen, soDienThoai, laNguoiVietNam);
//	                dsKhachHang.add(kh);
//	            }
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            try {
//	                if (rs != null) rs.close();
//	                if (ps != null) ps.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//
//	        return dsKhachHang;
//	    }
//
//
//	// Lấy khách hàng theo mã khách hàng
//	 public KhachHang getKhachHangTheoMa(String maKH) {
//	        KhachHang kh = null;
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        ResultSet rs = null;
//
//	        try {
//	            con = ConnectDB.getConnection();
//	            String sql = "SELECT maKhachHang, hoTen, soDienThoai, laNguoiVietNam FROM KhachHang WHERE maKhachHang = ?";
//	            ps = con.prepareStatement(sql);
//	            ps.setString(1, maKH);
//	            rs = ps.executeQuery();
//
//	            if (rs.next()) {
//	                String maKhachHang = rs.getString("maKhachHang");
//	                String hoTen = rs.getString("hoTen");
//	                String soDienThoai = rs.getString("soDienThoai");
//	                boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");
//
//	                kh = new KhachHang(maKhachHang, hoTen, soDienThoai, laNguoiVietNam);
//	            }
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            try {
//	                if (rs != null) rs.close();
//	                if (ps != null) ps.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//
//	        return kh;
//	    }
//
//
//	public String taoMaKhachHangTuDong() {
//
//		// Lấy ngày hiện tại
//		LocalDate ngayHienTai = LocalDate.now();
//		String ngay = String.format("%02d", ngayHienTai.getDayOfMonth());
//		String thang = String.format("%02d", ngayHienTai.getMonthValue());
//		String nam = String.valueOf(ngayHienTai.getYear());
//
//		// Lấy danh sách phiếu hiện có (từ database hoặc list)
//		List<KhachHang> dskh = getAllKhachHang();
//
//		// Đếm số phiếu trong ngày hiện tại
//		int dem = 0;
//		for (KhachHang kh : dskh) {
//			if (kh.getMaKhachHang().contains("KH" + ngay + thang + nam)) {
//				dem++;
//			}
//		}
//
//		// Tăng số thứ tự lên 1
//		dem++;
//
//		// Ghép lại chuỗi mã theo định dạng
//		String maKH = String.format("KH%s%s%s%03d", ngay, thang, nam, dem);
//		return maKH;
//	}
//
//	// Thêm mới khách hàng
//	 public boolean themKhachHang(KhachHang kh) {
//	        String sql = "INSERT INTO KhachHang (maKhachHang, hoTen, soDienThoai, laNguoiVietNam) VALUES (?, ?, ?, ?)";
//	        Connection con = null;
//	        PreparedStatement ps = null;
//
//	        try {
//	            con = ConnectDB.getConnection();
//	            ps = con.prepareStatement(sql);
//
//	            ps.setString(1, kh.getMaKhachHang());
//	            ps.setString(2, kh.getHoTen());
//	            ps.setString(3, kh.getSoDienThoai());
//	            ps.setBoolean(4, kh.LaNguoiVietNam());
//
//	            int result = ps.executeUpdate();
//	            return result > 0; // trả về true nếu thêm thành công
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            try {
//	                if (ps != null) ps.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//
//	        return false;
//	    }
//
//
////    // Xóa khách hàng
////    public boolean delete(String maKH) {
////        int n = 0;
////        try {
////            ConnectDB.getInstance();
////            Connection con = ConnectDB.getConnection();
////            String sql = "DELETE FROM KhachHang WHERE maKhachHang = ?";
////            PreparedStatement ps = con.prepareStatement(sql);
////            ps.setString(1, maKH);
////
////            n = ps.executeUpdate();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return n > 0;
////    }
//
//	// Cập nhật khách hàng
//	 public boolean capNhatKhachHang(KhachHang kh) {
//	        String sql = "UPDATE KhachHang SET hoTen = ?, soDienThoai = ?, laNguoiVietNam = ? WHERE maKhachHang = ?";
//	        Connection con = null;
//	        PreparedStatement ps = null;
//
//	        try {
//	            con = ConnectDB.getConnection();
//	            ps = con.prepareStatement(sql);
//
//	            ps.setString(1, kh.getHoTen());
//	            ps.setString(2, kh.getSoDienThoai());
//	            ps.setBoolean(3, kh.LaNguoiVietNam());
//	            ps.setString(4, kh.getMaKhachHang());
//
//	            int result = ps.executeUpdate();
//	            return result > 0; // true nếu cập nhật thành công
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            try {
//	                if (ps != null) ps.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//
//	        return false;
//	    }
//
//
//
package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_DAO {

    // Danh sách khách hàng (cache tạm thời)
    private ArrayList<KhachHang> dskh;

    public KhachHang_DAO() {
        dskh = new ArrayList<>();
    }

    // 🔹 Lấy toàn bộ khách hàng
    public List<KhachHang> getAllKhachHang() {
        dskh.clear();
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT maKhachHang, hoTen, soDienThoai, laNguoiVietNam FROM KhachHang";
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

    // 🔹 Lấy khách hàng theo mã
    public KhachHang getKhachHangTheoMa(String maKH) {
        KhachHang kh = null;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hoTen = rs.getString("hoTen");
                String soDienThoai = rs.getString("soDienThoai");
                boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");
                kh = new KhachHang(maKH, hoTen, soDienThoai, laNguoiVietNam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kh;
    }

    // 🔹 Lấy khách hàng theo SĐT (chính xác 1 số)
    public KhachHang getKhachHangTheoSDT(String sdt) {
        KhachHang kh = null;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM KhachHang WHERE soDienThoai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String maKH = rs.getString("maKhachHang");
                String hoTen = rs.getString("hoTen");
                boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");
                kh = new KhachHang(maKH, hoTen, sdt, laNguoiVietNam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kh;
    }

    // 🔹 Tìm khách hàng theo SĐT gần đúng (dành cho tìm kiếm)
    public List<KhachHang> timKhachHangTheoSDTGanDung(String soDT) {
        List<KhachHang> dsKhachHang = new ArrayList<>();
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM KhachHang WHERE soDienThoai LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + soDT + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maKH = rs.getString("maKhachHang");
                String hoTen = rs.getString("hoTen");
                String soDienThoai = rs.getString("soDienThoai");
                boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");

                KhachHang kh = new KhachHang(maKH, hoTen, soDienThoai, laNguoiVietNam);
                dsKhachHang.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKhachHang;
    }

    // 🔹 Tạo mã khách hàng tự động theo ngày
    public String taoMaKhachHangTuDong() {
        LocalDate ngayHienTai = LocalDate.now();
        String ngay = String.format("%02d", ngayHienTai.getDayOfMonth());
        String thang = String.format("%02d", ngayHienTai.getMonthValue());
        String nam = String.valueOf(ngayHienTai.getYear());

        List<KhachHang> danhSach = getAllKhachHang();
        int dem = 0;
        for (KhachHang kh : danhSach) {
            if (kh.getMaKhachHang().contains("KH" + ngay + thang + nam)) {
                dem++;
            }
        }
        dem++;
        return String.format("KH%s%s%s%03d", ngay, thang, nam, dem);
    }

    // 🔹 Thêm khách hàng
    public boolean themKhachHang(KhachHang kh) {
        String sql = "INSERT INTO KhachHang (maKhachHang, hoTen, soDienThoai, laNguoiVietNam) VALUES (?, ?, ?, ?)";
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kh.getMaKhachHang());
            ps.setString(2, kh.getHoTen());
            ps.setString(3, kh.getSoDienThoai());
            ps.setBoolean(4, kh.LaNguoiVietNam());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 🔹 Cập nhật khách hàng
    public boolean capNhatKhachHang(KhachHang kh) {
        String sql = "UPDATE KhachHang SET hoTen = ?, soDienThoai = ?, laNguoiVietNam = ? WHERE maKhachHang = ?";
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSoDienThoai());
            ps.setBoolean(3, kh.LaNguoiVietNam());
            ps.setString(4, kh.getMaKhachHang());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

