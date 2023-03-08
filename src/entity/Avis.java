/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author sirin
 */
public class Avis {
    private int id_avis ;
    private NoteA note;
    private String description;
    private Utilisateur utilisateur;
    private Vol vol;
   
 
    public Avis(){}

    public Avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public Avis(NoteA note, Vol vol) {
        this.note = note;
        this.vol = vol;
    }

    public Avis(NoteA note, String description) {
        this.note = note;
        this.description = description;
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

    public NoteA getNote() {
        return note;
    }

    
    
    public void setNote(NoteA note) {
        this.note = note;
    }

    public Avis(int id_avis, NoteA note, String description, Utilisateur utilisateur, Vol vol) {
        this.id_avis = id_avis;
        this.note = note;
        this.description = description;
        this.utilisateur = utilisateur;
        this.vol = vol;
    }

    @Override
    public String toString() {
        return "Avis{" + "id_avis=" + id_avis + ", note=" + note + ", description=" + description + ", utilisateur=" + utilisateur + ", vol=" + vol + '}';
    }

   

   

   

    

    public Avis(NoteA note, String description, Utilisateur utilisateur, Vol vol) {
        this.note = note;
        this.description = description;
        this.utilisateur = utilisateur;
        this.vol = vol;
    }

  
    public Avis(int id_avis, NoteA note, String description, Utilisateur utilisateur) {
        this.id_avis = id_avis;
        this.note = note;
        this.description = description;
        this.utilisateur = utilisateur;
    }

    public Avis(NoteA note, String description, Utilisateur utilisateur) {
        this.note = note;
        this.description = description;
        this.utilisateur = utilisateur;
    }

    public Avis(int id_avis, String description) {
        this.id_avis = id_avis;
        this.description = description;
    }

  

  

    public int getId_avis() {
        return id_avis;
    }

   

    public String getDescription() {
        return description;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

   

    public void setDescription(String description) {
        this.description = description;
    }
public static NoteA enumnote(String s ){
    if(s.equals(NoteA.Excellent.toString())){
        return NoteA.Excellent;
    }
else if(s.equals(NoteA.Bien.toString())){
return NoteA.Bien;
    
}
    else if(s.equals(NoteA.Moyen.toString())){
return NoteA.Moyen;
    
}
    else return NoteA.Mauvais;
} 
    
}
