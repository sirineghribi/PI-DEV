/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piproject;

import entity.Formation;
import entity.Maintenance;
import entity.Vehicule;
import java.sql.Date;
import services.FormationeServices;
import services.MaintenanceServices;

/**
 *
 * @author abder
 */
public class Piproject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FormationeServices ss=new FormationeServices(); 
        System.out.println(ss.findById(0));
        //System.out.println(ss.getAll());
//Formation f1= new Formation(1,1,"awdas",Date.valueOf("2044-01-01"),2);
       //ss.ajouter(f1);
        //System.out.println(ss.getAll()
        //);
        //ss.supprimer(f1);
        //ss.modifier(f1);
    MaintenanceServices ff=new MaintenanceServices();
     //Vehicule v = new Vehicule();
     //v.set_id_vehicule(4);
     //Maintenance f=new Maintenance(5,v,false,2.f,2.f);
    //ff.ajouter(f);
        //System.out.println(ff.getAll());
        //System.out.println(ff.findById(4));
        //Maintenance m= ff.getM(4);
        //System.out.println(m);
        //System.out.println(ff.findByStatus(false));
        //ff.supprimer(f);
       // ff.modifier(f);
       
            
    }
    
    
}
