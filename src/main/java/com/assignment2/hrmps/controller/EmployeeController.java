package com.assignment2.hrmps.controller;

import com.assignment2.hrmps.model.Department;
import com.assignment2.hrmps.model.Employee;
import com.assignment2.hrmps.model.EmploymentType;
import com.assignment2.hrmps.repository.EmployeeRepository;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmployeeController {

    @FXML
    private TableView<Employee> employeeTable;
    @FXML private TableColumn<Employee, String> idColumn; //Initializing all columns
    @FXML private TableColumn<Employee, String> firstNameColumn;
    @FXML private TableColumn<Employee, String> lastNameColumn;
    @FXML private TableColumn<Employee, String> departmentColumn;
    @FXML private TableColumn<Employee, EmploymentType> employmentTypeColumn;
    @FXML private TextField idField; //Initializing all fields
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField departmentField;
    @FXML private ComboBox<EmploymentType> employmentTypeCombo;

    private EmployeeRepository employeeRepository;
    private ObservableList<Employee> employeeData;

    @FXML
    public void initialize() {

        // Load data and repo
        employeeRepository = new EmployeeRepository("data/employees.ser");
        employeeData = FXCollections.observableArrayList(employeeRepository.findAll());

        // Bindings
        idColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        firstNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getLastName()));
        employmentTypeColumn.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getEmploymentType()));
        // In case department is null
        departmentColumn.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getDepartment() != null
                                ? c.getValue().getDepartment().getName()
                                : ""
                )
        );

        // Adding data
        employmentTypeCombo.setItems(FXCollections.observableArrayList(EmploymentType.values()));
        employeeTable.setItems(employeeData);

        // Handling row selection
        employeeTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> showEmployeeDetails(newVal));
    }

    //If table tow is selected add data into the fields
    private void showEmployeeDetails(Employee e) {
        if (e != null) {
            idField.setText(e.getId());
            firstNameField.setText(e.getFirstName());
            lastNameField.setText(e.getLastName());
            departmentField.setText(e.getDepartment() != null ? e.getDepartment().getName() : "");
            employmentTypeCombo.setValue(e.getEmploymentType());
        } else {
            //If no row is selected clear fields
            idField.clear();
            firstNameField.clear();
            lastNameField.clear();
            departmentField.clear();
            employmentTypeCombo.setValue(null);
        }
    }

    @FXML
    private void handleAdd() { //Handles adding an employee
        if (idField.getText().isBlank() || employmentTypeCombo.getValue() == null)
            return;

        Employee e = new Employee(idField.getText(), firstNameField.getText(), lastNameField.getText());

        e.setDepartment(new Department(departmentField.getText(), departmentField.getText()));
        e.setEmploymentType(employmentTypeCombo.getValue());   // NEW

        employeeRepository.add(e);
        employeeData.add(e);
        employeeTable.refresh();
    }

    @FXML
    private void handleUpdate() { //Handles editing an employee
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        selected.setFirstName(firstNameField.getText());
        selected.setLastName(lastNameField.getText());
        selected.setDepartment(new Department(departmentField.getText(), departmentField.getText()));
        selected.setEmploymentType(employmentTypeCombo.getValue());   // NEW

        employeeRepository.update(selected);
        employeeTable.refresh();
    }

    @FXML
    private void handleDelete() { //Handles deleting an employee
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        employeeRepository.delete(selected.getId());
        employeeData.remove(selected);
    }
}
