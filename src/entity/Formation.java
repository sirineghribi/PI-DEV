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
        private int id_f,nbr_heure;
    private typeformation type;
    private Date date;
    private Utilisateur c; 
    
    public Formation() {
}


    
    public Formation(int id_f,Utilisateur c , typeformation type, Date date,int nbr_heure) {
        this.id_f = id_f;
        this.c = c;
        this.type= type;
        this.date = date;
        this.nbr_heure=nbr_heure;
    }

    public Formation( Utilisateur c, int nbr_heure, typeformation type, Date date) {
        this.c =  c;
        this.nbr_heure = nbr_heure;
        this.type = type;
        this.date = date;
    }
    
    

    public Utilisateur getUtilisateur () {
        return c;
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

    public typeformation getType() {
        return type;
    }

    public void setId_f(int id_f) {
        this.id_f = id_f;
    }

    public void setNbrheur(int nbr_heure) {
        this.nbr_heure = nbr_heure;
    }

    public void setType(typeformation type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Formation{" + "id_f=" + id_f + ", \n c=" + c + ", \n type=" + type + ", \n nbr_heure=" + nbr_heure + '}';
    }
    public static typeformation stringToType(String s){
    
    if (s.equals(typeformation.gforceprep.toString()))
        return typeformation.gforceprep;
    else 
    if (s.equals(typeformation.muscleprep.toString()))
        return typeformation.muscleprep;
    else 
        return typeformation.skeletonprep;
    }
    }
    
