package com.assignment2.hrmps.controller;

import com.assignment2.hrmps.model.AttendanceRecord;
import com.assignment2.hrmps.model.Employee;
import com.assignment2.hrmps.model.LeaveRecord;
import com.assignment2.hrmps.service.AttendanceService;
import com.assignment2.hrmps.service.EmployeeService;
import com.assignment2.hrmps.service.LeaveService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AttendanceController {

    // Attendance table
    @FXML
    private TableView<AttendanceRecord> attendanceTable;

    @FXML
    private TableColumn<AttendanceRecord, String> employeeColumn;

    @FXML
    private TableColumn<AttendanceRecord, String> statusColumn;

    @FXML
    private TableColumn<AttendanceRecord, java.time.LocalDate> dateColumn;

    @FXML
    private ComboBox<String> statusBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> employeeBox;

    // Leave table
    @FXML
    private TableView<LeaveRecord> leaveTable;

    @FXML
    private TableColumn<LeaveRecord, String> leaveEmployeeColumn;

    @FXML
    private TableColumn<LeaveRecord, String> leaveTypeColumn;

    @FXML
    private TableColumn<LeaveRecord, Object> leaveStartColumn;

    @FXML
    private TableColumn<LeaveRecord, Object> leaveEndColumn;

    @FXML
    private TableColumn<LeaveRecord, String> leaveStatusColumn;

    @FXML
    private ComboBox<String> leaveTypeBox;

    @FXML
    private ComboBox<String> leaveStatusBox;

    @FXML
    private DatePicker leaveStartPicker;

    @FXML
    private DatePicker leaveEndPicker;

    private AttendanceService attendanceService = new AttendanceService();
    private EmployeeService employeeService = new EmployeeService();
    private LeaveService leaveService = new LeaveService();

    @FXML
    public void initialize() {
        // Attendance table setup
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        statusBox.getItems().addAll("Present", "Absent", "Sick");

        // Fill employee dropdown from EmployeeService
        employeeBox.getItems().clear();
        for (Employee emp : employeeService.getAllEmployees()) {
            employeeBox.getItems().add(emp.toString());
        }

        // Load existing attendance
        attendanceTable.getItems().setAll(attendanceService.getAllRecords());

        // Leave table setup
        if (leaveEmployeeColumn != null) {
            leaveEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
            leaveTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            leaveStartColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            leaveEndColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            leaveStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

            leaveTable.getItems().setAll(leaveService.getAllRecords());
        }

        if (leaveTypeBox != null) {
            leaveTypeBox.getItems().addAll("Vacation", "Sick", "Personal");
        }

        if (leaveStatusBox != null) {
            leaveStatusBox.getItems().addAll("Pending", "Approved", "Rejected");
        }
    }

    @FXML
    private void addAttendance() {
        String emp = employeeBox.getValue();
        String status = statusBox.getValue();
        var date = datePicker.getValue();

        if (emp == null || status == null || date == null) {
            return;
        }

        AttendanceRecord rec = new AttendanceRecord(emp, date, status);
        attendanceService.addRecord(rec);
        attendanceTable.getItems().add(rec);
    }

    @FXML
    private void addLeave() {
        String emp = employeeBox.getValue();
        String type = leaveTypeBox.getValue();
        String status = leaveStatusBox.getValue();
        var start = leaveStartPicker.getValue();
        var end = leaveEndPicker.getValue();

        if (emp == null || type == null || status == null || start == null || end == null) {
            return;
        }

        LeaveRecord record = new LeaveRecord(emp, start, end, type, status);
        leaveService.addRecord(record);
        leaveTable.getItems().add(record);
    }
}