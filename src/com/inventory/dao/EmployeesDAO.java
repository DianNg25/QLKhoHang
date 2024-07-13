package com.inventory.dao;

import com.inventory.utils.XJdbc;
import com.inventory.entity.Employees;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDAO extends InvenDAO<Employees, String> {
    
    public void insert(Employees model) {
        String sql = "INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?, ?)";
//        XJdbc.update(sql,
//                model.getMaNV(),
//                model.getMatKhau(),
//                model.getHoTen(),
//                model.getVaiTro());
    }

    public void update(Employees model) {
        String sql = "UPDATE NhanVien SET MatKhau=?, HoTen=?, VaiTro=? WHERE MaNV=?";
//        XJdbc.update(sql,
//                model.getMatKhau(),
//                model.getHoTen(),
//                model.getVaiTro(),
//                model.getMaNV());
    }

    public void delete(String MaNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        XJdbc.update(sql, MaNV);
    }

    public List<Employees> selectAll() {
        String sql = "SELECT * FROM NhanVien";
        return this.selectBySql(sql);
    }

    public Employees selectById(String manv) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<Employees> list = this.selectBySql(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<Employees> selectBySql(String sql, Object... args) {
        List<Employees> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
//                while (rs.next()) {
//                    Employees entity = new Employees();
//                    entity.setMaNV(rs.getString("MaNV"));
//                    entity.setMatKhau(rs.getString("MatKhau"));
//                    entity.setHoTen(rs.getString("HoTen"));
//                    entity.setVaiTro(rs.getBoolean("VaiTro"));
//                    list.add(entity);
//                }
            } finally {
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
}
