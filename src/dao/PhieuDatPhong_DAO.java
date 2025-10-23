package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import connectDB.ConnectDB;
import entity.PhieuDatPhong;

public class PhieuDatPhong_DAO {

    // Thêm phiếu đặt phòng mới vào CSDL
    public boolean themPhieuDatPhong(PhieuDatPhong pdt) {
        String query = "INSERT INTO PhieuDatPhong (maPhieuDatPhong, maKhachHang, maNhanVien, ngayTao, trangThai) "
                     + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, pdt.getMaPhieuDatPhong());
            ps.setString(2, pdt.getKhachHang().getMaKhachHang());
            ps.setString(3, pdt.getNhanVien().getMaNhanVien());
            ps.setDate(4, Date.valueOf(pdt.getNgayTao()));
            ps.setString(5, pdt.getTrangThai());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
