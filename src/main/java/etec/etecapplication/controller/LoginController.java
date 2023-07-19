package etec.etecapplication.controller;


import etec.etecapplication.HelloApplication;
import etec.etecapplication.dataBaseConnection.DataBaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

    @FXML
   private  Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField usernameTextField;



    public void labelMessageLogin(ActionEvent e){
        if ((passwordTextField.getText().isBlank() == false) && (usernameTextField.getText().isBlank() == false) ) {
           loginValidation();
        }else {
            loginMessageLabel.setText("please enter username & password to login");
        }
    }
    public void cancelButtonAction(ActionEvent e){
        Stage  stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginValidation() {

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connection = connectNow.getConnection();

        String verifyLogin = "SELECT COUNT(1) FROM `useraccount` WHERE userName ='" + usernameTextField.getText() + "' AND password ='" + passwordTextField.getText() + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Welcome");
                        HelloApplication.switchscene("home.fxml");
                } else {
                    loginMessageLabel.setText("Invalid login, please try again");

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}


