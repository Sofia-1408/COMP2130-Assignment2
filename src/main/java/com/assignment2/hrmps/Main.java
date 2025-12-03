package com.assignment2.hrmps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.assignment2.hrmps.authentication.AuthService;
import com.assignment2.hrmps.ui.LoginScreen;
import com.assignment2.hrmps.authentication.User;
import com.assignment2.hrmps.controller.MainController;

public class Main extends Application {

    private Stage Stage;
    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Stage = stage;

        AuthService authService = new AuthService();
        LoginScreen loginScreen = new LoginScreen(authService, this);

        loginScreen.show(Stage);
    }

    public void showMainView() {
        try { //Everything in try/catch in case of an error
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/assignment2/hrmps/view/main-view.fxml")); //Calling the main view
            Scene scene = new Scene(loader.load());

            // Get controller and pass current user
            MainController controller = loader.getController();
            controller.setCurrentUser(currentUser);

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
