package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChiTietPhieuDatPhong;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.LoaiPhong;

public class ChiTietPhieuDatPhong_DAO {

    public List<ChiTietPhieuDatPhong> getChiTietTheoMaPhieu(String maPhieuDatPhong) {
        List<ChiTietPhieuDatPhong> dsCT = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Kết nối CSDL
            con = ConnectDB.getInstance().getConnection();

            String sql = """
                SELECT ctpdp.maPhieuDatPhong, ctpdp.maPhong,
                       ctpdp.ngayNhanThuc, ctpdp.ngayTraThuc, ctpdp.trangThai AS trangThaiCT,
                       p.trangThai AS trangThaiPhong,
                       lp.maLoaiPhong, lp.tenLoaiPhong, lp.sucChua, lp.gia, lp.moTa
                FROM ChiTietPhieuDatPhong ctpdp
                JOIN Phong p ON ctpdp.maPhong = p.maPhong
                JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong
                WHERE ctpdp.maPhieuDatPhong = ?
            """;

            ps = con.prepareStatement(sql);
            ps.setString(1, maPhieuDatPhong);
            rs = ps.executeQuery();

            while (rs.next()) {
                // --- Thông tin phiếu ---
                PhieuDatPhong phieu = new PhieuDatPhong(maPhieuDatPhong);

                // --- Thông tin loại phòng ---
                String maLoaiPhong = rs.getString("maLoaiPhong");
                String tenLoaiPhong = rs.getString("tenLoaiPhong");
                int sucChua = rs.getInt("sucChua");
                double gia = rs.getDouble("gia");
                String moTa = rs.getString("moTa");

                LoaiPhong loai = new LoaiPhong(maLoaiPhong, tenLoaiPhong, sucChua, gia, moTa);

                // --- Thông tin phòng ---
                String maPhong = rs.getString("maPhong");
                String trangThaiPhong = rs.getString("trangThaiPhong");
                Phong phong = new Phong(maPhong, loai, trangThaiPhong);

                // --- Thông tin ngày và trạng thái chi tiết ---
                Date ngayNhanSQL = rs.getDate("ngayNhanThuc");
                Date ngayTraSQL = rs.getDate("ngayTraThuc");
                LocalDate ngayNhan = ngayNhanSQL != null ? ngayNhanSQL.toLocalDate() : null;
                LocalDate ngayTra = ngayTraSQL != null ? ngayTraSQL.toLocalDate() : null;

                String trangThaiChiTiet = rs.getString("trangThaiCT");

                // --- Tạo đối tượng chi tiết ---
                ChiTietPhieuDatPhong ct = new ChiTietPhieuDatPhong(phieu, phong, ngayNhan, ngayTra, trangThaiChiTiet);
                dsCT.add(ct);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return dsCT;
    }
}
