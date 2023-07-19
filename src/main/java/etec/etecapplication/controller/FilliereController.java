package etec.etecapplication.controller;

import etec.etecapplication.HelloApplication;
import etec.etecapplication.entities.Filliere;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class FilliereController implements Initializable {


    @FXML
    private Button ajouterFilliere;

    @FXML
    private Button homeButton;

    @FXML
    private TableColumn<Filliere, String> idColumn;

    @FXML
    private Button modifierFilliere;

    @FXML
    private TextField nomText;

    @FXML
    private TableColumn<Filliere, String> nomColumn;

    @FXML
    private Button supprimerFilliere;

    @FXML
    private TableView<Filliere> table;

    private ObservableList<Filliere> fillieres = FXCollections.observableArrayList();


    int myIndex;
    int id;

    @FXML
    void add(ActionEvent event) {
        String nom = nomText.getText();
        if (nom.isEmpty() == true) {
            System.out.println("Nom Filliére Is Empty");
        }else {
            try {
                stm = con.prepareStatement("insert into filliere (nom) values (?)");
                stm.setString(1,nom);
                stm.executeUpdate();
                tableMethod();
                nomText.setText("");
                nomText.requestFocus();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void onClick(MouseEvent event){
        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        nomText.setText(table.getItems().get(myIndex).getNom());
    }

    @FXML
    void delete(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        try {
            stm = con.prepareStatement("delete from filliere  where id= ?");
            stm.setInt(1,id);
            stm.executeUpdate();
            tableMethod();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    void returnButton(ActionEvent event) throws IOException {
        HelloApplication.switchscene("home.fxml");

    }

    @FXML
    void update(ActionEvent event) {
       myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        String nom = nomText.getText();

        try {
            stm = con.prepareStatement("update filliere set nom = ? where id = ?");
            stm.setString(1,nom);
            stm.setInt(2,id);
            stm.executeUpdate();
            tableMethod();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    Connection con;
    PreparedStatement stm;

    public void connect()
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/etecschool", "root","");
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }


    public void tableMethod(){
        connect();
        fillieres.removeAll();
        table.getItems().clear();
        try {
            stm = con.prepareStatement("select * from filliere");
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                fillieres.add(new Filliere(rs.getInt(1),rs.getString(2)));
                table.setItems(fillieres);
                idColumn.setCellValueFactory(f -> f.getValue().idProperty().asString());
                nomColumn.setCellValueFactory(f -> f.getValue().nomProperty());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableMethod();
        connect();
    }
}
