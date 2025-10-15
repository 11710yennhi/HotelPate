package entity;

import java.util.Objects;

public class LoaiPhong {
private String maLoaiPhong;
private String tenLoaiPhong;
private int suaChua;
private double gia;
private String moTa;



public LoaiPhong() {
	
}

public LoaiPhong(String maLoaiPhong) {
	this.maLoaiPhong = maLoaiPhong;
}

public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, int suaChua, double gia, String moTa) {
	this.maLoaiPhong = maLoaiPhong;
	this.tenLoaiPhong = tenLoaiPhong;
	this.suaChua = suaChua;
	this.gia = gia;
	this.moTa = moTa;
}

public String getMaLoaiPhong() {
	return maLoaiPhong;
}

public void setMaLoaiPhong(String maLoaiPhong) {
	this.maLoaiPhong = maLoaiPhong;
}

public String getTenLoaiPhong() {
	return tenLoaiPhong;
}

public void setTenLoaiPhong(String tenLoaiPhong) {
	this.tenLoaiPhong = tenLoaiPhong;
}

public int getSuaChua() {
	return suaChua;
}

public void setSuaChua(int suaChua) {
	this.suaChua = suaChua;
}

public double getGia() {
	return gia;
}

public void setGia(double gia) {
	this.gia = gia;
}

public String getMoTa() {
	return moTa;
}

public void setMoTa(String moTa) {
	this.moTa = moTa;
}

@Override
public String toString() {
	return "LoaiPhong [maLoaiPhong=" + maLoaiPhong + ", tenLoaiPhong=" + tenLoaiPhong + ", suaChua=" + suaChua
			+ ", gia=" + gia + ", moTa=" + moTa + "]";
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
	LoaiPhong other = (LoaiPhong) obj;
	return Objects.equals(maLoaiPhong, other.maLoaiPhong);
}


}
