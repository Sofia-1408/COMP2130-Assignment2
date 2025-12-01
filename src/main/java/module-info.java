module com.assignment2.hrmps {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens com.assignment2.hrmps.controller to javafx.fxml;

    //opens com.assignment2.hrmps.model to javafx.base;

    exports com.assignment2.hrmps;
}