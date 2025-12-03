package com.assignment2.hrmps.service;

import com.assignment2.hrmps.model.Employee;

import java.io.*;
import java.util.ArrayList;

public class EmployeeService {

    private ArrayList<Employee> employees;
    private final String FILE_NAME = "data/employees.ser";

    public EmployeeService() {
        employees = loadEmployees();
    }

    public ArrayList<Employee> getAllEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        saveEmployees();
    }

    private ArrayList<Employee> loadEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Employee>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void saveEmployees() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Error saving employees.");
        }
    }
}
