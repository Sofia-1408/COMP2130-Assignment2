package com.assignment2.hrmps.repository;

import com.assignment2.hrmps.model.PayrollRecord;
import com.assignment2.hrmps.utilities.Serialization;

import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PayrollRepository {

    private final String filePath;
    private List<PayrollRecord> records = new ArrayList<>();

    public PayrollRepository(String filePath) { //constructor
        this.filePath = filePath;
        load();
    }

    private void load() { //Loads the list of payroll records
        try {
            records = Serialization.loadList(filePath);
        } catch (IOException | ClassNotFoundException e) {
            records = new ArrayList<>();
        }
    }

    private void save() { //Saves the current list of payroll recrods
        try {
            Serialization.saveList(records, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(PayrollRecord record) { //Adds a new payroll record
        records.add(record);
        save();
    }

    public List<PayrollRecord> findAll() {
        return records;
    } //returns all of the payroll records

    public List<PayrollRecord> findByEmployee(String employeeId) {
        return records.stream()
                .filter(r -> r.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }

    public List<PayrollRecord> findByPeriod(YearMonth period) { //returns all payroll record for a specific month
        return records.stream()
                .filter(r -> period.equals(r.getPeriod()))
                .collect(Collectors.toList());
    }

    public List<PayrollRecord> findByEmployeeAndPeriod(String employeeId, YearMonth period) { //Returns payroll record matching an employee and time
        return records.stream()
                .filter(r -> r.getEmployeeId().equals(employeeId))
                .filter(r -> period.equals(r.getPeriod()))
                .collect(Collectors.toList());
    }
}