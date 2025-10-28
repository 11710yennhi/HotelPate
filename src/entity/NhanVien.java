//package entity;
//<<<<<<< HEAD
//import java.time.LocalDate;
//import java.util.Objects;
//=======
//>>>>>>> 9f14c2473b645f98db78a8eaf804491cde972697
//
//import java.time.LocalDate;
//import java.util.Objects;
//
//public class NhanVien {
//<<<<<<< HEAD
//	private String maNhanVien;
//	private String hoten;
//	private boolean gioiTinh;
//	private LocalDate ngaySinh;
//	private String soDienThoai;
//	private String email;
//	private boolean chucVu;
//	private LocalDate ngayTao;
//	private boolean trangThai;
//	
//	
//	
//	public NhanVien() {	
//	}
//	
//	public NhanVien(String maNhanVien) {
//		this.maNhanVien = maNhanVien;
//	}
//
//	public String getMaNhanVien() {
//		return maNhanVien;
//	}
//
//	public void setMaNhanVien(String maNhanVien) {
//		this.maNhanVien = maNhanVien;
//	}
//
//	public String getHoten() {
//		return hoten;
//	}
//
//	public void setHoten(String hoten) {
//		this.hoten = hoten;
//	}
//
//	public boolean isGioiTinh() {
//		return gioiTinh;
//	}
//
//	public void setGioiTinh(boolean gioiTinh) {
//		this.gioiTinh = gioiTinh;
//	}
//
//	public LocalDate getNgaySinh() {
//		return ngaySinh;
//	}
//
//	public void setNgaySinh(LocalDate ngaySinh) {
//		this.ngaySinh = ngaySinh;
//	}
//
//	public String getSoDienThoai() {
//		return soDienThoai;
//	}
//
//	public void setSoDienThoai(String soDienThoai) {
//		this.soDienThoai = soDienThoai;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public boolean isChucVu() {
//		return chucVu;
//	}
//
//	public void setChucVu(boolean chucVu) {
//		this.chucVu = chucVu;
//	}
//
//	public LocalDate getNgayTao() {
//		return ngayTao;
//	}
//
//	public void setNgayTao(LocalDate ngayTao) {
//		this.ngayTao = ngayTao;
//	}
//
//	public boolean isTrangThai() {
//		return trangThai;
//	}
//
//	public void setTrangThai(boolean trangThai) {
//		this.trangThai = trangThai;
//	}
//
//	
//	@Override
//	public int hashCode() {
//		return Objects.hash(chucVu);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		NhanVien other = (NhanVien) obj;
//		return chucVu == other.chucVu;
//	}
//
//	@Override
//	public String toString() {
//		return "NhanVien [maNhanVien=" + maNhanVien + ", hoten=" + hoten + ", gioiTinh=" + gioiTinh + ", ngaySinh="
//				+ ngaySinh + ", soDienThoai=" + soDienThoai + ", email=" + email + ", chucVu=" + chucVu + ", ngayTao="
//				+ ngayTao + ", trangThai=" + trangThai + "]";
//	}
//
//	
//=======
//    private String maNhanVien;
//    private String hoten;
//    private String soDienThoai;
//    private String email;
//    private boolean chucVu;
//    private LocalDate ngayTao;
//    private boolean trangThai;
//    private boolean gioiTinh;
//    private LocalDate ngaySinh;
//
//    public NhanVien() {
//    }
//
//    public NhanVien(String maNhanVien) {
//        this.maNhanVien = maNhanVien;
//    }
//
//    public NhanVien(String maNhanVien, String hoten, String soDienThoai, String email, boolean chucVu,
//            LocalDate thoiGianTao, boolean trangThai, boolean gioiTinh, LocalDate ngaySinh) {
//        this.maNhanVien = maNhanVien;
//        this.hoten = hoten;
//        this.soDienThoai = soDienThoai;
//        this.email = email;
//        this.chucVu = chucVu;
//        this.ngayTao = ngayTao;
//        this.trangThai = trangThai;
//        this.gioiTinh = gioiTinh;
//        this.ngaySinh = ngaySinh;
//    }
//
//    public String getMaNhanVien() {
//        return maNhanVien;
//    }
//
//    public void setMaNhanVien(String maNhanVien) {
//        if (maNhanVien == null || maNhanVien.trim().isEmpty()) {
//            throw new IllegalArgumentException("Mã nhân viên không được để trống.");
//        }
//        this.maNhanVien = maNhanVien;
//    }
//
//    public String getHoten() {
//        return hoten;
//    }
//
//    public void setHoten(String hoten) {
//        if (hoten == null || hoten.trim().isEmpty()) {
//            throw new IllegalArgumentException("Họ tên không được để trống.");
//        }
//        this.hoten = hoten;
//    }
//
//    public String getSoDienThoai() {
//        return soDienThoai;
//    }
//
//    public void setSoDienThoai(String soDienThoai) {
//        if (soDienThoai == null || soDienThoai.trim().isEmpty()) {
//            throw new IllegalArgumentException("Số điện thoại không được để trống.");
//        }
//        this.soDienThoai = soDienThoai;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        if (email == null || email.trim().isEmpty()) {
//            throw new IllegalArgumentException("Email không được để trống.");
//        }
//        this.email = email;
//    }
//
//    public boolean isChucVu() {
//        return chucVu;
//    }
//
//    public void setChucVu(boolean chucVu) {
//        this.chucVu = chucVu;
//    }
//
//    public LocalDate getNgayTao() {
//        return ngayTao;
//    }
//
//    public void setNgayTao(LocalDate thoiGianTao) {
//        if (thoiGianTao == null) {
//            throw new IllegalArgumentException("Thời gian tạo không được để trống.");
//        }
//        this.ngayTao = thoiGianTao;
//    }
//
//    public boolean isTrangThai() {
//        return trangThai;
//    }
//
//    public void setTrangThai(boolean trangThai) {
//        this.trangThai = trangThai;
//    }
//
//    public boolean isGioiTinh() {
//        return gioiTinh;
//    }
//
//    public void setGioiTinh(boolean gioiTinh) {
//        this.gioiTinh = gioiTinh;
//    }
//
//    public LocalDate getNgaySinh() {
//        return ngaySinh;
//    }
//
//    public void setNgaySinh(LocalDate ngaySinh) {
//        if (ngaySinh == null) {
//            throw new IllegalArgumentException("Ngày sinh không được để trống.");
//        }
//        this.ngaySinh = ngaySinh;
//    }
//
//	@Override
//	public String toString() {
//		return "NhanVien [maNhanVien=" + maNhanVien + ", hoten=" + hoten + ", soDienThoai=" + soDienThoai + ", email="
//				+ email + ", chucVu=" + chucVu + ", thoiGianTao=" + ngayTao + ", trangThai=" + trangThai
//				+ ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(maNhanVien);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		NhanVien other = (NhanVien) obj;
//		return Objects.equals(maNhanVien, other.maNhanVien);
//	}
//    
//    
//>>>>>>> 9f14c2473b645f98db78a8eaf804491cde972697
//}

package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
    private String maNhanVien;
    private String hoten;
    private boolean gioiTinh;
    private LocalDate ngaySinh;
    private String soDienThoai;
    private String email;
    private boolean chucVu;
    private LocalDate ngayTao;
    private boolean trangThai;

    public NhanVien() {
    }

    public NhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public NhanVien(String maNhanVien, String hoten, boolean gioiTinh, LocalDate ngaySinh,
                    String soDienThoai, String email, boolean chucVu,
                    LocalDate ngayTao, boolean trangThai) {
        this.maNhanVien = maNhanVien;
        this.hoten = hoten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.chucVu = chucVu;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        if (maNhanVien == null || maNhanVien.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã nhân viên không được để trống.");
        }
        this.maNhanVien = maNhanVien;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        if (hoten == null || hoten.trim().isEmpty()) {
            throw new IllegalArgumentException("Họ tên không được để trống.");
        }
        this.hoten = hoten;
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
        if (ngaySinh == null) {
            throw new IllegalArgumentException("Ngày sinh không được để trống.");
        }
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        if (soDienThoai == null || soDienThoai.trim().isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại không được để trống.");
        }
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống.");
        }
        this.email = email;
    }

    public boolean isChucVu() {
        return chucVu;
    }

    public void setChucVu(boolean chucVu) {
        this.chucVu = chucVu;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        if (ngayTao == null) {
            throw new IllegalArgumentException("Ngày tạo không được để trống.");
        }
        this.ngayTao = ngayTao;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "NhanVien [maNhanVien=" + maNhanVien + ", hoten=" + hoten + ", gioiTinh=" + gioiTinh
                + ", ngaySinh=" + ngaySinh + ", soDienThoai=" + soDienThoai + ", email=" + email
                + ", chucVu=" + chucVu + ", ngayTao=" + ngayTao + ", trangThai=" + trangThai + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(maNhanVien);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        NhanVien other = (NhanVien) obj;
        return Objects.equals(maNhanVien, other.maNhanVien);
    }
}
