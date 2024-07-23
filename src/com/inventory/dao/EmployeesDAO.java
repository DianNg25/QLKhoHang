package com.inventory.dao;

import com.inventory.utils.XJdbc;
import com.inventory.entity.Employees;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDAO extends InvenDAO<Employees, String> {

    public void insert(Employees model) {
        String sql = "INSERT INTO Employees (EmployeeID, Username, FullName, Phone, Email, Password, Position, Image ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getEmployeeID(),
                model.getUsername(),
                model.getFullName(),
                model.getPhone(),
                model.getEmail(),
                model.getPassword(),
                model.getPosition(),
                model.getImage());

    }

    public void update(Employees model) {
        String sql = "UPDATE Employees SET  Username=?, FullName=?, Phone=?, Email=?, Password=?, Position=?, Image=?, WHERE EmployeeID=?";
        XJdbc.update(sql,
                model.getUsername(),
                model.getFullName(),
                model.getPhone(),
                model.getEmail(),
                model.getPassword(),
                model.getPosition(),
                model.getImage(),
                model.getEmployeeID());
    }

    public void delete(String EmployeeID) {
        String sql = "DELETE FROM Employees WHERE EmployeeID=?";
        XJdbc.update(sql, EmployeeID);
    }

    public List<Employees> selectAll() {
        String sql = "SELECT * FROM Employees";
        return this.selectBySql(sql);
    }

    public Employees selectById(String EmployeeID) {
        String sql = "SELECT * FROM Employees WHERE EmployeeID=?";
        List<Employees> list = this.selectBySql(sql, EmployeeID);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<Employees> selectBySql(String sql, Object... args) {
        List<Employees> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    Employees entity = new Employees();
                    entity.setEmployeeID(rs.getString("EmployeeID"));
                    entity.setUsername(rs.getString("Name"));
                    entity.setFullName(rs.getString("FullName"));
                    entity.setPhone(rs.getInt("Phone"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setPassword(rs.getString("Password"));
                    entity.setPosition(rs.getString("Position"));
                    entity.setImage(rs.getString("Image"));

                    list.add(entity);
                }
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
