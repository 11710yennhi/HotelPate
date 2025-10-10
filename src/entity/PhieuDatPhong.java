//package entity;
//
//import java.time.LocalDate;
//
//package entity;
//
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//
//public class PhieuDatPhong {
//    private String maPhieuDatPhong;
//    private KhachHang khachHang;
//    private Phong phong;
//    private int soNguoiDiCung;
//    private LocalDate ngayNhanPhong;
//    private LocalDate ngayTraPhong;
//    private LocalDate ngayTao;
//    private String trangThai;
//
//    // Constructor
//    public PhieuDatPhong(String maPhieuDatPhong, KhachHang khachHang, Phong phong,
//                         int soNguoiDiCung, LocalDate ngayNhanPhong, LocalDate ngayTraPhong,
//                         LocalDate ngayTao, String trangThai) {
//        this.maPhieuDatPhong = maPhieuDatPhong;
//        this.khachHang = khachHang;
//        this.phong = phong;
//        this.soNguoiDiCung = soNguoiDiCung;
//        this.ngayNhanPhong = ngayNhanPhong;
//        this.ngayTraPhong = ngayTraPhong;
//        this.ngayTao = ngayTao;
//        this.trangThai = trangThai;
//    }
//
//    public PhieuDatPhong() {}
//
//    // Getter & Setter
//    public String getMaPhieuDatPhong() {
//        return maPhieuDatPhong;
//    }
//
//    public void setMaPhieuDatPhong(String maPhieuDatPhong) {
//        this.maPhieuDatPhong = maPhieuDatPhong;
//    }
//
//    public KhachHang getKhachHang() {
//        return khachHang;
//    }
//
//    public void setKhachHang(KhachHang khachHang) {
//        this.khachHang = khachHang;
//    }
//
//    public Phong getPhong() {
//        return phong;
//    }
//
//    public void setPhong(Phong phong) {
//        this.phong = phong;
//    }
//
//    public int getSoNguoiDiCung() {
//        return soNguoiDiCung;
//    }
//
//    public void setSoNguoiDiCung(int soNguoiDiCung) {
//        this.soNguoiDiCung = soNguoiDiCung;
//    }
//
//    public LocalDate getNgayNhanPhong() {
//        return ngayNhanPhong;
//    }
//
//    public void setNgayNhanPhong(LocalDate ngayNhanPhong) {
//        this.ngayNhanPhong = ngayNhanPhong;
//    }
//
//    public LocalDate getNgayTraPhong() {
//        return ngayTraPhong;
//    }
//
//    public void setNgayTraPhong(LocalDate ngayTraPhong) {
//        this.ngayTraPhong = ngayTraPhong;
//    }
//
//    public LocalDate getNgayTao() {
//        return ngayTao;
//    }
//
//    public void setNgayTao(LocalDate ngayTao) {
//        this.ngayTao = ngayTao;
//    }
//
//    public String getTrangThai() {
//        return trangThai;
//    }
//
//    public void setTrangThai(String trangThai) {
//        this.trangThai = trangThai;
//    }
//
//    // ✅ Tính số ngày thuê
//    public long getSoNgayThue() {
//        return ChronoUnit.DAYS.between(ngayNhanPhong, ngayTraPhong);
//    }
//
//    // ✅ Tính tổng tiền (giá phòng × số ngày)
//    public double getTongTien() {
//        return phong.getGiaPhong() * getSoNgayThue();
//    }
//
//    // ✅ Thuộc tính dẫn xuất: tiền cọc = 30% tổng tiền
//    public double getTienCoc() {
//        return getTongTien() * 0.3;
//    }
//
//    @Override
//    public String toString() {
//        return "PhieuDatPhong [maPhieu=" + maPhieuDatPhong +
//                ", khachHang=" + khachHang.getTenKhachHang() +
//                ", phong=" + phong.getMaPhong() +
//                ", soNguoiDiCung=" + soNguoiDiCung +
//                ", ngayNhan=" + ngayNhanPhong +
//                ", ngayTra=" + ngayTraPhong +
//                ", ngayTao=" + ngayTao +
//                ", tongTien=" + getTongTien() +
//                ", tienCoc=" + getTienCoc() +
//                ", trangThai=" + trangThai + "]";
//    }
//}
