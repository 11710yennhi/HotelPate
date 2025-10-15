package entity;

import java.util.Objects;

public class Phong {
 private String maLoaiPhong;
 private LoaiPhong loaiPhong;
 
public Phong(String maLoaiPhong, LoaiPhong loaiPhong) {
	super();
	this.maLoaiPhong = maLoaiPhong;
	this.loaiPhong = loaiPhong;
}
public Phong(String maLoaiPhong) {
	super();
	this.maLoaiPhong = maLoaiPhong;
}
public Phong() {
	super();
}
public String getMaLoaiPhong() {
	return maLoaiPhong;
}
public void setMaLoaiPhong(String maLoaiPhong) {
	this.maLoaiPhong = maLoaiPhong;
}
public LoaiPhong getLoaiPhong() {
	return loaiPhong;
}
public void setLoaiPhong(LoaiPhong loaiPhong) {
	this.loaiPhong = loaiPhong;
}
@Override
public String toString() {
	return "Phong [maLoaiPhong=" + maLoaiPhong + ", loaiPhong=" + loaiPhong + "]";
}
@Override
public int hashCode() {
	return Objects.hash(maLoaiPhong);
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
	return Objects.equals(maLoaiPhong, other.maLoaiPhong);
}

 
}
