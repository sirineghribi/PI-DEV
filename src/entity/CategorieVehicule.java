/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

//import tn.esprit.services.EnumTypeCat;

/**
 *
 * @author ASUS
 */
public class CategorieVehicule {
    private int id_cat;
    private TypeCat nom_cat;
    private String lieu;
    
    public CategorieVehicule(){
        
    }

    public CategorieVehicule(int id_cat, String lieu) {
        this.id_cat = id_cat;
        this.lieu = lieu;
    }

    
    public CategorieVehicule(int id_cat, TypeCat nom_cat,String lieu) {
        this.id_cat = id_cat;
        this.nom_cat = nom_cat;
        this.lieu=lieu;
    }

    public CategorieVehicule(int id_cat, TypeCat nom_cat) {
        this.id_cat = id_cat;
        this.nom_cat = nom_cat;
    }
    
    

    public CategorieVehicule(TypeCat nom_cat, String lieu) {
        this.nom_cat = nom_cat;
        this.lieu = lieu;

    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    
     public int get_id_cat(){
        return id_cat;
    }
    
    public void set_id_cat(int id_cat){
        this.id_cat=id_cat;
    }
    
     public TypeCat get_nom_cat(){
        return nom_cat;
    }
    
    public void set_nom_cat(TypeCat nom_cat){
        this.nom_cat=nom_cat;
    }

    @Override
    public String toString() {
        return "CategorieVehicule{" + "id_cat=" + id_cat + ", nom_cat=" + nom_cat + ", lieu=" + lieu + '}';
    }

 
    public static TypeCat enumtypecat(String s ){
    if(s.equals(TypeCat.CARGO.toString())){
        return TypeCat.CARGO;
    }
    else return TypeCat.CIVIL;
    }
    
}
