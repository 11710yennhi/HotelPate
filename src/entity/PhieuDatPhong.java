package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhieuDatPhong {
   private String maPhieuDatPhong;
   private KhachHang khachHang;
   private NhanVien nhanVien;
   private LocalDate ngayTao;
   private String trangThai;
   private double tongTienPhong; // thuộc tính dẫn xuất
   private double tienCoc; // thuộc tính dẫn xuất
   private List<ChiTietPhieuDatPhong> dsChiTiet;
	public PhieuDatPhong() {
		
	}
	
	public PhieuDatPhong(String maPhieuDatPhong) {
		this.maPhieuDatPhong = maPhieuDatPhong;
	}

	public PhieuDatPhong(String maPhieuDatPhong, KhachHang khachHang, NhanVien nhanVien, LocalDate ngayTao,
			String trangThai) {
		this.maPhieuDatPhong = maPhieuDatPhong;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.ngayTao = ngayTao;
		this.trangThai = trangThai;
		this.dsChiTiet = new ArrayList<ChiTietPhieuDatPhong>();
		capNhatTongTien();
	}
	
	
	 public String getMaPhieuDatPhong() {
		return maPhieuDatPhong;
	}

	public void setMaPhieuDatPhong(String maPhieuDatPhong) {
		this.maPhieuDatPhong = maPhieuDatPhong;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public LocalDate getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDate ngayTao) {
		this.ngayTao = ngayTao;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public List<ChiTietPhieuDatPhong> getDsChiTiet() {
		return dsChiTiet;
	}

	public void setDsChiTiet(List<ChiTietPhieuDatPhong> dsChiTiet) {
		this.dsChiTiet = dsChiTiet;
	}

	public void themChiTiet(ChiTietPhieuDatPhong ct) {
	        dsChiTiet.add(ct);
	        capNhatTongTien();
	    }
	 public void capNhatTongTien() {
	        tongTienPhong = 0;
	        for (ChiTietPhieuDatPhong ct : dsChiTiet) {
	            tongTienPhong += ct.getPhong().getLoaiPhong().getGia();
	        }
	        tienCoc = tongTienPhong * (0.5);
	    }

	    public double getTongTienPhong() {
	        return tongTienPhong;
	    }

	    public double getTienCoc() {
	        return tienCoc;
	    }

		@Override
		public int hashCode() {
			return Objects.hash(maPhieuDatPhong);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PhieuDatPhong other = (PhieuDatPhong) obj;
			return Objects.equals(maPhieuDatPhong, other.maPhieuDatPhong);
		}

		@Override
		public String toString() {
			return "PhieuDatPhong [maPhieuDatPhong=" + maPhieuDatPhong + ", khachHang=" + khachHang + ", nhanVien="
					+ nhanVien + ", ngayTao=" + ngayTao + ", trangThai=" + trangThai + ", tongTienPhong="
					+ tongTienPhong + ", tienCoc=" + tienCoc + ", dsChiTiet=" + dsChiTiet + "]";
		}
	
   
}
