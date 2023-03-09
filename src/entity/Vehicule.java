/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import entity.CategorieVehicule;
/**
 *
 * @author ASUS
 */
public class Vehicule implements Comparable<Vehicule>{
    
    private int id_vehicule;
    private String nom_vh;
    private CategorieVehicule CategorieVehicule;
    private float poid_sup;
    private int vitesse;
    private int nbr_pas;
    private boolean status;
    
    public Vehicule(){
        
    }

    public Vehicule(int id_vehicule, int nbr_pas) {
        this.id_vehicule = id_vehicule;
        this.nbr_pas = nbr_pas;
    }

    public Vehicule(String nom_vh, CategorieVehicule CategorieVehicule) {
        this.nom_vh = nom_vh;
        this.CategorieVehicule = CategorieVehicule;
    }

    public Vehicule(int id_vehicule, String nom_vh, float poid_sup, int vitesse, int nbr_pas) {
        this.id_vehicule = id_vehicule;
        this.nom_vh = nom_vh;
        this.poid_sup = poid_sup;
        this.vitesse = vitesse;
        this.nbr_pas = nbr_pas;
    }

    public Vehicule(int id_vehicule, String nom_vh) {
        this.id_vehicule = id_vehicule;
        this.nom_vh = nom_vh;
    }
    
    public Vehicule(int id,String nom_vh, CategorieVehicule CategorieVehicule, float poid, int vitesse, int passager, boolean status){
        this.id_vehicule=id; this.nom_vh=nom_vh; this.CategorieVehicule=CategorieVehicule; this.poid_sup=poid; this.vitesse=vitesse;
        this.nbr_pas=passager; this.status=status; 
    }

    public Vehicule(String nom_vh, float poid_sup, int vitesse, int nbr_pas) {
        this.nom_vh = nom_vh;
        this.poid_sup = poid_sup;
        this.vitesse = vitesse;
        this.nbr_pas = nbr_pas;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    
    public Vehicule(String nom_vh, CategorieVehicule CategorieVehicule, float poid_sup, int vitesse, int nbr_pas, boolean status) {
        this.nom_vh=nom_vh;
        this.CategorieVehicule = CategorieVehicule;
        this.poid_sup = poid_sup;
        this.vitesse = vitesse;
        this.nbr_pas = nbr_pas;
        this.status = status;
        
    }

    public String getNom_vh() {
        return nom_vh;
    }

    public void setNom_vh(String nom_vh) {
        this.nom_vh = nom_vh;
    }
    

    
    public int get_id_vehicule(){
        return id_vehicule;
    }

    public CategorieVehicule getCategorieVehicule() {
        return CategorieVehicule;
    }

    public void setCategorieVehicule(CategorieVehicule CategorieVehicule) {
        this.CategorieVehicule = CategorieVehicule;
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
    
   
    
    public void set_id_vehicule(int id_vehicule){
        this.id_vehicule=id_vehicule;
    }

    /*
    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    } */
    
    public void set_poid_sup(float poid_sup){
        this.poid_sup=poid_sup;
    }
    
    public void set_vitesse(int vitesse){
        this.vitesse=vitesse;
    }
    
    public void set_nbr_pas(int nbr_pas){
        this.nbr_pas=nbr_pas;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "id_vehicule=" + id_vehicule + ", CategorieVehicule=" + CategorieVehicule + ", poid_sup=" + poid_sup + ", vitesse=" + vitesse + ", nbr_pas=" + nbr_pas + ", status=" + status + '}';
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
        final Vehicule other = (Vehicule) obj;
        return this.id_vehicule == other.id_vehicule;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id_vehicule;
        return hash;
    }
    
    @Override
    public int compareTo(Vehicule o) {
        return (int)(this.nbr_pas-o.nbr_pas);
    } 
    
    
}
