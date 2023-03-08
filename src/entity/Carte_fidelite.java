/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Zeineb Ben Mami
 */
public class Carte_fidelite {
    private int numero,nbr_point;
      private Date date_c;
    private Utilisateur utilisateur;

    public Carte_fidelite(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Carte_fidelite(int numero) {
        this.numero=numero;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    
    
    
    
    public Carte_fidelite() {
    }

    public Carte_fidelite(int numero, int nbr_point, Utilisateur utilisateur) {
        this.numero = numero;
        this.nbr_point = nbr_point;
         this.utilisateur = utilisateur;
       
    }
   

    public Carte_fidelite(int numero, int nbr_point, Utilisateur utilisateur,Date date_c) {
        this.numero = numero;
        this.nbr_point = nbr_point;
         this.utilisateur = utilisateur;
         this.date_c=date_c;
       
    }
    
    
     public Carte_fidelite( int nbr_point, Utilisateur utilisateur) {
     
        this.nbr_point = nbr_point;
         this.utilisateur = utilisateur;
    }

    
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNbr_point() {
        return nbr_point;
    }

    public void setNbr_point(int nbr_point) {
        this.nbr_point = nbr_point;
    }

    public Date getDate_c() {
        return date_c;
    }

    public void setDate_c(Date date_c) {
        this.date_c = date_c;
    }

   

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Carte_fidelite other = (Carte_fidelite) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (this.nbr_point != other.nbr_point) {
            return false;
        }
        if (this.utilisateur != other.utilisateur) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Carte_fidelite{" + "numero=" + numero + ", nbr_point=" + nbr_point + ", date_c=" + date_c + ", utilisateur=" + utilisateur + '}';
    }

    
    
    
    
}
