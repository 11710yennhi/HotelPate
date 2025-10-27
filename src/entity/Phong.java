package entity;

import java.util.Objects;

public class Phong {
	private String maPhong;
	private LoaiPhong loaiPhong;
	private String trangThai;
	public Phong(String maPhong, LoaiPhong loaiPhong, String trangThai) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.trangThai = trangThai;
	}
	
	
	public Phong() {
		super();
	}


	public Phong(String maPhong) {
		super();
		this.maPhong = maPhong;
	}


	public String getMaPhong() {
		return maPhong;
	}


	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}


	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}


	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}


	public String getTrangThai() {
		return trangThai;
	}


	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}


	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", loaiPhong=" + loaiPhong + ", trangThai=" + trangThai + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}

}
