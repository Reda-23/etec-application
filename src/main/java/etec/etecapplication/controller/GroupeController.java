package etec.etecapplication.controller;

import etec.etecapplication.HelloApplication;
import etec.etecapplication.entities.Groupe;
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

public class GroupeController implements Initializable {


    @FXML
    private Button ajouterGroupe;

    @FXML
    private Button homeButton;

    @FXML
    private TableView<Groupe> table;

    @FXML
    private TableColumn<Groupe, String> idColumn;

    @FXML
    private Button modifierGroupe;

    @FXML
    private TableColumn<Groupe, String> niveauColumn;

    @FXML
    private TextField niveauText;

    @FXML
    private TableColumn<Groupe, String> nomColumn;

    @FXML
    private TextField nomText;

    @FXML
    private Button supprimerGroupe;

    Connection con;
    PreparedStatement stm;
    int id;
    int myIndex;


    private  ObservableList<Groupe> groupes = FXCollections.observableArrayList();


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
        String niveau = niveauText.getText();
        if ((nom.isEmpty()) && (niveau.isEmpty())) {
            System.out.println("!!!!");
        }
        else {
            try {
                stm = con.prepareStatement("insert into groupe(nom,niveau) values (?,?)");
                stm.setString(1, nom);
                stm.setString(2, niveau);
                stm.executeUpdate();
                tableMethod();
                nomText.setText("");
                niveauText.setText("");
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
                    niveauText.setText(table.getItems().get(myIndex).getNiveau());
    }

    @FXML
    void delete(ActionEvent event) {
        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        groupes.remove(myIndex);
        try {
            stm = con.prepareStatement("delete from groupe where id=?");
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
        String niveau = niveauText.getText();
        try {
            stm = con.prepareStatement("update groupe set nom =? , niveau = ? where id = ?");
            stm.setString(1,nom);
            stm.setString(2,niveau);
            stm.setInt(3,id);
            stm.executeUpdate();
            tableMethod();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void tableMethod(){
        connect();
        groupes.removeAll();
        table.getItems().clear();
        try {
            stm = con.prepareStatement("select * from groupe");
            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                groupes.add(new Groupe(rs.getInt(1),rs.getString(2),rs.getString(3)));
                table.setItems(groupes);
                idColumn.setCellValueFactory(f->f.getValue().idProperty().asString());
                nomColumn.setCellValueFactory(f->f.getValue().nomProperty());
                niveauColumn.setCellValueFactory(f->f.getValue().niveauProperty());
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
