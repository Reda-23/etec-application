package etec.etecapplication.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Module {

    private SimpleIntegerProperty id;
    private SimpleStringProperty nom;
    private SimpleStringProperty cours;
    private SimpleStringProperty td;

    public Module(Integer id, String nom, String cours, String td) {
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.cours = new SimpleStringProperty(cours);
        this.td = new SimpleStringProperty(td);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getCours() {
        return cours.get();
    }

    public SimpleStringProperty coursProperty() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours.set(cours);
    }

    public String getTd() {
        return td.get();
    }

    public SimpleStringProperty tdProperty() {
        return td;
    }

    public void setTd(String td) {
        this.td.set(td);
    }
}
