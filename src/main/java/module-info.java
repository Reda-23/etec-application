module etec.etecapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens etec.etecapplication to javafx.fxml;
    exports etec.etecapplication.dataBaseConnection;
    opens etec.etecapplication.dataBaseConnection to javafx.fxml;
    exports etec.etecapplication.controller;
    opens etec.etecapplication.controller to javafx.fxml;
    exports etec.etecapplication;
}