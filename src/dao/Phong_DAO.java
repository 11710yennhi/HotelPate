package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LoaiPhong;
import entity.Phong;

public class Phong_DAO {

//	// ======= LẤY TẤT CẢ PHÒNG =======
//	public List<Phong> layTatCaPhong() {
//		List<Phong> ds = new ArrayList<>();
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "SELECT p.maPhong, p.trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa "
//					+ "FROM Phong p LEFT JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong";
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//
//			while (rs.next()) {
//				String maPhong = rs.getString("maPhong");
//				String trangThai = rs.getString("trangThai");
//
//				String maLoai = rs.getString("maLoaiPhong");
//				String tenLoai = rs.getString("tenLoaiPhong");
//				int sucChua = rs.getInt("sucChua");
//				double gia = rs.getDouble("gia");
//				String moTa = rs.getString("moTa");
//
//				LoaiPhong loaiPhong = null;
//				if (maLoai != null) {
//					loaiPhong = new LoaiPhong(maLoai, tenLoai, sucChua, gia, moTa);
//				}
//
//				Phong phong = new Phong(maPhong, loaiPhong, trangThai);
//				ds.add(phong);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ds;
//	}
	
	
	  // ======= LẤY TOÀN BỘ PHÒNG =======
    public List<Phong> getAllPhong() {
        List<Phong> dsPhong = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectDB.getInstance().getConnection();
            String sql = "SELECT p.maPhong, p.trangThai, p.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa "
                       + "FROM Phong p JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

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
        } finally {
            closeResources(conn, stmt, rs);
        }
        return dsPhong;
    }
    
    
    // ======= LẤY PHÒNG TRỐNG =======
    public List<Phong> getPhongTrong() {
        List<Phong> dsPhongTrong = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectDB.getInstance().getConnection();
            String sql = "SELECT p.maPhong, p.trangThai, p.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa "
                       + "FROM Phong p JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong "
                       + "WHERE p.trangThai = N'Trống'";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

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
        } finally {
            closeResources(conn, stmt, rs);
        }
        return dsPhongTrong;
    }
    
    // ======= LẤY PHÒNG THEO MÃ LOẠI PHÒNG =======
    public List<Phong> getPhongTheoMaLoaiPhong(String maLoaiPhong) {
        List<Phong> dsPhong = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectDB.getInstance().getConnection();
            String sql = "SELECT p.maPhong, p.trangThai, p.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa "
                       + "FROM Phong p JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong "
                       + "WHERE p.maLoaiPhong = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maLoaiPhong);
            rs = stmt.executeQuery();

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
        } finally {
            closeResources(conn, stmt, rs);
        }

        return dsPhong;
    }
    
    // ======= ĐÓNG KẾT NỐI AN TOÀN =======
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // ======= LẤY TẤT CẢ PHÒNG =======
    public List<Phong> layTatCaPhong() {
        List<Phong> ds = new ArrayList<>();
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT p.maPhong, p.trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa "
                       + "FROM Phong p LEFT JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String trangThai = rs.getString("trangThai");
                String maLoai = rs.getString("maLoaiPhong");
                String tenLoai = rs.getString("tenLoaiPhong");
                int sucChua = rs.getInt("sucChua");
                double gia = rs.getDouble("gia");
                String moTa = rs.getString("moTa");

                LoaiPhong loaiPhong = null;
                if (maLoai != null) {
                    loaiPhong = new LoaiPhong(maLoai, tenLoai, sucChua, gia, moTa);
                }

                Phong phong = new Phong(maPhong, loaiPhong, trangThai);
                ds.add(phong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }


	// ======= THÊM PHÒNG MỚI =======
	public boolean themPhong(Phong phong) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "INSERT INTO Phong (maPhong, maLoaiPhong, trangThai) VALUES (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, phong.getMaPhong());
			ps.setString(2, phong.getLoaiPhong() != null ? phong.getLoaiPhong().getMaLoaiPhong() : null);
			ps.setString(3, phong.getTrangThai());
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	// ======= CẬP NHẬT PHÒNG =======
	public boolean capNhatPhong(Phong phong) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "UPDATE Phong SET maLoaiPhong=?, trangThai=? WHERE maPhong=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, phong.getLoaiPhong() != null ? phong.getLoaiPhong().getMaLoaiPhong() : null);
			ps.setString(2, phong.getTrangThai());
			ps.setString(3, phong.getMaPhong());
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	// ======= XÓA PHÒNG =======
	public boolean xoaPhong(String maPhong) {
		int n = 0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "DELETE FROM Phong WHERE maPhong=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maPhong);
			n = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	// ======= TÌM KIẾM PHÒNG THEO MÃ =======
//	public Phong timPhongTheoMa(String maPhong) {
//		Phong phong = null;
//		try {
//			Connection con = ConnectDB.getInstance().getConnection();
//			String sql = "SELECT p.maPhong, p.trangThai, lp.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa "
//					+ "FROM Phong p LEFT JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong " + "WHERE p.maPhong=?";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, maPhong);
//			ResultSet rs = ps.executeQuery();
//
//			if (rs.next()) {
//				String trangThai = rs.getString("trangThai");
//				String maLoai = rs.getString("maLoaiPhong");
//				String tenLoai = rs.getString("tenLoaiPhong");
//				int sucChua = rs.getInt("sucChua");
//				double gia = rs.getDouble("gia");
//				String moTa = rs.getString("moTa");
//
//				LoaiPhong loaiPhong = null;
//				if (maLoai != null) {
//					loaiPhong = new LoaiPhong(maLoai, tenLoai, sucChua, gia, moTa);
//				}
//
//				phong = new Phong(maPhong, loaiPhong, trangThai);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return phong;
//	}
	
	// ======= TÌM PHÒNG THEO MÃ =======
    public Phong timPhongTheoMa(String maPhongInput) {
        Phong phong = null;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = """
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
            """;

            PreparedStatement ps = con.prepareStatement(sql);
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

	

	public String getLastRoomCodeByLoai(String prefix) {
		String sql = "SELECT MAX(maPhong) FROM Phong WHERE maPhong LIKE ?";
		try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
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
