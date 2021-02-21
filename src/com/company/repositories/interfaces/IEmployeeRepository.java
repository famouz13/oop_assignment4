package com.company.repositories.interfaces;

import com.company.entities.Employee;
import java.util.ArrayList;


public interface IEmployeeRepository {
    boolean createNewEmployee(Employee empl);
    boolean editEmployee(Employee empl);
    boolean deleteEmployee(int id);
    Employee getEmployee(int id);
    ArrayList<Employee> getAllEmployees();
}