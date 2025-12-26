-- Xóa database cũ nếu có
IF DB_ID('QLKS') IS NOT NULL
    DROP DATABASE QLKS;
GO

-- Tạo mới database
CREATE DATABASE QLKS;
GO

USE QLKS;
GO

CREATE TABLE NhanVien (
    maNhanVien VARCHAR(14) PRIMARY KEY,
    hoTen NVARCHAR(50) NOT NULL,
    gioiTinh BIT DEFAULT 0,
    ngaySinh DATE,
    soDienThoai VARCHAR(10) NOT NULL UNIQUE,
    email VARCHAR(100),
    chucVu BIT DEFAULT 0,
    ngayTao DATE DEFAULT GETDATE(),
    trangThai BIT DEFAULT 1
);

CREATE TABLE TaiKhoan (
    maNhanVien VARCHAR(14) PRIMARY KEY,
    matKhau VARCHAR(255) NOT NULL,
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien)
);

CREATE TABLE KhachHang (
    maKhachHang VARCHAR(14) PRIMARY KEY,
    hoTen NVARCHAR(50) NOT NULL,
    soDienThoai VARCHAR(10) UNIQUE,
    laNguoiVietNam BIT DEFAULT 1
);

CREATE TABLE KhuyenMai (
    maKhuyenMai VARCHAR(14) PRIMARY KEY,
    tenKhuyenMai NVARCHAR(100),
    ngayTao DATE DEFAULT GETDATE(),
    ngayBatDau DATE NOT NULL,
    ngayKetThuc DATE NOT NULL,
    loaiKhuyenMai NVARCHAR(50) NOT NULL,
    dieuKien FLOAT NOT NULL CHECK (dieuKien >= 0),
    giaTriGiam FLOAT NOT NULL CHECK (giaTriGiam > 0),
    CONSTRAINT CK_KhuyenMai_NgayHopLe CHECK (ngayKetThuc >= ngayBatDau)
);

CREATE TABLE LoaiPhong (
    maLoaiPhong VARCHAR(5) PRIMARY KEY,
    tenLoaiPhong NVARCHAR(100) UNIQUE,
    sucChua INT CHECK (sucChua > 0),
    gia FLOAT CHECK (gia > 0),
    moTa NVARCHAR(255)
);

CREATE TABLE Phong (
    maPhong VARCHAR(4) PRIMARY KEY,
    maLoaiPhong VARCHAR(5) NOT NULL,
    trangThai NVARCHAR(50),
    FOREIGN KEY (maLoaiPhong) REFERENCES LoaiPhong(maLoaiPhong)
);

CREATE TABLE PhieuDatPhong (
    maPhieuDatPhong VARCHAR(14) PRIMARY KEY,
    maKhachHang VARCHAR(14) NOT NULL,
    maNhanVien VARCHAR(14) NOT NULL,
    ngayTao DATE DEFAULT GETDATE(),
    trangThai NVARCHAR(50),
    FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKhachHang),
    FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien)
);

CREATE TABLE ChiTietPhieuDatPhong (
    maPhieuDatPhong VARCHAR(14) NOT NULL,
    maPhong VARCHAR(4) NOT NULL,
    ngayNhanThuc DATE,
    ngayTraThuc DATE,
    trangThai NVARCHAR(50),
    PRIMARY KEY (maPhieuDatPhong, maPhong),
    FOREIGN KEY (maPhieuDatPhong) REFERENCES PhieuDatPhong(maPhieuDatPhong),
    FOREIGN KEY (maPhong) REFERENCES Phong(maPhong),
    CONSTRAINT CK_CT_PDP_Ngay CHECK (ngayTraThuc >= ngayNhanThuc)
);

CREATE TABLE ChiPhiPhatSinh (
    maChiPhiPhatSinh VARCHAR(14) PRIMARY KEY,
    tenChiPhiPhatSinh NVARCHAR(100) NOT NULL UNIQUE,
    loaiChiPhiPhatSinh NVARCHAR(100) NOT NULL,
    gia FLOAT CHECK (gia > 0)
);

CREATE TABLE ChiTietChiPhiPhatSinh (
    maPhieuDatPhong VARCHAR(14) NOT NULL,
    maChiPhiPhatSinh VARCHAR(14) NOT NULL,
    soLuong INT DEFAULT 1 CHECK (soLuong > 0),
    PRIMARY KEY (maPhieuDatPhong, maChiPhiPhatSinh),
    FOREIGN KEY (maPhieuDatPhong) REFERENCES PhieuDatPhong(maPhieuDatPhong),
    FOREIGN KEY (maChiPhiPhatSinh) REFERENCES ChiPhiPhatSinh(maChiPhiPhatSinh)
);

CREATE TABLE HoaDon (
    maHoaDon VARCHAR(14) PRIMARY KEY,
    maPhieuDatPhong VARCHAR(14) NOT NULL UNIQUE,
    maKhuyenMai VARCHAR(14),
    phuongThucThanhToan NVARCHAR(15),
    FOREIGN KEY (maPhieuDatPhong) REFERENCES PhieuDatPhong(maPhieuDatPhong),
    FOREIGN KEY (maKhuyenMai) REFERENCES KhuyenMai(maKhuyenMai)
);
GO
