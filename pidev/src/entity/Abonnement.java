/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
/**
 *
 * @author zied loukil
 */
public class Abonnement implements Comparable<Abonnement>{
    private int id;
    private Date d;
    private Type_abonnement type;
    private Utilisateur c;

    public Abonnement() 
    {
 
    }

    public Abonnement(Date d, Type_abonnement type, Utilisateur c) {
        this.d = d;
        this.type = type;
        this.c = c;
    }

    public Abonnement(int id, Date d, Type_abonnement type, Utilisateur c) {
        this.id = id;
        this.d = d;
        this.type = type;
        this.c = c;
    }

    public Utilisateur getC() {
        return c;
    }

    public int getId() {
        return id;
    }

    public Date getD() {
        return d;
    }

    public Type_abonnement getType() {
        return type;
    }

    public void setC(Utilisateur c) {
        this.c = c;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public void setType(Type_abonnement type) {
        this.type = type;
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
        final Abonnement other = (Abonnement) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", d=" + d + ", type=" + type + ", c=" + c + '}';
    }

    
    
    @Override
    public int compareTo(Abonnement o) {
        return this.getD().compareTo(o.getD());
    }
   
}
