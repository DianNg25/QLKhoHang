-- Create Database
CREATE DATABASE QuanLyKhoHang;
GO
USE QuanLyKhoHang;
GO

-- Bảng Nhân Viên
CREATE TABLE Employees (
    EmployeeID CHAR(10) PRIMARY KEY,
    Username VARCHAR(50) NOT NULL, 
    FullName NVARCHAR(100) NOT NULL,
    Phone VARCHAR(15),
    Email VARCHAR(100),
    Password VARCHAR(255) NOT NULL,
    Position VARCHAR(50) NOT NULL DEFAULT 'User',
    Image VARCHAR(MAX)
);
GO

-- Bảng Người Dùng
CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY(1,1),
    EmployeeID CHAR(10),
    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);
GO

-- Bảng Nhà Cung Cấp
CREATE TABLE Suppliers (
    SupplierID CHAR(10) PRIMARY KEY,
    SupplierName VARCHAR(100) NOT NULL,
    Address VARCHAR(255),
    Phone VARCHAR(15),
    Email VARCHAR(100)
);
GO

-- Bảng Sản Phẩm
CREATE TABLE Products (
    ProductID CHAR(10) PRIMARY KEY,
    ProductName VARCHAR(100) NOT NULL,
    SupplierID CHAR(10),
    Weight VARCHAR(50),
    Color NVARCHAR(50),
    Quantity INT,
    Price DECIMAL(10, 2),
    Status NVARCHAR(255) NOT NULL,
    FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID)
);
GO

-- Bảng Phiếu Xuất
CREATE TABLE ExportForms (
    ExportFormID CHAR(10) PRIMARY KEY,
    EmployeeID CHAR(10),
    ExportDate DATE NOT NULL,
    TotalAmount DECIMAL(10, 2),
    CommissionFee DECIMAL(10, 2),
    ShippingFee DECIMAL(10, 2),
    Status NVARCHAR(255) NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);
GO

-- Bảng Chi Tiết Phiếu Xuất
CREATE TABLE ExportFormDetails (
    ExportFormDetailID CHAR(10) PRIMARY KEY,
    ExportFormID CHAR(10),
    ProductID CHAR(10),
    Quantity INT,
    Price DECIMAL(10, 2),
    FOREIGN KEY (ExportFormID) REFERENCES ExportForms(ExportFormID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
GO

-- Bảng Phiếu Nhập
CREATE TABLE ImportForms (
    ImportFormID CHAR(10) PRIMARY KEY,
    EmployeeID CHAR(10),
    ImportDate DATE NOT NULL,
    TotalAmount DECIMAL(10, 2),
    Status NVARCHAR(255) NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);
GO

-- Bảng Chi Tiết Phiếu Nhập
CREATE TABLE ImportFormDetails (
    ImportFormDetailID CHAR(10) PRIMARY KEY,
    ImportFormID CHAR(10),
    ProductID CHAR(10),
    Quantity INT,
    Price DECIMAL(10, 2),
    FOREIGN KEY (ImportFormID) REFERENCES ImportForms(ImportFormID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
GO

-- Bảng Báo Cáo
CREATE TABLE Reports (
    ReportID CHAR(10) PRIMARY KEY,
    EmployeeID CHAR(10),
    ReportType NVARCHAR(50) NOT NULL,
    GeneratedDate DATE NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);
GO

-- Bảng Kiểm Kho
CREATE TABLE InventoryChecks (
    InventoryCheckID CHAR(10) PRIMARY KEY,
    EmployeeID CHAR(10),
    CheckDate DATE NOT NULL,
    Notes NVARCHAR(255),
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);
GO

-- Bảng Chi Tiết Kiểm Kho
CREATE TABLE InventoryCheckDetails (
    InventoryCheckDetailID CHAR(10) PRIMARY KEY,
    InventoryCheckID CHAR(10),
    ProductID CHAR(10),
    Quantity INT,
    FOREIGN KEY (InventoryCheckID) REFERENCES InventoryChecks(InventoryCheckID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
GO

-- Stored Procedure for Revenue Statistics within a Date Range
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_ThongKeDoanhThuTuNgayDenNgay
    @StartDate DATE,
    @EndDate DATE
AS
BEGIN
    SELECT
        YEAR(ExportDate) AS Nam,
        SUM(TotalAmount) AS DoanhThu
    FROM ExportForms
    WHERE ExportDate BETWEEN @StartDate AND @EndDate
    GROUP BY YEAR(ExportDate)
END;
GO

-- Stored Procedure for Inventory Statistics
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE sp_ThongKeHangTonKho
AS
BEGIN
    SELECT
        p.ProductID,
        p.ProductName,
        p.Quantity,
        COALESCE(SUM(e.Quantity), 0) AS SoLuongDaXuat,
        COALESCE(SUM(i.Quantity), 0) AS SoLuongDaNhap,
        (p.Quantity + COALESCE(SUM(i.Quantity), 0) - COALESCE(SUM(e.Quantity), 0)) AS SoLuongTonKho
    FROM Products p
    LEFT JOIN ExportFormDetails e ON p.ProductID = e.ProductID
    LEFT JOIN ImportFormDetails i ON p.ProductID = i.ProductID
    GROUP BY p.ProductID, p.ProductName, p.Quantity
END;
GO
