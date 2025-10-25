package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;

public class PhieuDatPhong_DAO {


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

 
//    public boolean xoaPhieuDatPhong(String maPhieuDatPhong) {
//        String query = "DELETE FROM PhieuDatPhong WHERE maPhieuDatPhong = ?";
//
//        try (Connection con = ConnectDB.getConnection();
//             PreparedStatement ps = con.prepareStatement(query)) {
//
//            ps.setString(1, maPhieuDatPhong);
//
//            int rowsAffected = ps.executeUpdate();
//            return rowsAffected > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }


    public PhieuDatPhong timPhieuDatPhongTheoMa(String maPhieuDatPhong) {
        String query = "SELECT * FROM PhieuDatPhong WHERE maPhieuDatPhong = ?";
        PhieuDatPhong pdt = null;

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, maPhieuDatPhong);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pdt = new PhieuDatPhong(
                    rs.getString("maPhieuDatPhong"),
                    new KhachHang(rs.getString("maKhachHang")),
                    new NhanVien(rs.getString("maNhanVien")),
                    rs.getDate("ngayTao").toLocalDate(),
                    rs.getString("trangThai")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pdt;
    }
}
