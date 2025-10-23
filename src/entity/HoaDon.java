//package entity;
//
//import java.sql.Date;
//import java.util.Objects;
//
//public class HoaDon {
//	private String maHoaDon;
//	private PhieuDatPhong phieuDatPhong;
//	private Date ngayTraPhongThuc;
//	private KhuyenMai khuyenMai;
//	private String phuongThucThanhToan;
//
//	// Constructor
//	public HoaDon(String maHoaDon, PhieuDatPhong phieuDatPhong, Date ngayTraPhongThuc, KhuyenMai khuyenMai,
//			String phuongThucThanhToan) {
//		this.maHoaDon = maHoaDon;
//		this.phieuDatPhong = phieuDatPhong;
//		this.ngayTraPhongThuc = ngayTraPhongThuc;
//		this.khuyenMai = khuyenMai;
//		this.phuongThucThanhToan = phuongThucThanhToan;
//	}
//
//	public HoaDon(String maHoaDon) {
//		this.maHoaDon = maHoaDon;
//	}
//
//	public HoaDon() {
//
//	}
//
//	// Getter & Setter
//	public String getMaHoaDon() {
//		return maHoaDon;
//	}
//
//	public void setMaHoaDon(String maHoaDon) {
//		this.maHoaDon = maHoaDon;
//	}
//
//	public PhieuDatPhong getPhieuDatPhong() {
//		return phieuDatPhong;
//	}
//
//	public void setPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
//		this.phieuDatPhong = phieuDatPhong;
//	}
//
//	public Date getNgayTraPhongThuc() {
//		return ngayTraPhongThuc;
//	}
//
//	public void setNgayTraPhongThuc(Date ngayTraPhongThuc) {
//		this.ngayTraPhongThuc = ngayTraPhongThuc;
//	}
//
//	public KhuyenMai getKhuyenMai() {
//		return khuyenMai;
//	}
//
//	public void setKhuyenMai(KhuyenMai khuyenMai) {
//		this.khuyenMai = khuyenMai;
//	}
//
//	public String getPhuongThucThanhToan() {
//		return phuongThucThanhToan;
//	}
//
//	public void setPhuongThucThanhToan(String phuongThucThanhToan) {
//		this.phuongThucThanhToan = phuongThucThanhToan;
//	}
//
//	// Thuoc tinh dan xuat
//	public double getTongTien() {
//		if (phieuDatPhong == null)
//			return 0;
//		return phieuDatPhong.getTongTien();
//	}
//
//	public double getTongThanhToan() {
//		double tongTien = getTongTien();
//		if (khuyenMai == null)
//			return tongTien;
//
//		// ví dụ: khuyến mãi giảm theo %
//		if ("Phần trăm".equalsIgnoreCase(khuyenMai.getLoaiKhuyenMai())) {
//			return tongTien * (1 - khuyenMai.getGiaTriGiam() / 100);
//		}
//		// hoặc giảm trực tiếp theo số tiền
//		else {
//			return Math.max(0, tongTien - khuyenMai.getGiaTriGiam());
//		}
//	}
//
//	// equals & hashCode
//	@Override
//	public int hashCode() {
//		return Objects.hash(maHoaDon);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (!(obj instanceof HoaDon))
//			return false;
//		HoaDon other = (HoaDon) obj;
//		return Objects.equals(maHoaDon, other.maHoaDon);
//	}
//
//	// toString
//	@Override
//	public String toString() {
//		return "HoaDon [maHoaDon=" + maHoaDon + ", phieuDatPhong=" + phieuDatPhong + ", ngayTraPhongThuc="
//				+ ngayTraPhongThuc + ", khuyenMai=" + khuyenMai + ", tongTien=" + getTongTien() + ", tongThanhToan="
//				+ getTongThanhToan() + ", phuongThucThanhToan=" + phuongThucThanhToan + "]";
//	}
//}
