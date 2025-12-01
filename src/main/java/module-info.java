module com.assignment2.hrmps {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.assignment2.hrmps to javafx.fxml;
    exports com.assignment2.hrmps;
}