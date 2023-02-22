/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import entity.Abonnement;
import entity.Avis;
import entity.Formation;
import entity.Maintenance;
import entity.Reclamation;
import entity.Reservation;
import entity.Type_abonnement;
import entity.Utilisateur;
import entity.Vehicule;
import entity.Vol;
import java.sql.Date;
import services.AvisService;
import services.CarteService;
import services.CategorieVehiculeServices;
import services.FormationeServices;
import services.MaintenanceServices;
import services.ReclamationService;
import services.ReservationService;
import services.Type_abonnementService;
import services.UtilisateurService;
import services.VehiculeServices;
import services.VolService;
import entity.CategorieVehicule;
import tools.MaConnection;

/**
 *
 * @author lenovo
 */
public class GestionVol {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        
         //  ***********************************************    Utilisateur   ************************************************************** 
        UtilisateurService ut = new  UtilisateurService();
          CarteService c = new CarteService();

             Utilisateur u1 = new Utilisateur("bm","zeineb","femme","zeinebbm@gmail.com","zz",Date.valueOf("2001-06-26") );
             Utilisateur u2 = new Utilisateur("mn","imen","femme","imenmn@gmail.com","ii",Date.valueOf("2002-06-26") );
             Utilisateur u4=new Utilisateur();
            // ut.ajouter(u1);
        // System.out.println(ut.getAll());
        
        
        //  ***********************************************    Vols & Reservation   ************************************************************** 
        VolService vs= new VolService();
        Vehicule p1 = new Vehicule(5,"24", (float) 14, 2,5,true);
        Vol v1=new  Vol("mars","planifié",200,Date.valueOf("2024-12-12"),p1);
        Vol v2=new  Vol(12,"mars","planifié",500,Date.valueOf("2024-10-12"),p1);
        
        //vs.ajouter(v1);
        //vs.supprimer(v2);
        //System.out.println(vs.trier());
      // System.out.println(vs.findById(10));
      // vs.modifier(v2);
        //System.out.println(vs.getAll()); 
        Utilisateur u3 = new Utilisateur(2,"mn","imen","femme","imenmn@gmail.com","ii",Date.valueOf("2002-06-26") );
        ReservationService rs=new ReservationService();
        Reservation r1=new Reservation(14,  143000000, 20000000,1, Date.valueOf("2023-12-12"),v2, u3 );
       
      // rs.ajouter(r1);
       // rs.modifier_etatR(r1,"Confirmé");
        // System.out.println(rs.getAll());
        //System.out.println(rs.findById(1));
        
       
       /* 
       //***********************************   Reclamation & avis  *************************************************
       ReclamationService rs1 = new ReclamationService();
       AvisService as =new AvisService();
        Reclamation r = new Reclamation(19,"technique", "panne", 1,"non traité");
        Reclamation re1 = new Reclamation(7,"service", "retard", 2,"traité");
        Avis a = new Avis(9,"bien","tout est bien passé",1);
        //as.ajouter(a);
        //as.supprimer(a);
       // as.modifier("moyenne", a);
       // System.out.println(as.getAll());
      // rs1.ajouter(re1);
       // rs.ajouter(r1);
      // rs1.modifier("bcde", r);
      //rs1.supprimer(re1);
       // System.out.println(rs1.getAll());
       
       
        //=******************************************    Formation &  Maintenance   ****************************************************
        
        FormationeServices ss=new FormationeServices(); 
        Formation f1= new Formation(2,2,"hey",Date.valueOf("2044-01-01"),2);
       // ss.ajouter(f1);
        //System.out.println(ss.getAll());
        //ss.supprimer(f1);
        //ss.modifier(f1);
         MaintenanceServices ff=new MaintenanceServices();
         Maintenance f=new Maintenance(2,5,"alo",2.f,2.f);
        //ff.ajouter(f);
       //System.out.println(ff.getAll());
        //ff.supprimer(f);
        ff.modifier(f);
        
        
        //*********************************************    Abonnement & type abonnement   *********************************************************
        Type_abonnementService ts=new Type_abonnementService();
        Type_abonnement ta=new Type_abonnement(2,"type1", "!!!!", 12, 12);
        //ts.ajouter(ta);
        //ts.modifier(ta);
        //System.out.println(ts.getAll());
        
       // AbonnementService aservice=new AbonnementService();
        //Abonnement aa=new Abonnement(1, Date.valueOf("2024-01-01"), ta, u3);
        //aservice.ajouter(aa);
        //System.out.println(aservice.getAll());
        
        
        // ************************************    Vehicule & categorie vehicule  *********************************************************
         
        VehiculeServices vhs = new VehiculeServices();
        //Vehicule p1 = new Vehicule(5,"24", (float) 14, 2,5,true);
       //vhs.ajouter(p1);
       //vhs.supprimerVehicule(p1);
       //vhs.modifierVehiculeparNom("6", p1);
     
       // System.out.println(vhs.getAll());
        
        CategorieVehiculeServices cv = new CategorieVehiculeServices();
        CategorieVehicule c1 = new CategorieVehicule(11,"24",5);
       //cv.supprimerVehicule(c1);
        //cv.ajouter(c1);
       //System.out.println(cv.getAll());
        */
        
    }
    
}
