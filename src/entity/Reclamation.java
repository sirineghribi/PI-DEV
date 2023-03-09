/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;
import entity.Typerec;
import entity.Utilisateur;

/**
 *
 * @author sirin
 */
public class Reclamation {
    private int id_rec;
    private Typerec type;
    private String description;
    private Utilisateur utilisateur;
    private String etat;

    public Reclamation(Typerec type, String description) {
        this.type = type;
        this.description = description;
    }

    public Reclamation(int id_rec, String description) {
        this.id_rec = id_rec;
        this.description = description;
    }

    public Reclamation(String description) {
        this.description = description;
    }

    public Reclamation(Typerec type, String description, Utilisateur utilisateur) {
        this.type = type;
        this.description = description;
        this.utilisateur = utilisateur;
    }

   

    public Typerec getType() {
        return type;
    }

    public void setType(Typerec type) {
        this.type = type;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Reclamation(Typerec type, String description, Utilisateur utilisateur, String etat) {
        this.type = type;
        this.description = description;
        this.utilisateur = utilisateur;
        this.etat = etat;
    }

    public Reclamation(int id_rec, Typerec type, String description, Utilisateur utilisateur, String etat) {
        this.id_rec = id_rec;
        this.type = type;
        this.description = description;
        this.utilisateur = utilisateur;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_rec=" + id_rec + ", type=" + type + ", description=" + description + ", utilisateur=" + utilisateur + ", etat=" + etat + '}';
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

  
  
public Reclamation(){}

    
  

    public int getId_rec() {
        return id_rec;
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

  

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

   
public static Typerec enumtype(String s ){
    if(s.equals(Typerec.service.toString())){
        return Typerec.service;
    }
else if(s.equals(Typerec.technique.toString())){
return Typerec.technique;
}
    else return Typerec.autre;
}
   
    


    
}