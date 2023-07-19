package etec.etecapplication.controller;


import etec.etecapplication.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EmploiController implements Initializable {





    Connection con;
    PreparedStatement stm;

    ResultSet rs;


    @FXML
    private Button addButton;

    @FXML
    private ComboBox<String> enseignatBox ;
    @FXML
    private ComboBox<String> moduleBox;

    @FXML
    private ComboBox<String> salleBox;

    @FXML
    private Button homeButton;

    @FXML
    private Button deleteButton;
    @FXML
    private ComboBox<String> groupeBox;

    @FXML
    private TextArea textArea1;

    @FXML
    private TextArea textArea2;

    @FXML
    private TextArea textArea3;
    @FXML
    private TextArea textArea4;
    @FXML
    private TextArea textArea5;
    @FXML
    private TextArea textArea6;
    @FXML
    private TextArea textArea7;
    @FXML
    private TextArea textArea8;
    @FXML
    private TextArea textArea9;
    @FXML
    private TextArea textArea10;
    @FXML
    private TextArea textArea11;
    @FXML
    private TextArea textArea12;
    @FXML
    private TextArea textArea13;
    @FXML
    private TextArea textArea14;
    @FXML
    private TextArea textArea15;
    @FXML
    private TextArea textArea16;
    @FXML
    private TextArea textArea17;
    @FXML
    private TextArea textArea18;
    @FXML
    private TextArea textArea19;
    @FXML
    private TextArea textArea20;

    @FXML
    private Button printButton;


    @FXML
    private AnchorPane node;


    @FXML
    void printData(ActionEvent event) {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null) {
            PageLayout pageLayout = printerJob.getPrinter().createPageLayout(Paper.A3, PageOrientation.PORTRAIT,0,0,0,0);
            boolean success = printerJob.printPage(pageLayout, node);
            if (success) {
                printerJob.endJob();
            }
        }


    }
    @FXML
    void returnButton(ActionEvent event) throws IOException {
        HelloApplication.switchscene("home.fxml");
    }


    public void enseignantBox() {
        ObservableList<String> enseignantsData = FXCollections.observableArrayList();
        try {
            connect();
            stm = con.prepareStatement("SELECT * FROM `enseignant`");
            rs = stm.executeQuery();
            while (rs.next()) {
                String data = rs.getString(2);
                String data2 = rs.getString(3);
                String combo = data2 + " " + data;
                enseignantsData.add(combo);
                enseignatBox.setItems(enseignantsData);

            }
        }catch (SQLException e){
            e.printStackTrace();
    }

    }

    public void moduleBox(){
        ObservableList<String> moduleData = FXCollections.observableArrayList();
        try {
            connect();
            stm = con.prepareStatement("SELECT * FROM `module`");
            rs = stm.executeQuery();
            while (rs.next()){
                String data = rs.getString(2);
                moduleData.add(data);
                moduleBox.setItems(moduleData);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void salleBox(){
        ObservableList<String> salleData = FXCollections.observableArrayList();
        try {
            connect();
            stm = con.prepareStatement("SELECT * FROM `salle`");
            rs = stm.executeQuery();
            while (rs.next()){
                String data = rs.getString(2);
                salleData.add(data);
                salleBox.setItems(salleData);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void groupeBox(){
        ObservableList<String> groupeData = FXCollections.observableArrayList();
        try {
            connect();
            stm = con.prepareStatement("SELECT * FROM `groupe`");
            rs = stm.executeQuery();
            while (rs.next()){
                String data = rs.getString(2);
                groupeData.add(data);
                groupeBox.setItems(groupeData);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    TextArea selectedTextArea;

    @FXML
    void addData() {
        if (selectedTextArea != null) {
            selectedTextArea.setText("Professor : " + enseignatBox.getSelectionModel().getSelectedItem() + "\n");
            selectedTextArea.appendText("Module : " + moduleBox.getSelectionModel().getSelectedItem() + "\n");
            selectedTextArea.appendText("Salle : " + salleBox.getSelectionModel().getSelectedItem() + "\n");
            selectedTextArea.appendText("Groupe : " + groupeBox.getSelectionModel().getSelectedItem() + "\n");
        }
    }

    @FXML
    void deleteData(){
        if (selectedTextArea != null){
            selectedTextArea.clear();
        }
    }


    @FXML
    void initializeText(){
            textArea1.setOnMouseClicked(event -> {selectedTextArea = textArea1;});
            textArea2.setOnMouseClicked(event -> {selectedTextArea = textArea2;});
            textArea3.setOnMouseClicked(event -> {selectedTextArea = textArea3;});
            textArea4.setOnMouseClicked(event -> {selectedTextArea = textArea4;});
            textArea5.setOnMouseClicked(event -> {selectedTextArea = textArea5;});
            textArea6.setOnMouseClicked(event -> {selectedTextArea = textArea6;});
            textArea7.setOnMouseClicked(event -> {selectedTextArea = textArea7;});
            textArea8.setOnMouseClicked(event -> {selectedTextArea = textArea8;});
            textArea9.setOnMouseClicked(event -> {selectedTextArea = textArea9;});
            textArea10.setOnMouseClicked(event -> {selectedTextArea = textArea10;});
            textArea11.setOnMouseClicked(event -> {selectedTextArea = textArea11;});
            textArea12.setOnMouseClicked(event -> {selectedTextArea = textArea12;});
            textArea13.setOnMouseClicked(event -> {selectedTextArea = textArea13;});
            textArea14.setOnMouseClicked(event -> {selectedTextArea = textArea14;});
            textArea15.setOnMouseClicked(event -> {selectedTextArea = textArea15;});
            textArea16.setOnMouseClicked(event -> {selectedTextArea = textArea16;});
            textArea17.setOnMouseClicked(event -> {selectedTextArea = textArea17;});
            textArea18.setOnMouseClicked(event -> {selectedTextArea = textArea18;});
            textArea19.setOnMouseClicked(event -> {selectedTextArea = textArea19;});
            textArea20.setOnMouseClicked(event -> {selectedTextArea = textArea20;});
    }











    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/etecschool", "root","");
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect();
        enseignantBox();
        moduleBox();
        salleBox();
        groupeBox();

    }
}
