package dao;

import java.sql.*;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.LoaiPhong;

public class LoaiPhong_DAO {

    // ✅ Lấy toàn bộ danh sách loại phòng trong CSDL
    public ArrayList<LoaiPhong> getAllLoaiPhong() {
        ArrayList<LoaiPhong> dsLoaiPhong = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Kết nối cơ sở dữ liệu
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM LoaiPhong";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Duyệt kết quả trả về
            while (rs.next()) {
                String maLoaiPhong = rs.getString("maLoaiPhong");
                String tenLoaiPhong = rs.getString("tenLoaiPhong");
                int suaChua = rs.getInt("sucChua");
                double gia = rs.getDouble("gia");
                String moTa = rs.getString("moTa");

                LoaiPhong lp = new LoaiPhong(maLoaiPhong, tenLoaiPhong, suaChua, gia, moTa);
                dsLoaiPhong.add(lp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return dsLoaiPhong;
    }
    public LoaiPhong getLoaiPhongTheoMa(String maLoaiPhong) {
        LoaiPhong lp = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Kết nối CSDL
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM LoaiPhong WHERE maLoaiPhong = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maLoaiPhong);

            rs = stmt.executeQuery();

            // Nếu có kết quả, tạo đối tượng LoaiPhong
            if (rs.next()) {
                String tenLoaiPhong = rs.getString("tenLoaiPhong");
                int sucChua = rs.getInt("sucChua");
                double gia = rs.getDouble("gia");
                String moTa = rs.getString("moTa");

                lp = new LoaiPhong(maLoaiPhong, tenLoaiPhong, sucChua, gia, moTa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lp;
    }
    public LoaiPhong getLoaiPhongTheoTen(String tenLoaiPhong) {
        LoaiPhong lp = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Kết nối đến cơ sở dữ liệu
            con = ConnectDB.getConnection();
            String sql = "SELECT * FROM LoaiPhong WHERE tenLoaiPhong = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tenLoaiPhong);

            rs = stmt.executeQuery();

            // Nếu có kết quả, tạo đối tượng LoaiPhong
            if (rs.next()) {
                String maLoaiPhong = rs.getString("maLoaiPhong");
                int sucChua = rs.getInt("sucChua");
                double gia = rs.getDouble("gia");
                String moTa = rs.getString("moTa");

                lp = new LoaiPhong(maLoaiPhong, tenLoaiPhong, sucChua, gia, moTa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lp;
    }

}
