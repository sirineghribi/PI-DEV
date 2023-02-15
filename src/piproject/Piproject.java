/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piproject;

import entity.Formation;
import entity.Maintenance;
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
        //FormationeServices ss=new FormationeServices(); 
        //Formation f1= new Formation(2,2,"ali",Date.valueOf("2044-01-01"),2);
       // ss.ajouter(f1);
        //System.out.println(ss.getAll()
        //);
        //ss.supprimer(f1);
        //ss.modifier(f1);
    MaintenanceServices ff=new MaintenanceServices();
    Maintenance f=new Maintenance(5,5,"same7",2.f,2.f);
    //ff.ajouter(f);
        //System.out.println(ff.getAll());
        //ff.supprimer(f);
        ff.modifier(f);
            
    }
    
    
}
