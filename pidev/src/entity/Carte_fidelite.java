/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Zeineb Ben Mami
 */
public class Carte_fidelite {
    private int numero,nbr_point,id_u;

    public Carte_fidelite() {
    }

    public Carte_fidelite(int numero, int nbr_point, int id_u) {
        this.numero = numero;
        this.nbr_point = nbr_point;
        this.id_u = id_u;
    }
    
    
     public Carte_fidelite( int nbr_point, int id_u) {
     
        this.nbr_point = nbr_point;
        this.id_u = id_u;
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

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
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
        if (this.id_u != other.id_u) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Carte_fidelite{" + "numero=" + numero + ", nbr_point=" + nbr_point + ", id_u=" + id_u + '}';
    }
    
    
    
}
