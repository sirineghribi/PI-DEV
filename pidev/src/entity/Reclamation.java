/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;

/**
 *
 * @author sirin
 */
public class Reclamation {
    private int id_rec;
    private String type;
    private String description;
    private int id_c;
    private String etat;

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reclamation other = (Reclamation) obj;
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        return true;
    }

   

    public int getId_c() {
        return id_c;
    }
    

    public Reclamation(String type, String description, int id_c,String etat) {
        this.type = type;
        this.description = description;
        this.id_c = id_c;
        this.etat = etat;
      
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }
    
public Reclamation(){}

    public Reclamation(int id_rec, String type, String description,int id_c, String etat) {
        this.id_rec = id_rec;
        this.type = type;
        this.description = description;
        this.id_c= id_c;
        this.etat=etat;
    }

  

    public int getId_rec() {
        return id_rec;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getEtat() {
        return etat;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_rec=" + id_rec + ", type=" + type + ", description=" + description + ", id_c=" + id_c + ", etat=" + etat + '}';
    }

   
    


    
}
