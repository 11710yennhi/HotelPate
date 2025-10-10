package entity;
// đã sửa
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
	
	public NhanVien() {	
	}
	
	public NhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public NhanVien(String maNhanVien, String hoten, String soDienThoai, String email, boolean vaiTro,
			LocalDate thoiGianTao, boolean trangThai) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoten = hoten;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.chucVu = vaiTro;
		this.thoiGianTao = thoiGianTao;
		this.trangThai = trangThai;
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

	public boolean isVaiTro() {
		return chucVu;
	}

	public void setVaiTro(boolean vaiTro) {
		this.chucVu = vaiTro;
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

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoten=" + hoten + ", soDienThoai=" + soDienThoai + ", email="
				+ email + ", vaiTro=" + chucVu + ", thoiGianTao=" + thoiGianTao + ", trangThai=" + trangThai + "]";
	}
	
	
}
