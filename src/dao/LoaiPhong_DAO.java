package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LoaiPhong;

public class LoaiPhong_DAO {

    // ======= LẤY TẤT CẢ DỮ LIỆU TRONG BẢNG LoaiPhong =======
    public List<LoaiPhong> getAllLoaiPhong() {
        List<LoaiPhong> ds = new ArrayList<>();
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM LoaiPhong";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String ma = rs.getString("maLoaiPhong");
                String ten = rs.getString("tenLoaiPhong");
                int sucChua = rs.getInt("sucChua");
                double gia = rs.getDouble("gia");
                String moTa = rs.getString("moTa");

                LoaiPhong lp = new LoaiPhong(ma, ten, sucChua, gia, moTa);
                ds.add(lp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    // ======= THÊM MỚI LOẠI PHÒNG =======
    public boolean insertLoaiPhong(LoaiPhong lp) {
        int n = 0;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "INSERT INTO LoaiPhong (maLoaiPhong, tenLoaiPhong, sucChua, gia, moTa) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, lp.getMaLoaiPhong());
            ps.setString(2, lp.getTenLoaiPhong());
            ps.setInt(3, lp.getSuaChua()); // sửa lỗi: tên phương thức getSucChua
            ps.setDouble(4, lp.getGia());
            ps.setString(5, lp.getMoTa());
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // ======= CẬP NHẬT LOẠI PHÒNG =======
    public boolean updateLoaiPhong(LoaiPhong lp) {
        int n = 0;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "UPDATE LoaiPhong SET tenLoaiPhong=?, sucChua=?, gia=?, moTa=? WHERE maLoaiPhong=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, lp.getTenLoaiPhong());
            ps.setInt(2, lp.getSuaChua()); // sửa lỗi: tên phương thức getSucChua
            ps.setDouble(3, lp.getGia());
            ps.setString(4, lp.getMoTa());
            ps.setString(5, lp.getMaLoaiPhong());
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // ======= XÓA LOẠI PHÒNG =======
    public boolean deleteLoaiPhong(String maLoaiPhong) {
        int n = 0;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "DELETE FROM LoaiPhong WHERE maLoaiPhong = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maLoaiPhong);
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // ======= TÌM KIẾM THEO MÃ =======
    public LoaiPhong getLoaiPhongTheoMa(String ma) {
        LoaiPhong lp = null;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM LoaiPhong WHERE maLoaiPhong = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String ten = rs.getString("tenLoaiPhong");
                int sucChua = rs.getInt("sucChua");
                double gia = rs.getDouble("gia");
                String moTa = rs.getString("moTa");
                lp = new LoaiPhong(ma, ten, sucChua, gia, moTa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lp;
    }

    // ======= HÀM HỖ TRỢ CHO GUI KHÔNG LỖI BIÊN DỊCH =======
    // chỉ là alias cho getLoaiPhongTheoMa
  
	public LoaiPhong findByMa(String ma) {
		// TODO Auto-generated method stub
		return getLoaiPhongTheoMa(ma);
	}
}
