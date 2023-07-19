package etec.etecapplication.controller;

import etec.etecapplication.HelloApplication;
import etec.etecapplication.entities.Module;
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

public class ModuleController implements Initializable {

    @FXML
    private Button ajouterModule;

    @FXML
    private TextField coursText;

    @FXML
    private TableColumn<Module,String> courscolumn;

    @FXML
    private Button homeButton;

    @FXML
    private TableColumn<Module,String> idColumn;

    @FXML
    private Button modifierModule;

    @FXML
    private TableColumn<Module,String> moduleColumn;

    @FXML
    private TextField nomText;

    @FXML
    private Button supprimerModule;

    @FXML
    private TableView<Module> table;

    @FXML
    private TableColumn<Module,String> tdColumn;

    @FXML
    private TextField tdText;

    private ObservableList<Module> modules = FXCollections.observableArrayList();

    @FXML
    void add(ActionEvent event) throws SQLException {
        String nom = nomText.getText();
        String cours = coursText.getText();
        String td = tdText.getText();
        if ((nom.isEmpty() == true) && (cours.isEmpty() == true) && (td.isEmpty() == true)) {
            System.out.println("Fill All The Fields");
        }
        else {
            try {
                stm = con.prepareStatement("insert into module(nom,cours,td) values (?,?,?)");
                stm.setString(1, nom);
                stm.setString(2, cours);
                stm.setString(3, td);
                stm.executeUpdate();
                tableMethod();
                nomText.setText("");
                coursText.setText("");
                tdText.setText("");
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
        coursText.setText(table.getItems().get(myIndex).getCours());
        tdText.setText(table.getItems().get(myIndex).getTd());
    }

    @FXML
    void delete(ActionEvent event) {

        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        try {
            stm = con.prepareStatement("delete from module where id=?");
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
        String cours = coursText.getText();
        String td = tdText.getText();
        try {
            stm = con.prepareStatement("update module set nom=? ,  cours = ? , td = ? where id = ?");
            stm.setString(1, nom);
            stm.setString(2, cours);
            stm.setString(3, td);
            stm.setInt(id,4);
            stm.executeUpdate();
            tableMethod();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    Connection con;
    PreparedStatement stm;

    int myIndex;
    int id;

    public void tableMethod(){
        connect();
        modules.removeAll();
        table.getItems().clear();
        try {
            stm = con.prepareStatement("select * from module");
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                modules.add(new Module(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
                table.setItems(modules);
                idColumn.setCellValueFactory(f->f.getValue().idProperty().asString());
                moduleColumn.setCellValueFactory(f->f.getValue().nomProperty());
                courscolumn.setCellValueFactory(f->f.getValue().coursProperty());
                tdColumn.setCellValueFactory(f->f.getValue().tdProperty());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void connect()
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/etecschool", "root","");
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableMethod();
        connect();
    }
}
