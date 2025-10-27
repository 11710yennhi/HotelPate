package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_DAO {

    public KhachHang getKhachHangTheoMa(String maKH) {
        KhachHang kh = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectDB.getConnection();
            String sql = "SELECT maKhachHang, hoTen, soDienThoai, laNguoiVietNam FROM KhachHang WHERE maKhachHang = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, maKH);
            rs = ps.executeQuery();

            if (rs.next()) {
                String maKhachHang = rs.getString("maKhachHang");
                String hoTen = rs.getString("hoTen");
                String soDienThoai = rs.getString("soDienThoai");
                boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");

                kh = new KhachHang(maKhachHang, hoTen, soDienThoai, laNguoiVietNam);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return kh;
    }
    public List<KhachHang> getKhachHangTheoSDT(String soDT) {
        List<KhachHang> dsKhachHang = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectDB.getConnection();
            String sql = "SELECT maKhachHang, hoTen, soDienThoai, laNguoiVietNam "
                       + "FROM KhachHang WHERE soDienThoai LIKE ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + soDT + "%"); // dùng LIKE để tìm gần đúng
            rs = ps.executeQuery();

            while (rs.next()) {
                String maKhachHang = rs.getString("maKhachHang");
                String hoTen = rs.getString("hoTen");
                String soDienThoai = rs.getString("soDienThoai");
                boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");

                KhachHang kh = new KhachHang(maKhachHang, hoTen, soDienThoai, laNguoiVietNam);
                dsKhachHang.add(kh);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return dsKhachHang;
    }
    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> dsKhachHang = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectDB.getConnection();
            String sql = "SELECT maKhachHang, hoTen, soDienThoai, laNguoiVietNam FROM KhachHang";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String maKhachHang = rs.getString("maKhachHang");
                String hoTen = rs.getString("hoTen");
                String soDienThoai = rs.getString("soDienThoai");
                boolean laNguoiVietNam = rs.getBoolean("laNguoiVietNam");

                KhachHang kh = new KhachHang(maKhachHang, hoTen, soDienThoai, laNguoiVietNam);
                dsKhachHang.add(kh);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return dsKhachHang;
    }
    public boolean themKhachHang(KhachHang kh) {
        String sql = "INSERT INTO KhachHang (maKhachHang, hoTen, soDienThoai, laNguoiVietNam) VALUES (?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, kh.getMaKhachHang());
            ps.setString(2, kh.getHoTen());
            ps.setString(3, kh.getSoDienThoai());
            ps.setBoolean(4, kh.LaNguoiVietNam());

            int result = ps.executeUpdate();
            return result > 0; // trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    public boolean capNhatKhachHang(KhachHang kh) {
        String sql = "UPDATE KhachHang SET hoTen = ?, soDienThoai = ?, laNguoiVietNam = ? WHERE maKhachHang = ?";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectDB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSoDienThoai());
            ps.setBoolean(3, kh.LaNguoiVietNam());
            ps.setString(4, kh.getMaKhachHang());

            int result = ps.executeUpdate();
            return result > 0; // ✅ true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


}
