/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author lenovo
 */
public class Vol {
    
    private int id_v,id_mt,nbr_place;
    private String destination,etat;
    private float prix ;
    private Date date;

    public Vol() {
    }

    public Vol(int id_v, int id_mt, int nbr_place, String destination, String etat, float prix, Date date) {
        this.id_v = id_v;
        this.id_mt = id_mt;
        this.nbr_place = nbr_place;
        this.destination = destination;
        this.etat = etat;
        this.prix = prix;
        this.date = date;
    }

    public Vol(int id_v, int id_mt, String destination, String etat, float prix, Date date) {
        this.id_v = id_v;
        this.id_mt = id_mt;
        this.destination = destination;
        this.etat = etat;
        this.prix = prix;
        this.date = date;
    }

    public Vol(int id_mt, String destination, String etat, float prix, Date date) {
        this.id_mt = id_mt;
        this.destination = destination;
        this.etat = etat;
        this.prix = prix;
        this.date = date;
    }

    public int getId_v() {
        return id_v;
    }
    
    public void setId_v(int id_v) {
        this.id_v = id_v;
    }

    public int getId_mt() {
        return id_mt;
    }

    public void setId_mt(int id_mt) {
        this.id_mt = id_mt;
    }
    
    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }
    
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id_v;
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
        final Vol other = (Vol) obj;
        if (this.id_v != other.id_v) {
            return false;
        }
        if (this.id_mt != other.id_mt) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vol{" + "id_v=" + id_v + ", id_mt=" + id_mt + ", nbr_place=" + nbr_place + ", destination=" + destination + ", etat=" + etat + ", prix=" + prix + ", date=" + date + '}';
    }

    
    
    
    
    
    
}
