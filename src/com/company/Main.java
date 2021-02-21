package com.company;

import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.entities.Employee;
import com.company.repositories.EmployeeRepository;
import com.company.views.EmployeeConsoleView;
import com.company.views.interfaces.IEmployeeView;


import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        //employee view
        var consoleView = new EmployeeConsoleView();
        //menu
        String menu = " 1.Add new employee \n 2.Delete employee by ID \n 3.Edit employee by ID \n 4.Get employee by ID \n 5.Get all employees \n 6.Create command for project \n7.Exit";

        //program loop
        while (true) {
            try {
                System.out.println(menu);
                int operation = Integer.parseInt(scanner.nextLine());
                switch (operation) {
                    case 1:
                        consoleView.showCreateOption();
                        break;
                    case 2:
                        consoleView.showDeleteOption();
                        break;
                    case 3:
                        consoleView.showEditOption();
                        break;
                    case 4:
                        consoleView.showEmployee();
                        break;
                    case 5:
                        consoleView.showAllEmployees();
                        break;
                    case 6:
                        CreateCommand();
                        break;
                    case 7:
                        System.exit(0);
                    default:
                        System.out.println("Unsupported option");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void CreateCommand() {
        var employees = new EmployeeRepository(new PostgresDB()).getAllEmployees();
        var team = new ArrayList<Employee>();
        var scanner = new Scanner(System.in);
        String createCommandMenu = "1.Show available employees \n2. Add employee to the project \n3.Show project team and project cost \n4.Exit";
        while (true) {
            try {
                System.out.println(createCommandMenu);
                var option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        PrintEmployees(employees);
                        break;
                    case 2:
                        System.out.println("Id of employee: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        //java 8 stream api kinda c# linq
                        var empl = employees.stream().filter(n -> n.getEmployeeId() == id).findAny().orElse(null);
                        team.add(empl);
                        employees.removeIf(n -> n.getEmployeeId() == id);
                        break;
                    case 3:
                        PrintEmployees(team);
                        System.out.println("Cost of project: " + team.stream().mapToDouble(n -> n.getSalary()).sum());
                        break;
                }
                if (option == 4)
                    break;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void PrintEmployees(ArrayList<Employee> empls) {
        for (var empl : empls)
            System.out.println(empl);
    }
}