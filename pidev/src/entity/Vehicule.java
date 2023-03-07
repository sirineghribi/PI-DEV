/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ASUS
 */
public class Vehicule {
    
     private int id_vehicule;
    private String cat_vehicule;
    private float poid_sup;
    private int vitesse;
    private int nbr_pas;
    private boolean status;
    
    public Vehicule(){
        
    }

    public Vehicule(int id_vehicule, String cat_vehicule, float poid_sup, int vitesse) {
        this.id_vehicule = id_vehicule;
        this.cat_vehicule = cat_vehicule;
        this.poid_sup = poid_sup;
        this.vitesse = vitesse;
    }
    
    public Vehicule(int id, String cat, float poid, int vitesse, int passager, boolean status){
        this.id_vehicule=id; this.cat_vehicule=cat; this.poid_sup=poid; this.vitesse=vitesse;
        this.nbr_pas=passager; this.status=status; 
    }

    public Vehicule(int id_vehicule, int nbr_pas) {
        this.id_vehicule = id_vehicule;
        this.nbr_pas = nbr_pas;
    }
    
    
    public int get_id_vehicule(){
        return id_vehicule;
    }
    
    public String get_cat_vehicule(){
        return cat_vehicule;
    }
    
    public float get_poid_sup(){
        return poid_sup;
    }
    
    public int get_vitesse(){
        return vitesse;
    }
    
    public int get_nbr_pas(){
        return nbr_pas;
    }
    
    public boolean get_status(){
        return status;
    }
    
    public void set_id_vehicule(int id_vehicule){
        this.id_vehicule=id_vehicule;
    }
    
    public void set_cat_vehicule(String cat_vehicule){
        this.cat_vehicule=cat_vehicule;
    }
    
    public void set_poid_sup(float poid_sup){
        this.poid_sup=poid_sup;
    }
    
    public void set_vitesse(int vitesse){
        this.vitesse=vitesse;
    }
    
    public void set_nbr_pas(int nbr_pas){
        this.nbr_pas=nbr_pas;
    }
    
    public void set_status(boolean status){
        this.status=status;
    }
    
    @Override
    public String toString() {
        return "Vehicule{" + "id=" + id_vehicule + ", categorie=" + cat_vehicule + ", poid=" + poid_sup + ", vitesse=" + vitesse + ", capacite_passager=" + nbr_pas + ", status="+ status + '}';
    }
    
    
}
