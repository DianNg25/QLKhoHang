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
    String sql = "INSERT INTO Employees (EmployeeID, Username, FullName, Phone, Email, Password, Position, Image, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = XJdbc.prepareStatement(sql)) {
        stmt.setString(1, model.getEmployeeID());
        stmt.setString(2, model.getUsername());
        stmt.setString(3, model.getFullName());
        stmt.setInt(4, model.getPhone());
        stmt.setString(5, model.getEmail());
        stmt.setString(6, model.getPassword());
        stmt.setByte(7, model.getPosition());
        stmt.setString(8, model.getImage());
        stmt.setString(9, model.getStatus()); // Thêm trạng thái
        stmt.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

public void update(Employees model) {
    String sql = "UPDATE Employees SET Username=?, FullName=?, Phone=?, Email=?, Password=?, Position=?, Image=?, Status=? WHERE EmployeeID=?";
    try (PreparedStatement stmt = XJdbc.prepareStatement(sql)) {
        stmt.setString(1, model.getUsername());
        stmt.setString(2, model.getFullName());
        stmt.setInt(3, model.getPhone());
        stmt.setString(4, model.getEmail());
        stmt.setString(5, model.getPassword());
        stmt.setByte(6, model.getPosition());
        stmt.setString(7, model.getImage());
        stmt.setString(8, model.getStatus()); // Cập nhật trạng thái
        stmt.setString(9, model.getEmployeeID());
        stmt.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

public void delete(String EmployeeID) {
    String sql = "DELETE FROM Employees WHERE EmployeeID=?";
    try (PreparedStatement stmt = XJdbc.prepareStatement(sql)) {
        stmt.setString(1, EmployeeID);
        stmt.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
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
            entity.setStatus(rs.getString("Status")); // Lấy trạng thái từ kết quả truy vấn

            list.add(entity);
        }
    } catch (SQLException ex) {
        Logger.getLogger(EmployeesDAO.class.getName()).log(Level.SEVERE, null, ex);
        throw new RuntimeException(ex);
    }
    return list;
}
public void updateStatus(String emId, String newStatus) {
        String sql = "UPDATE Employees SET Status =? WHERE EmployeeID=?";
        XJdbc.update(sql,
                newStatus,
                emId);
    }
}
