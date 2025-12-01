package com.assignment2.hrmps.repository;

import com.assignment2.hrmps.model.Employee;
import com.assignment2.hrmps.utilities.Serialization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {

    private final String filePath;
    private List<Employee> employees = new ArrayList<>();
    //Constructor
    public EmployeeRepository(String filePath) {
        this.filePath = filePath;
        load();
    }

    //loading of employees
    private void load() {
        try {
            employees = Serialization.loadList(filePath);
        } catch (IOException | ClassNotFoundException e) {
            employees = new ArrayList<>();
        }
    }

    //saving of employees
    public void save() {
        try {
            Serialization.saveList(employees, filePath);
        } catch (IOException e) {
            e.printStackTrace(); // later: better error handling
        }
    }

    //Finds and reutrns all of employees
    public List<Employee> findAll() {
        return employees;
    }

    //Adds a new employee
    public void add(Employee e) {
        employees.add(e);
        save();
    }

    //Updates an existing employee
    public void update(Employee updated) {
        Optional<Employee> existingOpt = employees.stream()
                .filter(e -> e.getId().equals(updated.getId()))
                .findFirst();
        existingOpt.ifPresent(existing -> {
            employees.remove(existing);
            employees.add(updated);
            save();
        });
    }
    //Deletes an employee
    public void delete(String id) {
        employees.removeIf(e -> e.getId().equals(id));
        save();
    }
}
