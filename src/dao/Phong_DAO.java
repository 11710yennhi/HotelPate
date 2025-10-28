//package dao;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//import connectDB.ConnectDB;
//import entity.LoaiPhong;
//import entity.Phong;
//
//public class Phong_DAO {
//
//<<<<<<< HEAD
//    // ======= LẤY TOÀN BỘ PHÒNG =======
//=======
////	// ======= LẤY TẤT CẢ PHÒNG =======
////	public List<Phong> layTatCaPhong() {
////		List<Phong> ds = new ArrayList<>();
////		try {
////			Connection con = ConnectDB.getInstance().getConnection();
////			String sql = "SELECT p.maPhong, p.trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa "
////					+ "FROM Phong p LEFT JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong";
////			Statement stmt = con.createStatement();
////			ResultSet rs = stmt.executeQuery(sql);
////
////			while (rs.next()) {
////				String maPhong = rs.getString("maPhong");
////				String trangThai = rs.getString("trangThai");
////
////				String maLoai = rs.getString("maLoaiPhong");
////				String tenLoai = rs.getString("tenLoaiPhong");
////				int sucChua = rs.getInt("sucChua");
////				double gia = rs.getDouble("gia");
////				String moTa = rs.getString("moTa");
////
////				LoaiPhong loaiPhong = null;
////				if (maLoai != null) {
////					loaiPhong = new LoaiPhong(maLoai, tenLoai, sucChua, gia, moTa);
////				}
////
////				Phong phong = new Phong(maPhong, loaiPhong, trangThai);
////				ds.add(phong);
////			}
////
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		return ds;
////	}
//	
//	
//	  // ======= LẤY TOÀN BỘ PHÒNG =======
//>>>>>>> 9f14c2473b645f98db78a8eaf804491cde972697
//    public List<Phong> getAllPhong() {
//        List<Phong> dsPhong = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            conn = ConnectDB.getInstance().getConnection();
//            String sql = "SELECT p.maPhong, p.trangThai, p.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa "
//                       + "FROM Phong p JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong";
//            stmt = conn.prepareStatement(sql);
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                String maPhong = rs.getString("maPhong");
//                String trangThai = rs.getString("trangThai");
//                String maLoai = rs.getString("maLoaiPhong");
//                String tenLoai = rs.getString("tenLoaiPhong");
//                int sucChua = rs.getInt("sucChua");
//                double gia = rs.getDouble("gia");
//                String moTa = rs.getString("moTa");
//
//                LoaiPhong loaiPhong = new LoaiPhong(maLoai, tenLoai, sucChua, gia, moTa);
//                Phong phong = new Phong(maPhong, loaiPhong, trangThai);
//                dsPhong.add(phong);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources(conn, stmt, rs);
//        }
//        return dsPhong;
//    }
//<<<<<<< HEAD
//
//=======
//    
//    
//>>>>>>> 9f14c2473b645f98db78a8eaf804491cde972697
//    // ======= LẤY PHÒNG TRỐNG =======
//    public List<Phong> getPhongTrong() {
//        List<Phong> dsPhongTrong = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            conn = ConnectDB.getInstance().getConnection();
//            String sql = "SELECT p.maPhong, p.trangThai, p.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa "
//                       + "FROM Phong p JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong "
//                       + "WHERE p.trangThai = N'Trống'";
//            stmt = conn.prepareStatement(sql);
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                String maPhong = rs.getString("maPhong");
//                String trangThai = rs.getString("trangThai");
//                String maLoai = rs.getString("maLoaiPhong");
//                String tenLoai = rs.getString("tenLoaiPhong");
//                int sucChua = rs.getInt("sucChua");
//                double gia = rs.getDouble("gia");
//                String moTa = rs.getString("moTa");
//
//                LoaiPhong loaiPhong = new LoaiPhong(maLoai, tenLoai, sucChua, gia, moTa);
//                Phong phong = new Phong(maPhong, loaiPhong, trangThai);
//                dsPhongTrong.add(phong);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources(conn, stmt, rs);
//        }
//        return dsPhongTrong;
//    }
//<<<<<<< HEAD
//
//=======
//    
//>>>>>>> 9f14c2473b645f98db78a8eaf804491cde972697
//    // ======= LẤY PHÒNG THEO MÃ LOẠI PHÒNG =======
//    public List<Phong> getPhongTheoMaLoaiPhong(String maLoaiPhong) {
//        List<Phong> dsPhong = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            conn = ConnectDB.getInstance().getConnection();
//            String sql = "SELECT p.maPhong, p.trangThai, p.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa "
//                       + "FROM Phong p JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong "
//                       + "WHERE p.maLoaiPhong = ?";
//            stmt = conn.prepareStatement(sql);
//            stmt.setString(1, maLoaiPhong);
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                String maPhong = rs.getString("maPhong");
//                String trangThai = rs.getString("trangThai");
//                String maLoai = rs.getString("maLoaiPhong");
//                String tenLoai = rs.getString("tenLoaiPhong");
//                int sucChua = rs.getInt("sucChua");
//                double gia = rs.getDouble("gia");
//                String moTa = rs.getString("moTa");
//
//                LoaiPhong loaiPhong = new LoaiPhong(maLoai, tenLoai, sucChua, gia, moTa);
//                Phong phong = new Phong(maPhong, loaiPhong, trangThai);
//                dsPhong.add(phong);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources(conn, stmt, rs);
//        }
//
//        return dsPhong;
//    }
//<<<<<<< HEAD
//
//=======
//    
//>>>>>>> 9f14c2473b645f98db78a8eaf804491cde972697
//    // ======= ĐÓNG KẾT NỐI AN TOÀN =======
//    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
//        try {
//            if (rs != null) rs.close();
//            if (stmt != null) stmt.close();
//            if (conn != null) conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//<<<<<<< HEAD
//
//=======
//    
//>>>>>>> 9f14c2473b645f98db78a8eaf804491cde972697
//    // ======= LẤY TẤT CẢ PHÒNG =======
//    public List<Phong> layTatCaPhong() {
//        List<Phong> ds = new ArrayList<>();
//        try {
//            Connection con = ConnectDB.getInstance().getConnection();
//            String sql = "SELECT p.maPhong, p.trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa "
//                       + "FROM Phong p LEFT JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong";
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                String maPhong = rs.getString("maPhong");
//                String trangThai = rs.getString("trangThai");
//                String maLoai = rs.getString("maLoaiPhong");
//                String tenLoai = rs.getString("tenLoaiPhong");
//                int sucChua = rs.getInt("sucChua");
//                double gia = rs.getDouble("gia");
//                String moTa = rs.getString("moTa");
//
//                LoaiPhong loaiPhong = null;
//                if (maLoai != null) {
//                    loaiPhong = new LoaiPhong(maLoai, tenLoai, sucChua, gia, moTa);
//                }
//
//                Phong phong = new Phong(maPhong, loaiPhong, trangThai);
//                ds.add(phong);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ds;
//    }
//
//<<<<<<< HEAD
//    // ======= THÊM PHÒNG =======
//    public boolean themPhong(Phong phong) {
//        int n = 0;
//        try {
//            Connection con = ConnectDB.getInstance().getConnection();
//            String sql = "INSERT INTO Phong (maPhong, maLoaiPhong, trangThai) VALUES (?, ?, ?)";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, phong.getMaPhong());
//            ps.setString(2, phong.getLoaiPhong() != null ? phong.getLoaiPhong().getMaLoaiPhong() : null);
//            ps.setString(3, phong.getTrangThai());
//            n = ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return n > 0;
//    }
//
//    // ======= CẬP NHẬT PHÒNG =======
//    public boolean capNhatPhong(Phong phong) {
//        int n = 0;
//        try {
//            Connection con = ConnectDB.getInstance().getConnection();
//            String sql = "UPDATE Phong SET maLoaiPhong=?, trangThai=? WHERE maPhong=?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, phong.getLoaiPhong() != null ? phong.getLoaiPhong().getMaLoaiPhong() : null);
//            ps.setString(2, phong.getTrangThai());
//            ps.setString(3, phong.getMaPhong());
//            n = ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return n > 0;
//    }
//
//    // ======= XÓA PHÒNG =======
//    public boolean xoaPhong(String maPhong) {
//        int n = 0;
//        try {
//            Connection con = ConnectDB.getInstance().getConnection();
//            String sql = "DELETE FROM Phong WHERE maPhong=?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, maPhong);
//            n = ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return n > 0;
//    }
//
//    // ======= TÌM PHÒNG THEO MÃ =======
//=======
//
//	// ======= THÊM PHÒNG MỚI =======
//	public boolean themPhong(Phong phong) {
//		int n = 0;
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "INSERT INTO Phong (maPhong, maLoaiPhong, trangThai) VALUES (?, ?, ?)";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, phong.getMaPhong());
//			ps.setString(2, phong.getLoaiPhong() != null ? phong.getLoaiPhong().getMaLoaiPhong() : null);
//			ps.setString(3, phong.getTrangThai());
//			n = ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return n > 0;
//	}
//
//	// ======= CẬP NHẬT PHÒNG =======
//	public boolean capNhatPhong(Phong phong) {
//		int n = 0;
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "UPDATE Phong SET maLoaiPhong=?, trangThai=? WHERE maPhong=?";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, phong.getLoaiPhong() != null ? phong.getLoaiPhong().getMaLoaiPhong() : null);
//			ps.setString(2, phong.getTrangThai());
//			ps.setString(3, phong.getMaPhong());
//			n = ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return n > 0;
//	}
//
//	// ======= XÓA PHÒNG =======
//	public boolean xoaPhong(String maPhong) {
//		int n = 0;
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "DELETE FROM Phong WHERE maPhong=?";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, maPhong);
//			n = ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return n > 0;
//	}
//
//	// ======= TÌM KIẾM PHÒNG THEO MÃ =======
////	public Phong timPhongTheoMa(String maPhong) {
////		Phong phong = null;
////		try {
////			Connection con = ConnectDB.getInstance().getConnection();
////			String sql = "SELECT p.maPhong, p.trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa "
////					+ "FROM Phong p LEFT JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong " + "WHERE p.maPhong=?";
////			PreparedStatement ps = con.prepareStatement(sql);
////			ps.setString(1, maPhong);
////			ResultSet rs = ps.executeQuery();
////
////			if (rs.next()) {
////				String trangThai = rs.getString("trangThai");
////				String maLoai = rs.getString("maLoaiPhong");
////				String tenLoai = rs.getString("tenLoaiPhong");
////				int sucChua = rs.getInt("sucChua");
////				double gia = rs.getDouble("gia");
////				String moTa = rs.getString("moTa");
////
////				LoaiPhong loaiPhong = null;
////				if (maLoai != null) {
////					loaiPhong = new LoaiPhong(maLoai, tenLoai, sucChua, gia, moTa);
////				}
////
////				phong = new Phong(maPhong, loaiPhong, trangThai);
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		return phong;
////	}
//	
//	// ======= TÌM PHÒNG THEO MÃ =======
//>>>>>>> 9f14c2473b645f98db78a8eaf804491cde972697
//    public Phong timPhongTheoMa(String maPhongInput) {
//        Phong phong = null;
//        try {
//            Connection con = ConnectDB.getInstance().getConnection();
//            String sql = """
//                SELECT 
//                    RTRIM(LTRIM(p.maPhong)) AS maPhong, 
//                    RTRIM(LTRIM(p.trangThai)) AS trangThai,
//                    RTRIM(LTRIM(lp.maLoaiPhong)) AS maLoaiPhong, 
//                    RTRIM(LTRIM(lp.tenLoaiPhong)) AS tenLoaiPhong, 
//                    lp.sucChua, lp.gia, 
//                    COALESCE(lp.moTa, '') AS moTa
//                FROM Phong p 
//                LEFT JOIN LoaiPhong lp ON LTRIM(RTRIM(p.maLoaiPhong)) = LTRIM(RTRIM(lp.maLoaiPhong))
//                WHERE LTRIM(RTRIM(p.maPhong)) = ?
//            """;
//
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, maPhongInput.trim());
//
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                String maPhong = rs.getString("maPhong");
//                String trangThai = rs.getString("trangThai");
//                String maLoai = rs.getString("maLoaiPhong");
//                String tenLoai = rs.getString("tenLoaiPhong");
//                int sucChua = rs.getInt("sucChua");
//                double gia = rs.getDouble("gia");
//                String moTa = rs.getString("moTa");
//
//                LoaiPhong loaiPhong = new LoaiPhong(maLoai, tenLoai, sucChua, gia, moTa);
//                phong = new Phong(maPhong, loaiPhong, trangThai);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return phong;
//    }
//
//<<<<<<< HEAD
//    
//=======
//	
//
//	public String getLastRoomCodeByLoai(String prefix) {
//		String sql = "SELECT MAX(maPhong) FROM Phong WHERE maPhong LIKE ?";
//		try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
//			ps.setString(1, prefix + "%");
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				return rs.getString(1);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//>>>>>>> 9f14c2473b645f98db78a8eaf804491cde972697
//}
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LoaiPhong;
import entity.Phong;

public class Phong_DAO {

    // ======= LẤY TOÀN BỘ PHÒNG =======
    public List<Phong> getAllPhong() {
        List<Phong> dsPhong = new ArrayList<>();
        try (Connection conn = ConnectDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT p.maPhong, p.trangThai, p.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa " +
                 "FROM Phong p JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String trangThai = rs.getString("trangThai");
                String maLoai = rs.getString("maLoaiPhong");
                String tenLoai = rs.getString("tenLoaiPhong");
                int sucChua = rs.getInt("sucChua");
                double gia = rs.getDouble("gia");
                String moTa = rs.getString("moTa");

                LoaiPhong loaiPhong = new LoaiPhong(maLoai, tenLoai, sucChua, gia, moTa);
                Phong phong = new Phong(maPhong, loaiPhong, trangThai);
                dsPhong.add(phong);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPhong;
    }

    // ======= LẤY PHÒNG TRỐNG =======
    public List<Phong> getPhongTrong() {
        List<Phong> dsPhongTrong = new ArrayList<>();
        try (Connection conn = ConnectDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT p.maPhong, p.trangThai, p.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa " +
                 "FROM Phong p JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong WHERE p.trangThai = N'Trống'");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String trangThai = rs.getString("trangThai");
                String maLoai = rs.getString("maLoaiPhong");
                String tenLoai = rs.getString("tenLoaiPhong");
                int sucChua = rs.getInt("sucChua");
                double gia = rs.getDouble("gia");
                String moTa = rs.getString("moTa");

                LoaiPhong loaiPhong = new LoaiPhong(maLoai, tenLoai, sucChua, gia, moTa);
                Phong phong = new Phong(maPhong, loaiPhong, trangThai);
                dsPhongTrong.add(phong);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPhongTrong;
    }

    // ======= LẤY PHÒNG THEO MÃ LOẠI PHÒNG =======
    public List<Phong> getPhongTheoMaLoaiPhong(String maLoaiPhong) {
        List<Phong> dsPhong = new ArrayList<>();
        try (Connection conn = ConnectDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT p.maPhong, p.trangThai, p.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa " +
                 "FROM Phong p JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong WHERE p.maLoaiPhong = ?")) {
            stmt.setString(1, maLoaiPhong);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String trangThai = rs.getString("trangThai");
                String maLoai = rs.getString("maLoaiPhong");
                String tenLoai = rs.getString("tenLoaiPhong");
                int sucChua = rs.getInt("sucChua");
                double gia = rs.getDouble("gia");
                String moTa = rs.getString("moTa");

                LoaiPhong loaiPhong = new LoaiPhong(maLoai, tenLoai, sucChua, gia, moTa);
                Phong phong = new Phong(maPhong, loaiPhong, trangThai);
                dsPhong.add(phong);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPhong;
    }

    // ======= THÊM PHÒNG =======
    public boolean themPhong(Phong phong) {
        try (Connection con = ConnectDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Phong (maPhong, maLoaiPhong, trangThai) VALUES (?, ?, ?)")) {
            ps.setString(1, phong.getMaPhong());
            ps.setString(2, phong.getLoaiPhong() != null ? phong.getLoaiPhong().getMaLoaiPhong() : null);
            ps.setString(3, phong.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ======= CẬP NHẬT PHÒNG =======
    public boolean capNhatPhong(Phong phong) {
        try (Connection con = ConnectDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE Phong SET maLoaiPhong=?, trangThai=? WHERE maPhong=?")) {
            ps.setString(1, phong.getLoaiPhong() != null ? phong.getLoaiPhong().getMaLoaiPhong() : null);
            ps.setString(2, phong.getTrangThai());
            ps.setString(3, phong.getMaPhong());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ======= XÓA PHÒNG =======
    public boolean xoaPhong(String maPhong) {
        try (Connection con = ConnectDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM Phong WHERE maPhong=?")) {
            ps.setString(1, maPhong);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ======= TÌM PHÒNG THEO MÃ =======
    public Phong timPhongTheoMa(String maPhongInput) {
        Phong phong = null;
        try (Connection con = ConnectDB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement("""
                 SELECT 
                     RTRIM(LTRIM(p.maPhong)) AS maPhong, 
                     RTRIM(LTRIM(p.trangThai)) AS trangThai,
                     RTRIM(LTRIM(lp.maLoaiPhong)) AS maLoaiPhong, 
                     RTRIM(LTRIM(lp.tenLoaiPhong)) AS tenLoaiPhong, 
                     lp.sucChua, lp.gia, 
                     COALESCE(lp.moTa, '') AS moTa
                 FROM Phong p 
                 LEFT JOIN LoaiPhong lp ON LTRIM(RTRIM(p.maLoaiPhong)) = LTRIM(RTRIM(lp.maLoaiPhong))
                 WHERE LTRIM(RTRIM(p.maPhong)) = ?
             """)) {

            ps.setString(1, maPhongInput.trim());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String trangThai = rs.getString("trangThai");
                String maLoai = rs.getString("maLoaiPhong");
                String tenLoai = rs.getString("tenLoaiPhong");
                int sucChua = rs.getInt("sucChua");
                double gia = rs.getDouble("gia");
                String moTa = rs.getString("moTa");

                LoaiPhong loaiPhong = new LoaiPhong(maLoai, tenLoai, sucChua, gia, moTa);
                phong = new Phong(maPhong, loaiPhong, trangThai);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return phong;
    }

    // ======= LẤY MÃ PHÒNG MỚI NHẤT THEO TIỀN TỐ =======
    public String getLastRoomCodeByLoai(String prefix) {
        String sql = "SELECT MAX(maPhong) FROM Phong WHERE maPhong LIKE ?";
        try (Connection conn = ConnectDB.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, prefix + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
