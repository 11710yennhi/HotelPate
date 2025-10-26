package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiPhiPhatSinh;

public class ChiPhiPhatSinh_DAO {

    // Đọc tất cả dữ liệu từ bảng ChiPhiPhatSinh
    public List<ChiPhiPhatSinh> getAllChiPhiPhatSinh() {
        List<ChiPhiPhatSinh> ds = new ArrayList<>();
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM ChiPhiPhatSinh";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String ma = rs.getString("maChiPhiPhatSinh");
                String ten = rs.getString("tenChiPhiPhatSinh");
                String loai = rs.getString("loaiChiPhiPhatSinh");
                double gia = rs.getDouble("gia");

                // Cột "donViTinh" không có trong DB của bạn -> gán mặc định ""
                ChiPhiPhatSinh cp = new ChiPhiPhatSinh(ma, ten, loai, "", gia);
                ds.add(cp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    // Thêm mới
    public boolean insertChiPhi(ChiPhiPhatSinh cp) {
        int n = 0;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "INSERT INTO ChiPhiPhatSinh (maChiPhiPhatSinh, tenChiPhiPhatSinh, loaiChiPhiPhatSinh, gia) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cp.getMaChiPhiPhatSinh());
            ps.setString(2, cp.getTenChiPhiPhatSinh());
            ps.setString(3, cp.getLoaiChiPhiPhatSinh());
            ps.setDouble(4, cp.getGia());
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Cập nhật
    public boolean updateChiPhi(ChiPhiPhatSinh cp) {
        int n = 0;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "UPDATE ChiPhiPhatSinh SET tenChiPhiPhatSinh=?, loaiChiPhiPhatSinh=?, gia=? WHERE maChiPhiPhatSinh=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cp.getTenChiPhiPhatSinh());
            ps.setString(2, cp.getLoaiChiPhiPhatSinh());
            ps.setDouble(3, cp.getGia());
            ps.setString(4, cp.getMaChiPhiPhatSinh());
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Tìm kiếm theo mã
    public ChiPhiPhatSinh getChiPhiTheoMa(String ma) {
        ChiPhiPhatSinh cp = null;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM ChiPhiPhatSinh WHERE maChiPhiPhatSinh = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String ten = rs.getString("tenChiPhiPhatSinh");
                String loai = rs.getString("loaiChiPhiPhatSinh");
                double gia = rs.getDouble("gia");
                cp = new ChiPhiPhatSinh(ma, ten, loai, "", gia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cp;
    }
}
