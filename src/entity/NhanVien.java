package entity;
import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
	private String maNhanVien;
	private String hoten;
	private String soDienThoai;
	private String email;
	private boolean chucVu;
	private LocalDate thoiGianTao;
	private boolean trangThai;
	private boolean gioiTinh;
	private LocalDate ngaySinh;
	
	public NhanVien() {	
	}
	
	public NhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public NhanVien(String maNhanVien, String hoten, String soDienThoai, String email, boolean chucVu,
			LocalDate thoiGianTao, boolean trangThai, boolean gioiTinh, LocalDate ngaySinh) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoten = hoten;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.chucVu = chucVu;
		this.thoiGianTao = thoiGianTao;
		this.trangThai = trangThai;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isChucVu() {
		return chucVu;
	}

	public void setChucVu(boolean chucVu) {
		this.chucVu = chucVu;
	}

	public LocalDate getThoiGianTao() {
		return thoiGianTao;
	}

	public void setThoiGianTao(LocalDate thoiGianTao) {
		this.thoiGianTao = thoiGianTao;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	
	
}
