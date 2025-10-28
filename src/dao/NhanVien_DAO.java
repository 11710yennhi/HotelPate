	package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_DAO {
    private ArrayList<NhanVien> dsnv;

    public NhanVien_DAO() {
        dsnv = new ArrayList<NhanVien>();
    }

    // Đọc toàn bộ bảng NhanVien
    public List<NhanVien> docTuBang() {
        dsnv.clear();
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM NhanVien";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maNV = rs.getString("maNhanVien");
                String hoTen = rs.getString("hoTen");
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                LocalDate ngaySinh = rs.getDate("ngaySinh") != null ? rs.getDate("ngaySinh").toLocalDate() : null;
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                boolean chucVu = rs.getBoolean("chucVu");
                LocalDate ngayTao = rs.getDate("ngayTao") != null ? rs.getDate("ngayTao").toLocalDate() : null;
                boolean trangThai = rs.getBoolean("trangThai");

                NhanVien nv = new NhanVien(maNV, hoTen, soDienThoai, email, chucVu, ngayTao, trangThai, gioiTinh, ngaySinh);
                dsnv.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsnv;
    }

    // Lấy nhân viên theo mã
//    public NhanVien getNhanVienTheoMa(String maNV) {
//        NhanVien nv = null;
//        try {
//            Connection con = ConnectDB.getInstance().getConnection();
//            String sql = "SELECT * FROM NhanVien WHERE maNhanVien = ?";
//            PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setString(1, maNV);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                String hoTen = rs.getString("hoTen");
//                boolean gioiTinh = rs.getBoolean("gioiTinh");
//                LocalDate ngaySinh = rs.getDate("ngaySinh") != null ? rs.getDate("ngaySinh").toLocalDate() : null;
//                String soDienThoai = rs.getString("soDienThoai");
//                String email = rs.getString("email");
//                boolean chucVu = rs.getBoolean("chucVu");
//                LocalDate ngayTao = rs.getDate("ngayTao") != null ? rs.getDate("ngayTao").toLocalDate() : null;
//                boolean trangThai = rs.getBoolean("trangThai");
//
//                nv = new NhanVien(maNV, hoTen, soDienThoai, email, chucVu, ngayTao, trangThai, gioiTinh, ngaySinh);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return nv;
//    }
    
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

    // Thêm mới nhân viên
    public boolean create(NhanVien nv) {
        int n = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "INSERT INTO NhanVien (maNhanVien, hoTen, gioiTinh, ngaySinh, soDienThoai, email, chucVu, ngayTao, trangThai) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getMaNhanVien());
            ps.setString(2, nv.getHoten());
            ps.setBoolean(3, nv.isGioiTinh());
            if (nv.getNgaySinh() != null)
                ps.setDate(4, Date.valueOf(nv.getNgaySinh()));
            else
                ps.setNull(4, java.sql.Types.DATE);
            ps.setString(5, nv.getSoDienThoai());
            ps.setString(6, nv.getEmail());
            ps.setBoolean(7, nv.isChucVu());
            if (nv.getNgayTao() != null)
                ps.setDate(8, Date.valueOf(nv.getNgayTao()));
            else
                ps.setNull(8, java.sql.Types.DATE);
            ps.setBoolean(9, nv.isTrangThai());
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Xóa nhân viên
    public boolean delete(String maNV) {
        int n = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "DELETE FROM NhanVien WHERE maNhanVien = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNV);
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Cập nhật nhân viên
    public boolean update(NhanVien nv) {
        int n = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "UPDATE NhanVien SET hoTen = ?, gioiTinh = ?, ngaySinh = ?, soDienThoai = ?, email = ?, chucVu = ?, ngayTao = ?, trangThai = ? WHERE maNhanVien = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getHoten());
            ps.setBoolean(2, nv.isGioiTinh());
            if (nv.getNgaySinh() != null)
                ps.setDate(3, Date.valueOf(nv.getNgaySinh()));
            else
                ps.setNull(3, java.sql.Types.DATE);
            ps.setString(4, nv.getSoDienThoai());
            ps.setString(5, nv.getEmail());
            ps.setBoolean(6, nv.isChucVu());
            if (nv.getNgayTao() != null)
                ps.setDate(7, Date.valueOf(nv.getNgayTao()));
            else
                ps.setNull(7, java.sql.Types.DATE);
            ps.setBoolean(8, nv.isTrangThai());
            ps.setString(9, nv.getMaNhanVien());
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }
}
