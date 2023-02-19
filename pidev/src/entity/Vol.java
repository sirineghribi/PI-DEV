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
    
    private int id_v,nbr_place;
    private String destination,etat;
    private float prix ;
    private Date date;
    private Vehicule mt ;
    public Vol() {
    }

    public Vol(int id_v, int nbr_place, String destination, String etat, float prix, Date date, Vehicule mt) {
        this.id_v = id_v;
        this.nbr_place = nbr_place;
        this.destination = destination;
        this.etat = etat;
        this.prix = prix;
        this.date = date;
        this.mt = mt;
    }

    public Vol(int nbr_place, String destination, String etat, float prix, Date date, Vehicule mt) {
        this.nbr_place = nbr_place;
        this.destination = destination;
        this.etat = etat;
        this.prix = prix;
        this.date = date;
        this.mt = mt;
    }

    public Vol(int id_v, int nbr_place, String destination, float prix, Date date, Vehicule mt) {
        this.id_v = id_v;
        this.nbr_place = nbr_place;
        this.destination = destination;
        this.prix = prix;
        this.date = date;
        this.mt = mt;
    }

    public int getId_v() {
        return id_v;
    }

    public void setId_v(int id_v) {
        this.id_v = id_v;
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

    public Vehicule getMt() {
        return mt;
    }

    public void setMt(Vehicule mt) {
        this.mt = mt;
    }

    @Override
    public String toString() {
        return "Vol{" + "id_v=" + id_v + ", nbr_place=" + nbr_place + ", destination=" + destination + ", etat=" + etat + ", prix=" + prix + ", date=" + date + ", mt=" + mt + '}';
    }

    
    
    
    
    
    
    
    
}
