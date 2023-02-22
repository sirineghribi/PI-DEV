/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;

/**
 *
 * @author Zeineb Ben Mami
 */


public class Utilisateur  {
    private int id;
    private String nom,prenom,email,mdp;
    private Date date_n;
   
    Roles type;
    Genre genre;
    private String gendre;
    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, Genre genre, String email, String mdp, Roles type, Date date_n) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.mdp = mdp;
        this.type = type;
        this.date_n = date_n;
    }

    public Utilisateur(String nom, String prenom, Genre genre, String email, String mdp, Date date_n) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.mdp = mdp;
             
        this.date_n = date_n;
    }
    
     public Utilisateur(int id, String nom, String prenom, Genre genre, String email, String mdp, Date date_n) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.mdp = mdp;
      
        this.date_n = date_n;
    }

    public Utilisateur(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Utilisateur(String nom, String prenom, Genre genre, String email, String mdp, Date date_n, Roles type) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.mdp = mdp;
        this.date_n = date_n;
        this.type = type;
    }

    public Utilisateur(TableColumn<Utilisateur, String> idCol, TableColumn<Utilisateur, String> nomCol, TableColumn<Utilisateur, String> prenomCol, TableColumn<Utilisateur, String> genreCol, TableColumn<Utilisateur, String> emailCol, TableColumn<Utilisateur, String> mdpCol, TableColumn<Utilisateur, String> RoleCol, TableColumn<Utilisateur, String> dateCol) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.mdp = mdp;
        this.type = type;
        this.date_n = date_n;
    }

    public Utilisateur(int id) {
        this.id=id;
    }

  

    

   

   

    
     
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Roles getType() {
        return type;
    }

    public void setType(Roles type) {
        this.type = type;
    }

    public Date getDate_n() {
        return date_n;
    }

    public void setDate_n(Date date_n) {
        this.date_n = date_n;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", genre=" + genre + ", email=" + email + ", mdp=" + mdp + ", type=" + type + ", date_n=" + date_n + '}';
    }

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
        final Utilisateur other = (Utilisateur) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.mdp, other.mdp)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.date_n, other.date_n)) {
            return false;
        }
        return true;
    }
    
    
    public static Roles stringTorole (String s){
        Roles type=Roles.A;
               if(s == null ? Roles.C.toString() == null : s.equals(Roles.C.toString()))
                   type=Roles.C;
               return type;
    }
    
    public static Genre stringTogenre (String s){
        Genre genre =Genre.homme;
               if(s == null ? Genre.femme.toString() == null : s.equals(Genre.femme.toString()))
                   genre = Genre.femme;
               return genre;
    }
    
}
