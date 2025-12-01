package com.assignment2.hrmps.controller;

import com.assignment2.hrmps.model.Employee;
import com.assignment2.hrmps.model.PayrollRecord;
import com.assignment2.hrmps.repository.EmployeeRepository;
import com.assignment2.hrmps.repository.PayrollRepository;
import com.assignment2.hrmps.service.ReportService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.YearMonth;

public class ReportController {
    @FXML
    private ComboBox<Employee> employeeComboBox;
    @FXML //Adding all the fields
    private TextField departmentIdField;
    @FXML
    private TextField fromPeriodField;
    @FXML
    private TextField toPeriodField;
    @FXML
    private Label totalEmployeeLabel;
    @FXML
    private Label totalDepartmentLabel;

    @FXML
    private TableView<PayrollRecord> payrollTable;
    @FXML //Adding all the columns
    private TableColumn<PayrollRecord, String> periodColumn;
    @FXML
    private TableColumn<PayrollRecord, Number> grossColumn;
    @FXML
    private TableColumn<PayrollRecord, Number> netColumn;

    private ReportService reportService;
    private EmployeeRepository employeeRepository;
    private PayrollRepository payrollRepository;
    //Adding needed lists
    private ObservableList<Employee> employeeData;
    private ObservableList<PayrollRecord> payrollData;

    @FXML
    public void initialize() {
        // Load data and repo
        employeeRepository = new EmployeeRepository("data/employees.ser");
        payrollRepository = new PayrollRepository("data/payroll.ser");
        reportService = new ReportService(payrollRepository, employeeRepository);
        employeeData = FXCollections.observableArrayList(employeeRepository.findAll());
        employeeComboBox.setItems(employeeData);
        payrollData = FXCollections.observableArrayList();
        payrollTable.setItems(payrollData);
        //Bindings
        periodColumn.setCellValueFactory(cd ->
                new javafx.beans.property.SimpleStringProperty(
                        cd.getValue().getPeriod() != null ? cd.getValue().getPeriod().toString() : ""));
        grossColumn.setCellValueFactory(cd ->
                new javafx.beans.property.SimpleDoubleProperty(cd.getValue().getGrossPay()));
        netColumn.setCellValueFactory(cd ->
                new javafx.beans.property.SimpleDoubleProperty(cd.getValue().getNetPay()));
    }

    @FXML
    private void handleLoadEmployeeReport() { //Loads the report in case of an employee
        Employee e = employeeComboBox.getValue();
        if (e == null) { //In case nothing is selected
            return;
        }

        payrollData.setAll(reportService.getPayrollForEmployee(e.getId()));
        double total = reportService.getTotalPaidToEmployee(e.getId());
        totalEmployeeLabel.setText(String.format("Total paid to %s: %.2f",
                e.getFirstName(), total));
    }

    @FXML
    private void handleLoadDepartmentReport() { //Loads the report in case of a department
        String deptId = departmentIdField.getText();
        if (deptId == null || deptId.isBlank()) {
            return; //In case nothing is selected
        }

        String fromStr = fromPeriodField.getText();
        String toStr = toPeriodField.getText();

        if (fromStr == null || fromStr.isBlank() || toStr == null || toStr.isBlank()) {
            double total = reportService.getTotalPaidForDepartment(deptId);
            totalDepartmentLabel.setText(
                    String.format("Total paid for department %s: %.2f", deptId, total));
        } else {
            try {
                YearMonth from = YearMonth.parse(fromStr.trim());
                YearMonth to = YearMonth.parse(toStr.trim());
                double total = reportService.getTotalPaidForDepartmentInPeriod(deptId, from, to);
                totalDepartmentLabel.setText(
                        String.format("Total paid for department %s (%s to %s): %.2f",
                                deptId, from, to, total));
            } catch (Exception e) {
                totalDepartmentLabel.setText("Invalid date range (use YYYY-MM).");
            }
        }
    }
}
