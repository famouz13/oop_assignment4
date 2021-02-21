package com.company.entities;

//employee class
public class Employee {
    private int employeeId;
    private String FirstName;
    private String LastName;
    private Position position;
    private double salary;

    //parametrized ctor for operations like creating new employees
    public Employee(String firstName, String lastName, Position position) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPosition(position);
    }

    // parametrized ctor for retrieving employees from database
    public Employee(int id, String firstName, String lastName, Position position) {
        this(firstName, lastName, position);
        this.employeeId = id;
    }

    public Employee(int id, String firstName, String lastName, Position position, float salary) {
        this(id, firstName, lastName, position);
        this.salary = salary;
    }

    //standard getters and setters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("%d |%s %s | %s | %f", getEmployeeId(), getFirstName(), getLastName(), getPosition(), getSalary());
    }

    public double getSalary() {
        return salary;
    }
}
