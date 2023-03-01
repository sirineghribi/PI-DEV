/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import tn.esprit.entity.CategorieVehicule;
import tn.esprit.entity.Vehicule;
import tn.esprit.services.CategorieVehiculeServices;
import tn.esprit.services.VehiculeServices;
import tn.esprit.entity.TypeCat;
import tn.esprit.entity.Maintenance;
import tn.esprit.services.MaintenanceServices;

/**
 *
 * @author ASUS
 */
public class Pidev3a27 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        CategorieVehiculeServices cv = new CategorieVehiculeServices();
        MaintenanceServices ms= new  MaintenanceServices();
        Maintenance m1= new Maintenance(1,false,(float)2.5,(float)2.5);
        CategorieVehicule c1 = new CategorieVehicule(TypeCat.CARGO, "New Jersey");
        CategorieVehicule c2 = new CategorieVehicule(16, TypeCat.CARGO, "New Jersey");
         //cv.supprimerCat(c2);
        //cv.ajouter(c1);
        System.out.println(cv.getAll());
        
        VehiculeServices vs = new VehiculeServices();
        Vehicule p1 = new Vehicule(10,"spaceV",c1,(float)1.2,9,7,true);
        Vehicule p2 = new Vehicule("spaceT",c2,(float)1.2,9,7,false); //(int id, CategorieVehicule CategorieVehicule, float poid, int vitesse, int passager, Maintenance maintenance)
      //  vs.ajouter(p2);
       // vs.supprimerVehicule(p1);
       //vs.modifierVehiculeparNom(14, p1);
      // vs.supprimerVehicule(p1);
    //  System.out.println(vs.getAll());
        
        
        
    }
    
}
