package com.company.controllers;

import com.company.entities.Employee;
import com.company.entities.Position;
import com.company.repositories.interfaces.IEmployeeRepository;


import java.util.ArrayList;


//controller for employee entity is a controlling middleware between data layer and view
public class EmployeeController {

    private final IEmployeeRepository repository;
    //ctor with repository argument
    public EmployeeController(IEmployeeRepository repository) {
        this.repository = repository;
    }
    //controller with access to crud options
    public String createEmployee(String firstName, String lastName, Position position) {
        return repository.createNewEmployee(new Employee(firstName, lastName, position)) ? "Added successfully" : "Something went wrong...";
    }

    public String deleteEmployee(int id) {
        return repository.deleteEmployee(id) ? "Deleted successfully" : "Something went wrong...";
    }

    public String editEmployee(int id, String firstName, String lastName, Position position) {
        return repository.editEmployee(new Employee(id, firstName, lastName, position)) ? "Edited successfully" : "Something went wrong...";
    }

    public ArrayList<Employee> getAllEmployees() {
        return repository.getAllEmployees();
    }

    public Employee getEmployee(int id) {
        return repository.getEmployee(id);
    }
}