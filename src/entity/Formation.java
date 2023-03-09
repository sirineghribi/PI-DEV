/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;



/**
 *
 * @author abder
 */
public class Formation {
        private int id_f,id_c,nbr_heure;
    private String type;
    Date date;

    public Formation() {
}


    
    public Formation(int id_f, int id_c, String type, Date date,int nbr_heure) {
        this.id_f = id_f;
        this.id_c = id_c;
        this.type= type;
        this.date = date;
        this.nbr_heure=nbr_heure;
    }

    public Formation(int id_c, int nbr_heure, String type, Date date) {
        this.id_c = id_c;
        this.nbr_heure = nbr_heure;
        this.type = type;
        this.date = date;
    }
    
    

    public int getId_c() {
        return id_c;
    }
    
        public int getId_f() {
        return id_f;
    }

    public int getNbrheur() {
        return nbr_heure;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public void setId_f(int id_f) {
        this.id_f = id_f;
    }

    public void setNbrheur(int nbr_heure) {
        this.nbr_heure = nbr_heure;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Formation{" + "id_f=" + id_f + ", id_c=" + id_c + ", type" + type + ", nbr_heure=" + nbr_heure + '}';
    }
    }
    
