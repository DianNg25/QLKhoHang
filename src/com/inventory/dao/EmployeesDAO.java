package com.inventory.dao;

import com.inventory.utils.XJdbc;
import com.inventory.entity.Employees;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDAO extends InvenDAO<Employees, String> {

    public void insert(Employees model) {
        String sql = "INSERT INTO Employees (EmployeeID, Name, Phone, Email, Position, Image, Password ) VALUES (?, ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql, 
                model.getEmployeeID(), 
                model.getName(), 
                model.getPhone(), 
                model.getEmail(), 
                model.getPosition(), 
                model.getImage(), 
                model.getPassword());
    }

    public void update(Employees model) {
        String sql = "UPDATE Employees SET  Name=?, Phone=?, Email=?, Position=?, Image=?, Password=?, WHERE EmployeeID=?";
        XJdbc.update(sql, 
             
                model.getName(), 
                model.getPhone(), 
                model.getEmail(),
                model.getPosition(),
                 model.getImage(), 
                model.getPassword(),
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
                    entity.setName(rs.getString("Name"));
                    entity.setPhone(rs.getInt("Phone"));
                    entity.setEmail(rs.getString("Email")); 
                    entity.setPosition(rs.getBoolean("Position")); 
                    entity.setImage(rs.getString("Image"));
                    entity.setPassword(rs.getString("Password"));
                   
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
