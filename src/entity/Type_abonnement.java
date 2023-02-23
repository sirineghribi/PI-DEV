/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;

/**
 *
 * @author zied loukil
 */
public class Type_abonnement implements Comparable<Type_abonnement>{
    private int id;
    private String nom,description;
    private float periode,offre,prix;

    public Type_abonnement() 
    {
        id=1;
        nom="";
        description="";
        periode=0;
        offre=0.5f;
    }

    public Type_abonnement(String nom, String description, float periode, float offre,float prix) {
        this.nom = nom;
        this.description = description;
        this.periode = periode;
        this.offre = offre;
        this.prix=prix;
    }

    public Type_abonnement(int id, String nom, String description, float periode, float offre,float prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.periode = periode;
        this.offre = offre;
        this.prix=prix;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPeriode() {
        return periode;
    }

    public void setPeriode(float periode) {
        this.periode = periode;
    }

    public float getOffre() {
        return offre;
    }

    public void setOffre(float offre) {
        this.offre = offre;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    

    @Override
    public String toString() {
        return "Type abonnement{id=" + id + "|nom=" + nom + "|description=" + description + "|periode=" + periode + "|offre=" + offre+"|prix="+prix+"}";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
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
        final Type_abonnement other = (Type_abonnement) obj;
        return this.id == other.id;
    }
    
    public static void display(List<Type_abonnement> l){
       int i=0;
       for(Type_abonnement t:l)
       {
           i++;
           System.out.println("type d'abonnement "+i+":\n"+t.toString());
       }
    }

    @Override
    public int compareTo(Type_abonnement o) {
        return this.nom.compareTo(o.nom);
    }
    
}
