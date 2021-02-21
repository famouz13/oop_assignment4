package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.entities.Employee;
import com.company.entities.Position;
import com.company.repositories.interfaces.IEmployeeRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


//data layer for our entity which implements CRUD operations
public class EmployeeRepository implements IEmployeeRepository {
    private final IDB db;

    public EmployeeRepository(IDB db) {
        this.db = db;
    }

    @Override
    //creating new entity
    public boolean createNewEmployee(Employee empl) {
        try (var connection = this.db.getConnection()) {
            //prepared sql statement for insert to avoid sql injection
            String sql = "INSERT INTO Employees(firstname,lastname,positionid) values(?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, empl.getFirstName());
            pst.setString(2, empl.getLastName());
            pst.setInt(3, empl.getPosition().ordinal() + 1);
            var res = pst.executeUpdate();
            return res == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editEmployee(Employee empl) {
        try (var connection = this.db.getConnection()) {

            String sql = "UPDATE Employees SET firstname = ?, lastname = ?, positionid = ? where employeeid = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, empl.getFirstName());
            pst.setString(2, empl.getLastName());
            pst.setInt(3, empl.getPosition().ordinal() + 1);
            pst.setInt(4, empl.getEmployeeId());
            return pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEmployee(int id) {
        try (var connection = this.db.getConnection()) {
            String sql = "DELETE FROM Employees WHERE employeeid = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            return pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Employee getEmployee(int id) {
        try (var connection = this.db.getConnection()) {
            var sql = "SELECT employeeid, firstname,lastname,positionid FROM Employees WHERE EmployeeID = ?";
            var pst = connection.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.next())
                return createEmployeeFromSingleResultSet(rs);

            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        try (var connection = this.db.getConnection()) {
            var resultSet = connection.prepareStatement("SELECT employeeid,firstname,lastname, Employees.positionid, salary from Employees\n" +
                    "JOIN Positions on Positions.positionid = employees.positionid").executeQuery();
            var employees = new ArrayList<Employee>();

            while (resultSet.next())
                employees.add(createEmployeeFromSingleResultSet(resultSet));

            return employees;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //this method created to avoid same code in getEmployee and getEmployees
    private Employee createEmployeeFromSingleResultSet(ResultSet rs) {
        try {
            var emplId = rs.getInt("employeeid");
            var firstName = rs.getString("firstname");
            var lastName = rs.getString("lastname");
            var position = Position.fromInteger(rs.getInt("positionid") - 1);
            var salary = rs.getFloat("salary");
            return new Employee(emplId, firstName, lastName, position, salary);
        } catch (Exception ex) {
            return null;
        }
    }
}
