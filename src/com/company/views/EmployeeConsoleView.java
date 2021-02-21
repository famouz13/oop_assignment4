package com.company.views;

import com.company.controllers.EmployeeController;
import com.company.data.PostgresDB;
import com.company.entities.Employee;
import com.company.entities.Position;
import com.company.repositories.EmployeeRepository;
import com.company.repositories.interfaces.IEmployeeRepository;
import com.company.views.interfaces.IEmployeeView;

import java.util.Scanner;

//view for displaying available operations
public class EmployeeConsoleView implements IEmployeeView {
    private EmployeeController _emplController;
    private Scanner scanner = new Scanner(System.in);

    public EmployeeConsoleView() {
        var _db = new PostgresDB();
        var _repo = new EmployeeRepository(_db);
        this._emplController = new EmployeeController(_repo);
    }

    @Override
    public void showCreateOption() {
        try {
            System.out.println("-------CREATE NEW EMPLOYEE----------");

            System.out.println("Firstname: ");
            String firstName = this.scanner.nextLine();
            System.out.println("Lastname: ");
            String lastName = this.scanner.nextLine();
            System.out.println("Position: \n1.Developer  \n2.TeamLead \n3.Project Manager \n4.Tester \n5.Architect \n6.Engineer");
            Position pos = Position.fromInteger(Integer.parseInt(this.scanner.nextLine()) - 1);
            System.out.println(pos);
            System.out.println(this._emplController.createEmployee(firstName, lastName, pos));

            System.out.println("------------------------------------");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void showDeleteOption() {
        try {
            System.out.println("---------------DELETE---------------");

            System.out.println("Enter employee id to delete:");
            int id = Integer.parseInt(this.scanner.nextLine());

            System.out.println(this._emplController.deleteEmployee(id));

            System.out.println("------------------------------------");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void showEditOption() {
        try {
            System.out.println("---------------EDIT---------------");

            System.out.println("ID of employee");
            int id = Integer.parseInt(this.scanner.nextLine());
            System.out.println("New Firstname: ");
            String firstName = this.scanner.nextLine();
            System.out.println("New Lastname: ");
            String lastName = this.scanner.nextLine();
            System.out.println("New Position: \n1.Developer  \n2.TeamLead \n3.Project Manager \n4.Tester \n5.Architect \n6. Engineer");
            Position pos = Position.fromInteger(Integer.parseInt(this.scanner.nextLine()) - 1);

            System.out.println(this._emplController.editEmployee(id, firstName, lastName, pos));

            System.out.println("----------------------------------");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void showEmployee() {
        try {
            System.out.println("---------------Employee---------------");
            System.out.println("Enter employee id");
            int id = Integer.parseInt(this.scanner.nextLine());

            var empl = this._emplController.getEmployee(id);
            if (empl == null)
                System.out.println("Employee is not exists");
            else
                System.out.println(empl);

            System.out.println("--------------------------------------");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void showAllEmployees() {
        try {
            System.out.println("---------------Employees---------------");

            for (Employee empl : this._emplController.getAllEmployees())
                System.out.println(empl);

            System.out.println("---------------------------------------");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
