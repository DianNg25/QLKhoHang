package com.inventory.dao;

import com.inventory.utils.XJdbc;
import com.inventory.entity.Employees;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class EmployeesDAO extends InvenDAO<Employees, String> {


    public void insert(Employees model) {
        String sql = "INSERT INTO Employees (EmployeeID, Username, FullName, Phone, Email, Password, Position, Image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = XJdbc.prepareStatement(sql)) {
            stmt.setString(1, model.getEmployeeID());
            stmt.setString(2, model.getUsername());
            stmt.setString(3, model.getFullName());
            stmt.setInt(4, model.getPhone());
            stmt.setString(5, model.getEmail());
            stmt.setString(6, model.getPassword());
            stmt.setByte(7, model.getPosition());
            stmt.setString(8, model.getImage());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                Employees entity = new Employees();
                entity.setEmployeeID(rs.getString("EmployeeID"));
                entity.setUsername(rs.getString("Username"));
                entity.setFullName(rs.getString("FullName"));
                entity.setPhone(rs.getInt("Phone")); // Giả sử Phone là kiểu int trong CSDL
                entity.setEmail(rs.getString("Email"));
                entity.setPassword(rs.getString("Password"));
                entity.setPosition(rs.getByte("Position"));
                entity.setImage(rs.getString("Image"));

                list.add(entity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
        return list;
    }
}
