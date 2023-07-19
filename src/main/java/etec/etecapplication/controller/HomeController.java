package etec.etecapplication.controller;

import etec.etecapplication.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeController {
    @FXML
    private Button emploiButton;

    @FXML
    private Button enseignantetmatiereButton;

    @FXML
    private Button filiereButton;

    @FXML
    private Button formateurButton;

    @FXML
    private Button groupeButton;


    @FXML
    private Button logoutButton;

    @FXML
    private Button moduleButton;

    @FXML
    private Button salleButton;

    @FXML
    void setEmploiButton(ActionEvent event) throws IOException {
        HelloApplication.switchscene("emploi.fxml");
    }


    @FXML
    void setFilliereButton(ActionEvent event) throws IOException {
        HelloApplication.switchscene("filliere.fxml");
    }

    @FXML
    void setFormateurButton(ActionEvent event) throws IOException {
            HelloApplication.switchscene("enseignant.fxml");
    }

    @FXML
    void setGroupeButton(ActionEvent event) throws IOException {
        HelloApplication.switchscene("groupe.fxml");
    }


    @FXML
    void setLogoutButton(ActionEvent event) throws IOException {
            HelloApplication.switchscene("login.fxml");
    }

    @FXML
    void setModuleButton(ActionEvent event) throws IOException {
            HelloApplication.switchscene("module.fxml");
    }

    @FXML
    void setSalleButton(ActionEvent event) throws IOException {
        HelloApplication.switchscene("salle.fxml");
    }


}
