package com.assignment2.hrmps.controller;

import com.assignment2.hrmps.model.Employee;
import com.assignment2.hrmps.model.PayrollRecord;
import com.assignment2.hrmps.repository.EmployeeRepository;
import com.assignment2.hrmps.repository.PayrollRepository;
import com.assignment2.hrmps.service.PayrollService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.YearMonth;

public class PayrollController {


    @FXML
    private ComboBox<Employee> employeeComboBox;
    @FXML
    private TextField periodField; //Adding all the fields
    @FXML
    private TextField hoursWorkedField;
    @FXML
    private TextField overtimeHoursField;
    @FXML
    private TextField bonusesField;
    @FXML
    private TextField deductionsField;

    @FXML
    private TableView<PayrollRecord> payrollTable;
    @FXML
    private TableColumn<PayrollRecord, String> empIdColumn; //Adding all the columns
    @FXML
    private TableColumn<PayrollRecord, String> periodColumn;
    @FXML
    private TableColumn<PayrollRecord, Number> grossColumn;
    @FXML
    private TableColumn<PayrollRecord, Number> taxColumn;
    @FXML
    private TableColumn<PayrollRecord, Number> netColumn;

    @FXML
    private Label statusLabel; //For error showing

    private PayrollRepository payrollRepository;
    private EmployeeRepository employeeRepository;
    private PayrollService payrollService;

    //Needed lists
    private ObservableList<Employee> employeeData;
    private ObservableList<PayrollRecord> payrollData;

    @FXML
    public void initialize() {
        // Load data and repo
        employeeRepository = new EmployeeRepository("data/employees.ser");
        payrollRepository = new PayrollRepository("data/payroll.ser");
        payrollService = new PayrollService(payrollRepository);
        employeeData = FXCollections.observableArrayList(employeeRepository.findAll());
        employeeComboBox.setItems(employeeData);
        payrollData = FXCollections.observableArrayList(payrollRepository.findAll());
        payrollTable.setItems(payrollData);

        //Bindings
        empIdColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEmployeeId()));
        periodColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getPeriod() != null ?
                                cellData.getValue().getPeriod().toString() : ""));
        grossColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getGrossPay()));
        taxColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getTaxAmount()));
        netColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getNetPay()));
    }

    //Called when user clicks calculate payroll
    @FXML
    private void handleCalculatePayroll() {
        //Makes sure employee is selected
        Employee employee = employeeComboBox.getValue();
        if (employee == null) {
            statusLabel.setText("Please select an employee");
            return;
        }

        //Must be YYYY-MM format
        YearMonth period;
        try {
            period = YearMonth.parse(periodField.getText().trim());
        } catch (Exception e) {
            statusLabel.setText("Invalid time. Use format YYYY-MM");
            return;
        }
        //Parse numeric fields, will be 0 if left empty
        double hours = parseDoubleOrZero(hoursWorkedField.getText());
        double overtime = parseDoubleOrZero(overtimeHoursField.getText());
        double bonuses = parseDoubleOrZero(bonusesField.getText());
        double deductions = parseDoubleOrZero(deductionsField.getText());

        PayrollRecord record = payrollService.calculatePayroll( //Math and creating payrollRecord
                employee, period, hours, overtime, bonuses, deductions
        );

        //Adding the record into the observable list
        payrollData.add(record);
        payrollTable.refresh();
        statusLabel.setText("Payroll calculated and saved.");
    }
    //Method for doubles or zeros
    private double parseDoubleOrZero(String text) {
        try {
            if (text == null || text.isBlank()) return 0.0;
            return Double.parseDouble(text.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}