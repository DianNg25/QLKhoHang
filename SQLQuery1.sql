CREATE DATABASE QuanLyKhoHang;
USE QuanLyKhoHang;

-- Bảng Người Dùng
CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY(1,1),
    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Role VARCHAR(50) NOT NULL DEFAULT 'User'
);

-- Bảng Nhân Viên
CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY IDENTITY(1,1),
    Name VARCHAR(100) NOT NULL,
    Phone VARCHAR(15),
    Email VARCHAR(100),
    Position VARCHAR(50),
    Image VARCHAR(MAX)
);

-- Bảng Nhà Cung Cấp
CREATE TABLE Suppliers (
    SupplierID INT PRIMARY KEY IDENTITY(1,1),
    SupplierName VARCHAR(100) NOT NULL,
    Address VARCHAR(255),
    Phone VARCHAR(15),
    Email VARCHAR(100)
);

-- Bảng Sản Phẩm
CREATE TABLE Products (
    ProductID INT PRIMARY KEY IDENTITY(1,1),
    ProductName VARCHAR(100) NOT NULL,
    SupplierID INT,
    Type VARCHAR(50),
    Quantity INT,
    Price DECIMAL(10, 2),
    FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID)
);

-- Bảng Phiếu Xuất
CREATE TABLE ExportForms (
    ExportFormID INT PRIMARY KEY IDENTITY(1,1),
    EmployeeID INT,
    ExportDate DATE NOT NULL,
    TotalAmount DECIMAL(10, 2),
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);

-- Bảng Chi Tiết Phiếu Xuất
CREATE TABLE ExportFormDetails (
    ExportFormDetailID INT PRIMARY KEY IDENTITY(1,1),
    ExportFormID INT,
    ProductID INT,
    Quantity INT,
    Price DECIMAL(10, 2),
    FOREIGN KEY (ExportFormID) REFERENCES ExportForms(ExportFormID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Bảng Phiếu Nhập
CREATE TABLE ImportForms (
    ImportFormID INT PRIMARY KEY IDENTITY(1,1),
    EmployeeID INT,
    ImportDate DATE NOT NULL,
    TotalAmount DECIMAL(10, 2),
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);

-- Bảng Chi Tiết Phiếu Nhập
CREATE TABLE ImportFormDetails (
    ImportFormDetailID INT PRIMARY KEY IDENTITY(1,1),
    ImportFormID INT,
    ProductID INT,
    Quantity INT,
    Price DECIMAL(10, 2),
    FOREIGN KEY (ImportFormID) REFERENCES ImportForms(ImportFormID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Bảng Phiếu Hủy Hàng
CREATE TABLE DestroyForms (
    DestroyFormID INT PRIMARY KEY IDENTITY(1,1),
    EmployeeID INT,
    DestroyDate DATE NOT NULL,
    TotalAmount DECIMAL(10, 2),
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);

-- Bảng Chi Tiết Phiếu Hủy Hàng
CREATE TABLE DestroyFormDetails (
    DestroyFormDetailID INT PRIMARY KEY IDENTITY(1,1),
    DestroyFormID INT,
    ProductID INT,
    Quantity INT,
    Reason VARCHAR(255),
    FOREIGN KEY (DestroyFormID) REFERENCES DestroyForms(DestroyFormID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Bảng Báo Cáo
CREATE TABLE Reports (
    ReportID INT PRIMARY KEY IDENTITY(1,1),
    EmployeeID INT,
    ReportType VARCHAR(50) NOT NULL,
    GeneratedDate DATE NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID),
    CONSTRAINT chk_report_type CHECK (ReportType IN ('Nhập', 'Xuất', 'Hàng hủy'))
);

-- Bảng Kiểm Kho
CREATE TABLE InventoryChecks (
    InventoryCheckID INT PRIMARY KEY IDENTITY(1,1),
    EmployeeID INT,
    CheckDate DATE NOT NULL,
    Notes VARCHAR(255),
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);

-- Bảng Chi Tiết Kiểm Kho
CREATE TABLE InventoryCheckDetails (
    InventoryCheckDetailID INT PRIMARY KEY IDENTITY(1,1),
    InventoryCheckID INT,
    ProductID INT,
    Quantity INT,
    FOREIGN KEY (InventoryCheckID) REFERENCES InventoryChecks(InventoryCheckID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
