package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import entity.HoaDon;
import entity.PhieuDatPhong;
import entity.KhuyenMai;
import connectDB.ConnectDB;

public class HoaDon_DAO {

    public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> ds = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM HoaDon";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String maHD = rs.getString("maHoaDon");
                String maPDP = rs.getString("maPhieuDatPhong");
                String maKM = rs.getString("maKhuyenMai");
                String phuongThuc = rs.getString("phuongThucThanhToan");

                PhieuDatPhong pdp = new PhieuDatPhong(maPDP);
                KhuyenMai km = maKM != null ? new KhuyenMai(maKM) : null;

                HoaDon hd = new HoaDon(maHD, pdp, km, phuongThuc);

                // Gọi lại tính tiền (do PDP và KM lúc này mới được gán ID)
                hd.tinhTongTien();
                hd.tinhTongThanhToan();
                
                ds.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public HoaDon getHoaDonTheoMa(String maHoDon) {
        HoaDon hd = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM HoaDon WHERE maHoaDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maHoDon);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String maPDP = rs.getString("maPhieuDatPhong");
                String maKM = rs.getString("maKhuyenMai");
                String phuongThuc = rs.getString("phuongThucThanhToan");

                PhieuDatPhong pdp = new PhieuDatPhong(maPDP);
                KhuyenMai km = maKM != null ? new KhuyenMai(maKM) : null;

                hd = new HoaDon(maHoDon, pdp, km, phuongThuc);

                hd.tinhTongTien();
                hd.tinhTongThanhToan();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hd;
    }

    public boolean themHoaDon(HoaDon hd) {
        int n = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "INSERT INTO HoaDon VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, hd.getMaHoaDon());
            stmt.setString(2, hd.getPhieuDatPhong().getMaPhieuDatPhong());
            stmt.setString(3, hd.getKhuyenMai() != null ? hd.getKhuyenMai().getMaKhuyenMai() : null);
            stmt.setString(4, hd.getPhuongThucThanhToan());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean xoaHoaDon(String maHD) {
        int n = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "DELETE FROM HoaDon WHERE maHoaDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maHD);

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean capNhatPhuongThucThanhToan(String maHD, String phuongThucMoi) {
        int n = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "UPDATE HoaDon SET phuongThucThanhToan = ? WHERE maHoaDon = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, phuongThucMoi);
            stmt.setString(2, maHD);

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    public HashMap<String, Object> getThongTinChiTietHoaDon(String maHD) {
        HashMap<String, Object> thongTin = new HashMap<>();

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = """
                SELECT 
                    hd.maHoaDon,
                    hd.phuongThucThanhToan,
                    pdp.maPhieuDatPhong,
                    kh.hoTen AS tenKhachHang,
                    nv.maNhanVien,
                    ctpdp.ngayNhanThuc,
                    ctpdp.ngayTraThuc,
                    pdp.trangThai
                FROM HoaDon hd
                JOIN PhieuDatPhong pdp ON hd.maPhieuDatPhong = pdp.maPhieuDatPhong
                JOIN KhachHang kh ON pdp.maKhachHang = kh.maKhachHang
                JOIN NhanVien nv ON pdp.maNhanVien = nv.maNhanVien
                JOIN ChiTietPhieuDatPhong ctpdp ON ctpdp.maPhieuDatPhong = pdp.maPhieuDatPhong
                WHERE hd.maHoaDon = ?
            """;

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maHD);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                thongTin.put("maHoaDon", rs.getString("maHoaDon"));
                thongTin.put("tenKhachHang", rs.getString("tenKhachHang"));
                thongTin.put("ngayNhan", rs.getDate("ngayNhanThuc"));
                thongTin.put("ngayTra", rs.getDate("ngayTraThuc"));
                thongTin.put("maNhanVien", rs.getString("maNhanVien"));
                thongTin.put("trangThai", rs.getString("trangThai"));
                thongTin.put("maPhieuDatPhong", rs.getString("maPhieuDatPhong"));
                thongTin.put("phuongThucThanhToan", rs.getString("phuongThucThanhToan"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return thongTin;
    }
    public ArrayList<Object[]> getChiTietPhongTheoHoaDon(String maHD) {
        ArrayList<Object[]> ds = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = """
                SELECT 
                    p.maPhong, 
                    lp.tenLoaiPhong, 
                    lp.gia,
                    DATEDIFF(DAY, ctpdp.ngayNhanThuc, ctpdp.ngayTraThuc) AS soNgay
                FROM HoaDon hd
                JOIN PhieuDatPhong pdp ON hd.maPhieuDatPhong = pdp.maPhieuDatPhong
                JOIN ChiTietPhieuDatPhong ctpdp ON pdp.maPhieuDatPhong = ctpdp.maPhieuDatPhong
                JOIN Phong p ON ctpdp.maPhong = p.maPhong
                JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong
                WHERE hd.maHoaDon = ?
            """;

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maHD);
            ResultSet rs = stmt.executeQuery();

            int stt = 1;
            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String loaiPhong = rs.getString("tenLoaiPhong");
                double gia = rs.getDouble("gia");
                int soNgay = rs.getInt("soNgay");
                double thanhTien = gia * soNgay;

                ds.add(new Object[]{
                    stt++,
                    maPhong,
                    loaiPhong,
                    String.format("%,.0f", gia),
                    String.format("%,.0f", thanhTien)
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
    public ArrayList<Object[]> getChiTietChiPhiTheoHoaDon(String maHD) {
        ArrayList<Object[]> ds = new ArrayList<>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = """
                SELECT 
                    dv.tenDichVu,
                    ctpdv.soLuong,
                    dv.donGia,
                    (ctpdv.soLuong * dv.donGia) AS thanhTien
                FROM HoaDon hd
                JOIN PhieuDatPhong pdp ON hd.maPhieuDatPhong = pdp.maPhieuDatPhong
                JOIN ChiTietPhieuDatDichVu ctpdv ON pdp.maPhieuDatPhong = ctpdv.maPhieuDatPhong
                JOIN DichVu dv ON ctpdv.maDichVu = dv.maDichVu
                WHERE hd.maHoaDon = ?
            """;

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maHD);
            ResultSet rs = stmt.executeQuery();

            int stt = 1;
            while (rs.next()) {
                String tenDV = rs.getString("tenDichVu");
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");
                double thanhTien = rs.getDouble("thanhTien");

                ds.add(new Object[]{
                    stt++,
                    tenDV,
                    soLuong,
                    String.format("%,.0f", donGia),
                    String.format("%,.0f", thanhTien)
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
    public HoaDon tinhTienHoaDon(String maHoaDon) {
        HoaDon hoaDon = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = """
                SELECT 
                    hd.maHoaDon,
                    SUM(lp.gia * DATEDIFF(DAY, ctpdp.ngayNhanThuc, ctpdp.ngayTraThuc)) AS tongTienPhong,
                    ISNULL(km.giaTriGiam, 0) AS giaTriGiam,
                    ISNULL(km.loaiKhuyenMai, '') AS loaiKhuyenMai,
                    ISNULL(pdp.tienCoc, 0) AS tienCoc
                FROM HoaDon hd
                JOIN PhieuDatPhong pdp ON hd.maPhieuDatPhong = pdp.maPhieuDatPhong
                JOIN ChiTietPhieuDatPhong ctpdp ON pdp.maPhieuDatPhong = ctpdp.maPhieuDatPhong
                JOIN Phong p ON ctpdp.maPhong = p.maPhong
                JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong
                LEFT JOIN KhuyenMai km ON pdp.maKhuyenMai = km.maKhuyenMai
                WHERE hd.maHoaDon = ?
                GROUP BY hd.maHoaDon, km.giaTriGiam, km.loaiKhuyenMai, pdp.tienCoc
            """;

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maHoaDon);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));

                double tongTienPhong = rs.getDouble("tongTienPhong");
                double tienCoc = rs.getDouble("tienCoc");
                double giaTriGiam = rs.getDouble("giaTriGiam");
                String loaiGiam = rs.getString("loaiKhuyenMai");

                double tienGiam = 0;
                if (loaiGiam.equalsIgnoreCase("phần trăm"))
                    tienGiam = tongTienPhong * giaTriGiam / 100.0;
                else
                    tienGiam = giaTriGiam;

                double tongThanhToan = tongTienPhong - tienCoc - tienGiam;
                if (tongThanhToan < 0)
                    tongThanhToan = 0;

                hoaDon.setTongTien(tongTienPhong);
                hoaDon.setTongThanhToan(tongThanhToan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDon;
    }
    public String getMaHoaDonTheoPhieu(String maPhieuDatPhong) {
        String maHD = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT maHoaDon FROM HoaDon WHERE maPhieuDatPhong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maPhieuDatPhong.trim()); // loại bỏ khoảng trắng dư thừa

            System.out.println("Tìm kiếm maPhieuDatPhong: '" + maPhieuDatPhong.trim() + "'");

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                maHD = rs.getString("maHoaDon");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maHD;
    }
    public boolean taoHoaDonMoi(String maHD, String maPDP, String phuongThuc) {
        int n = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "INSERT INTO HoaDon(maHoaDon, maPhieuDatPhong, phuongThucThanhToan) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maHD);
            stmt.setString(2, maPDP);
            stmt.setString(3, phuongThuc);

            System.out.println("SQL: " + sql);
            System.out.println("maHD=" + maHD + ", maPDP=" + maPDP + ", phuongThuc=" + phuongThuc);

            n = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace(); // xem lỗi cụ thể
        }
        return n > 0;
    }






}
