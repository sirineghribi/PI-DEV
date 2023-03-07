/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author sirin
 */
public class Avis {
    private int id_avis ;
    private String note,description;
    private int id_c;
    public Avis(){}

    public Avis(int id_avis, String note, String description, int id_c) {
        this.id_avis = id_avis;
        this.note = note;
        this.description = description;
        this.id_c = id_c;
    }

    

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public int getId_c() {
        return id_c;
    }

    public int getId_avis() {
        return id_avis;
    }

    public String getNote() {
        return note;
    }

    public String getDescription() {
        return description;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Avis{" + "id_avis=" + id_avis + ", note=" + note + ", description=" + description + '}';
    }

    public Avis(String note, String description) {
        this.note = note;
        this.description = description;
    }

    public Avis(int id_avis, String note, String description) {
        this.id_avis = id_avis;
        this.note = note;
        this.description = description;
    }
    
}
