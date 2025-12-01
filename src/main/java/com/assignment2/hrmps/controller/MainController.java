package com.assignment2.hrmps.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class MainController {
    @FXML
    private BorderPane contentPane;

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

    //This method allows for loading of the views and it is called whenever we try to load the views
    private void loadCenter(String path){
        try { //Everything in try catch in case of an error
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            contentPane.setCenter(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
