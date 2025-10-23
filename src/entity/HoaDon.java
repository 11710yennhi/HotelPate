package entity;

import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private PhieuDatPhong phieuDatPhong;
	private KhuyenMai khuyenMai;
	private String phuongThucThanhToan;
	// Thuộc tính dẫn xuất
	private double tongTien;
	private double tongThanhToan;

	public HoaDon() {
	}

	public HoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public HoaDon(String maHoaDon, PhieuDatPhong phieuDatPhong, KhuyenMai khuyenMai, String phuongThucThanhToan) {
		this.maHoaDon = maHoaDon;
		this.phieuDatPhong = phieuDatPhong;
		this.khuyenMai = khuyenMai;
		this.phuongThucThanhToan = phuongThucThanhToan;
		tinhTongTien();
		tinhTongThanhToan();
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public PhieuDatPhong getPhieuDatPhong() {
		return phieuDatPhong;
	}

	public void setPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
		this.phieuDatPhong = phieuDatPhong;
		tinhTongTien();
		tinhTongThanhToan();
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
		tinhTongTien();
		tinhTongThanhToan();
	}

	public String getPhuongThucThanhToan() {
		return phuongThucThanhToan;
	}

	public void setPhuongThucThanhToan(String phuongThucThanhToan) {
		this.phuongThucThanhToan = phuongThucThanhToan;
	}

	public double getTongTien() {
		return tongTien;
	}

	public double getTongThanhToan() {
		return tongThanhToan;
	}

	// TÍNH TOÁN

	public void tinhTongTien() {
		double tongTien = 0;

		if (phieuDatPhong != null) {
			this.tongTien = phieuDatPhong.getTongTien();
		}
	}

	public void tinhTongThanhToan() {
		double tienGiam = 0;
		double tienCoc = 0;

		if (phieuDatPhong != null) {
			tienCoc = phieuDatPhong.getTienCoc();
		}

		// Xử lý khuyến mãi
		if (khuyenMai != null) {
			if (khuyenMai.getLoaiKhuyenMai().equalsIgnoreCase("phần trăm")) {
				tienGiam = tongTien * khuyenMai.getGiaTriGiam() / 100.0;
			} else
				tienGiam = khuyenMai.getGiaTriGiam();
		}
		this.tongThanhToan = tongTien - tienCoc - tienGiam;
		if (tongThanhToan < 0)
			tongThanhToan = 0;
	}

	// Overide

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", phieuDatPhong="
				+ (phieuDatPhong != null ? phieuDatPhong.getMaPhieuDatPhong() : "null") + ", khuyenMai="
				+ (khuyenMai != null ? khuyenMai.getMaKhuyenMai() : "null") + ", tongTien=" + tongTien
				+ ", tongThanhToan=" + tongThanhToan + ", phuongThucThanhToan=" + phuongThucThanhToan + "]";
	}
}
