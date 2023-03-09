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
public class CategorieVehicule {
    private int id_cat;
    private String nom_cat;
    private int id_vh;
    
    public CategorieVehicule(){
        
    }
    
     public CategorieVehicule(int id_cat, String cat, int id){
        this.id_cat=id_cat; this.nom_cat=cat; this.id_vh=id;
    }
     
     public int get_id_cat(){
        return id_cat;
    }
    
    public void set_id_cat(int id_cat){
        this.id_cat=id_cat;
    }
    
     public String get_nom_cat(){
        return nom_cat;
    }
    
    public void set_nom_cat(String nom_cat){
        this.nom_cat=nom_cat;
    }
    
     public int get_id_vh(){
        return id_vh;
    }
    
    public void set_id_vh(int id_vh){
        this.id_vh=id_vh;
    }
    
    @Override
    public String toString(){
         return "CategorieVehicule{" + "id_cat=" + id_cat + ", id_vh=" + id_vh + ", nom_cat="+ nom_cat + '}';
    }
}
