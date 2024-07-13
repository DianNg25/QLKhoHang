/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.dao;

import com.inventory.utils.XJdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author Nguyen
 */
public class StatsDAO {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public List<Object[]> getRevenueStatistics() {
//        String sql = "{CALL sp_ThongKeDoanhThu}";
//        String[] cols = {"Nam", "DoanhThu"};
//        return this.getListOfArray(sql, cols);
//    }
    public List<Object[]> getDoanhThu(Date tuNgay, Date denNgay) {
        String sql = "{CALL sp_ThongKeDoanhThuTuNgayDenNgay(?,?)}";
        String[] cols = {"Nam", "DoanhThu"};
        return this.getListOfArray(sql, cols, tuNgay, denNgay);
    }

    public List<Object[]> getInventoryStatistics() {
        String sql = "{CALL sp_ThongKeHangTonKho}";
        String[] cols = {"ProductID", "ProductName", "Quantity", "SoLuongDaXuat", "SoLuongDaNhap", "SoLuongTonKho"};
        return this.getListOfArray(sql, cols);
    }
}
