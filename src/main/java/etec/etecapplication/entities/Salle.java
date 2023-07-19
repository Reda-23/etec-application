package etec.etecapplication.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Salle {
    private SimpleIntegerProperty id;
    private SimpleStringProperty nom;
    private SimpleStringProperty capacite;
    private SimpleStringProperty type;

    public Salle(Integer id, String nom, String capacite, String type) {
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.capacite = new SimpleStringProperty(capacite);
        this.type = new SimpleStringProperty(type);
    }

    public String getCapacite() {
        return capacite.get();
    }

    public SimpleStringProperty capaciteProperty() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite.set(capacite);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
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
}
