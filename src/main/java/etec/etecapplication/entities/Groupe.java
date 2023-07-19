package etec.etecapplication.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Groupe {
    private SimpleIntegerProperty id;
    private SimpleStringProperty nom;
    private SimpleStringProperty niveau;

    public Groupe(Integer id,String nom, String niveau) {
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.niveau = new SimpleStringProperty(niveau);
    }

    public String getNiveau() {
        return niveau.get();
    }

    public SimpleStringProperty niveauProperty() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau.set(niveau);
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
