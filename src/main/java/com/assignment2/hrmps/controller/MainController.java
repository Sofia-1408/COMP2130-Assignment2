package com.assignment2.hrmps.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import com.assignment2.hrmps.authentication.User;
import com.assignment2.hrmps.authentication.Role;
import javafx.scene.control.Button;

public class MainController {
    @FXML
    private BorderPane contentPane;

    @FXML
    private Button payrollButton;

    @FXML
    private Button reportsButton;

    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
        applyRoleRestrictions();
    }

    @FXML
    public void initialize() {
        showEmployees(); // default view once opened
    }

    @FXML
    private void showEmployees() {
        loadCenter("/com/assignment2/hrmps/view/employee-view.fxml");
    }

    @FXML
    private void showPayroll() {
        loadCenter("/com/assignment2/hrmps/view/payroll-view.fxml");
    }

    @FXML
    private void showReports() {
        loadCenter("/com/assignment2/hrmps/view/report-view.fxml");
    }

    @FXML
    private void showAttendance() {
        loadCenter("/com/assignment2/hrmps/view/attendance-view.fxml");
    }

    @FXML
    private void showAnalytics() {
        loadCenter("/com/assignment2/hrmps/view/analytics-view.fxml");
    }

    //This method allows for loading of the views and it is called whenever we try to load the views
    private void loadCenter(String path){
        try { //Everything in try catch in case of an error
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            contentPane.setCenter(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void applyRoleRestrictions() {
        if (currentUser != null && currentUser.getRole() == Role.EMPLOYEE) {
            if (payrollButton != null) payrollButton.setDisable(true);
            if (reportsButton != null) reportsButton.setDisable(true);
        }
    }
}
