package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_DAO {
    public NhanVien getNhanVienTheoMa(String maNhanVien) {
        String query = "SELECT * FROM NhanVien WHERE maNhanVien = ?";
        NhanVien nv = null;

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, maNhanVien);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("maNhanVien"));
                nv.setHoten(rs.getString("hoTen"));
                nv.setGioiTinh(rs.getBoolean("gioiTinh"));
                
                // Nếu cột ngày sinh có dữ liệu thì mới chuyển đổi sang LocalDate
                Date ngaySinh = rs.getDate("ngaySinh");
                if (ngaySinh != null)
                    nv.setNgaySinh(ngaySinh.toLocalDate());

                nv.setSoDienThoai(rs.getString("soDienThoai"));
                nv.setEmail(rs.getString("email"));
                nv.setChucVu(rs.getBoolean("chucVu"));
                
                Date ngayTao = rs.getDate("ngayTao");
                if (ngayTao != null)
                    nv.setNgayTao(ngayTao.toLocalDate());

                nv.setTrangThai(rs.getBoolean("trangThai"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nv;
    }
}
