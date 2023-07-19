package etec.etecapplication.controller;




import etec.etecapplication.HelloApplication;
import etec.etecapplication.entities.Enseignant;
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


public class EnseignantController implements Initializable {

    @FXML
    private Button ajouterFormateur;

    @FXML
    private TableColumn<Enseignant, String> emailColumn;

    @FXML
    private TextField emailText;

    @FXML
    private Button homeButton;
    @FXML
    private Button modifierFormateur;

    @FXML
    private TableColumn<Enseignant, String> nomColumn;

    @FXML
    private TextField nomText;

    @FXML
    private TableColumn<Enseignant, String> prenomColumn;

    @FXML
    private TextField prenomText;

    @FXML
    private Button supprimerFormateur;

    @FXML
    private TableView<Enseignant> table;

    @FXML
    private TableColumn<Enseignant, String> telColumn;

    @FXML
    private TextField telText;

    private ObservableList<Enseignant> enseignants = FXCollections.observableArrayList();






    Connection con;
    PreparedStatement stm;


    public void ajouter(ActionEvent e) {
        String nom = nomText.getText();
        String prenom = prenomText.getText();
        String tel = telText.getText();
        String email = emailText.getText();
        if (nom.isEmpty() == true || prenom.isEmpty() == true || tel.isEmpty() == true || email.isEmpty() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Champs Vide");
            alert.setContentText("Un Champs Vide");

        }else {

            try {
                stm = con.prepareStatement("insert into `enseignant`(nom,prenom,tel,email) values (?,?,?,?)");
                stm.setString(1, nom);
                stm.setString(2, prenom);
                stm.setString(3, tel);
                stm.setString(4, email);
                stm.executeUpdate();
                tableMethod();
                nomText.setText("");
                prenomText.setText("");
                telText.setText("");
                emailText.setText("");
                nomText.requestFocus();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void onClick(MouseEvent event){
        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        nomText.setText(table.getItems().get(myIndex).getNom());
        prenomText.setText(table.getItems().get(myIndex).getPrenom());
        telText.setText(table.getItems().get(myIndex).getTel());
        emailText.setText(table.getItems().get(myIndex).getEmail());
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




    public void tableMethod()
    {
         connect();
            enseignants.removeAll();
            table.getItems().clear();
        try
        {
            stm = con.prepareStatement("select id,nom,prenom,tel,email from enseignant");
            ResultSet rs = stm.executeQuery();
            {
                while (rs.next()) {
                    enseignants.add(new Enseignant(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                    table.setItems(enseignants);
                    nomColumn.setCellValueFactory(f->f.getValue().nomProperty());
                    prenomColumn.setCellValueFactory(f->f.getValue().prenomProperty());
                    telColumn.setCellValueFactory(f->f.getValue().telProperty());
                    emailColumn.setCellValueFactory(f->f.getValue().emailProperty());
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    int myIndex;
    int id;

    public void update(ActionEvent event){
        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));

        String nom = nomText.getText();
        String prenom = prenomText.getText();
        String tel = telText.getText();
        String email = emailText.getText();

        if (nom.isEmpty() == true || prenom.isEmpty() == true || tel.isEmpty() == true || email.isEmpty() == true) {

            System.out.println("Empty Fields");
        }else {

            try {
                stm = con.prepareStatement("update enseignant set nom = ?, prenom = ? , tel = ?, email= ?  where id= ?");
                stm.setString(1, nom);
                stm.setString(2, prenom);
                stm.setString(3, tel);
                stm.setString(4, email);
                stm.setInt(5, id);
                stm.executeUpdate();
                tableMethod();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    @FXML
     void delete(ActionEvent event) throws SQLException {
        myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
        try {
            stm = con.prepareStatement("delete from enseignant  where id= ?");
            stm.setInt(1,id);
            stm.executeUpdate();
            tableMethod();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableMethod();
        connect();
    }


    public void returnButton() throws IOException {
        HelloApplication.switchscene("home.fxml");
    }
}
