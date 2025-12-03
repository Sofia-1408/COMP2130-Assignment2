package com.assignment2.hrmps.ui;

import com.assignment2.hrmps.Main;
import com.assignment2.hrmps.authentication.AuthService;
import com.assignment2.hrmps.authentication.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen {

    private AuthService authService;
    private Main mainApp;

    public LoginScreen(AuthService authService, Main mainApp) {
        this.authService = authService;
        this.mainApp = mainApp;
    }

    public void show(Stage stage) {
        Label titleLabel = new Label("HRMPS Login");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Label messageLabel = new Label();

        loginButton.setOnAction(event -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Please enter username and password.");
                return;
            }

            boolean success = authService.login(username, password);

            if (success) {
                User loggedIn = authService.findUser(username);
                mainApp.setCurrentUser(loggedIn);
                mainApp.showMainView();
            } else {
                messageLabel.setText("Invalid username or password.");
            }
        });

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
                titleLabel,
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                loginButton,
                messageLabel
        );

        Scene scene = new Scene(root, 320, 260);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}
