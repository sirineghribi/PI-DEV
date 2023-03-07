/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;


/**
 *
 * @author lenovo
 */
public class Reservation {
    
    private int id_r,cin,num_phone,conditionA;
    private String etat;
    private Date date_res; 
    private float prix;
    private Vol vol ;
    private Utilisateur utilisateur;

    public Reservation() {
    }

    public Reservation(int id_r, int cin, int num_phone, int conditionA, String etat, Date date_res, float prix, Vol vol, Utilisateur utilisateur) {
        this.id_r = id_r;
        this.cin = cin;
        this.num_phone = num_phone;
        this.conditionA = conditionA;
        this.etat = etat;
        this.date_res = date_res;
        this.prix = prix;
        this.vol = vol;
        this.utilisateur = utilisateur;
    }
    public Reservation(int id_r, int cin, int num_phone, int conditionA, String etat, Date date_res, float prix, Vol vol) {
        this.id_r = id_r;
        this.cin = cin;
        this.num_phone = num_phone;
        this.conditionA = conditionA;
        this.etat = etat;
        this.date_res = date_res;
        this.prix = prix;
        this.vol = vol;
       
    }
    
    public Reservation(int id_r, int cin, int num_phone, int conditionA, String etat, Date date_res, Vol vol, Utilisateur utilisateur) {
        this.id_r = id_r;
        this.cin = cin;
        this.num_phone = num_phone;
        this.conditionA = conditionA;
        this.etat = etat;
        this.date_res = date_res;
        this.vol = vol;
        this.utilisateur = utilisateur;
        
    }

    public Reservation(int cin, int num_phone, int conditionA, String etat, Date date_res, Vol vol, Utilisateur utilisateur) {
        this.cin = cin;
        this.num_phone = num_phone;
        this.conditionA = conditionA;
        this.etat = etat;
        this.date_res = date_res;
        this.vol = vol;
        this.utilisateur = utilisateur;
        
    }

    public Reservation(int id_r, int cin, int num_phone, int conditionA, Date date_res, Vol vol, Utilisateur utilisateur) {
        this.id_r = id_r;
        this.cin = cin;
        this.num_phone = num_phone;
        this.conditionA = conditionA;
        this.date_res = date_res;
        this.vol = vol;
        this.utilisateur = utilisateur;
        
    }
   public Reservation( int cin, int num_phone, int conditionA, Date date_res, Vol vol, Utilisateur utilisateur) {
        
        this.cin = cin;
        this.num_phone = num_phone;
        this.conditionA = conditionA;
        this.date_res = date_res;
        this.vol = vol;
        this.utilisateur = utilisateur;
        
    }

    public Reservation(int id_r,  int num_phone, String etat, Date date_res, float prix, Vol vol, Utilisateur utilisateur) {
        this.id_r = id_r;
        this.num_phone = num_phone;
        this.etat = etat;
        this.date_res = date_res;
        this.prix = prix;
        this.vol = vol;
        this.utilisateur = utilisateur;
    }

    public Reservation(int id_r, int cin, int num_phone, String etat, Date date_res, float prix, Vol vol, Utilisateur utilisateur) {
        this.id_r = id_r;
        this.cin = cin;
        this.num_phone = num_phone;
        this.etat = etat;
        this.date_res = date_res;
        this.prix = prix;
        this.vol = vol;
        this.utilisateur = utilisateur;
    }
    
    
   
   
    public int getId_r() {
        return id_r;
    }

    public void setId_r(int id_r) {
        this.id_r = id_r;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getNum_phone() {
        return num_phone;
    }

    public void setNum_phone(int num_phone) {
        this.num_phone = num_phone;
    }

    public int getConditionA() {
        return conditionA;
    }

    public void setConditionA(int conditionA) {
        this.conditionA = conditionA;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate_res() {
        return date_res;
    }

    public void setDate_res(Date date_res) {
        this.date_res = date_res;
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_r=" + id_r + ", cin=" + cin + ", num_phone=" + num_phone + ", conditionA=" + conditionA + ", etat=" + etat + ", date_res=" + date_res + ", prix=" + prix + ", vol=" + vol + ", utilisateur=" + utilisateur + '}';
    }

    

    

    
    
    
   
    
    
    
    
   
    
    
    
}
