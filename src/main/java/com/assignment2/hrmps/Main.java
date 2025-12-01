package com.assignment2.hrmps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage Stage;

    @Override
    public void start(Stage stage) throws Exception {
        Stage = stage;
        showMainView();
    }

    public void showMainView() {
        try { //Everything in try/catch in case of an error
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/assignment2/hrmps/view/main-view.fxml")); //Calling the main view
            Scene scene = new Scene(loader.load());
            // Adding the title to the scene
            Stage.setTitle("Human Resources Management Payroll System");
            Stage.setScene(scene);
            Stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

