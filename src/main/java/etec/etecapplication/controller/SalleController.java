package etec.etecapplication.controller;

import etec.etecapplication.HelloApplication;
import etec.etecapplication.entities.Salle;
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

public class SalleController implements Initializable {


    @FXML
    private Button ajouterSalle;

    @FXML
    private TableColumn<Salle, String > capaciteColumn;

    @FXML
    private TextField capaciteText;

    @FXML
    private Button homeButton;

    @FXML
    private TableColumn<Salle, String > idColumn;

    @FXML
    private Button modifierSalle;

    @FXML
    private TableColumn<Salle, String > nomColumn;

    @FXML
    private TextField nomText;

    @FXML
    private Button supprimerSalle;

    @FXML
    private TableView<Salle> table;

    @FXML
    private TableColumn<Salle, String > typeColumn;

    @FXML
    private TextField typeText;

    private ObservableList<Salle> salles = FXCollections.observableArrayList();

    Connection con;
    PreparedStatement stm;

    int myIndex;
    int id;


    public void connect()
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/etecschool", "root","");
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }


    @FXML
    void add(ActionEvent event) {
        String nom = nomText.getText();
        String capacite = capaciteText.getText();
        String type = typeText.getText();
        if ((nom.isEmpty()== true) && (capacite.isEmpty() == true) && (type.isEmpty() == true)) {
            System.out.println("No Data Is Available");
        }
        else {
            try {
                stm = con.prepareStatement("insert into salle(nom,capacite,type) values (?,?,?)");
                stm.setString(1, nom);
                stm.setString(2, capacite);
                stm.setString(3, type);
                stm.executeUpdate();
                tableMethod();
                nomText.setText("");
                capaciteText.setText("");
                typeText.setText("");
                nomText.requestFocus();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }}
    }

    @FXML
    public void onClick(MouseEvent event){
        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        nomText.setText(table.getItems().get(myIndex).getNom());
        capaciteText.setText(table.getItems().get(myIndex).getCapacite());
        typeText.setText(table.getItems().get(myIndex).getType());
    }

    public void tableMethod(){
        connect();
        salles.removeAll();
        table.getItems().clear();
        try {
            stm = con.prepareStatement("select * from salle");
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                salles.add(new Salle(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
                table.setItems(salles);
                idColumn.setCellValueFactory(f->f.getValue().idProperty().asString());
                nomColumn.setCellValueFactory(f->f.getValue().nomProperty());
                capaciteColumn.setCellValueFactory(f->f.getValue().capaciteProperty());
                typeColumn.setCellValueFactory(f->f.getValue().typeProperty());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void delete(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        try {
            stm = con.prepareStatement("delete from salle where id=?");
            stm.setInt(1,id);
            stm.executeUpdate();
            tableMethod();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        String capacite = capaciteText.getText();
        String type = typeText.getText();
        try {
            stm = con.prepareStatement("update salle set nom =? , capacite = ? , type = ? where id = ?");
            stm.setString(1,nom);
            stm.setString(2,capacite);
            stm.setString(3,type);
            stm.setInt(4,id);
            stm.executeUpdate();
            tableMethod();
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
