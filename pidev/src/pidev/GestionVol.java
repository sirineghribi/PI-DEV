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
import entity.Genre;
import java.time.LocalDate;
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
        Utilisateur u3 = new Utilisateur(2,"mn","imen",Genre.femme,"imenmn@gmail.com","ii",Date.valueOf("2002-06-26") );
        ReservationService rs=new ReservationService();
        Reservation r1=new Reservation(14,  143000000, 20000000,1,  Date.valueOf(LocalDate.now()),v2, u3 );
       
       rs.ajouter(r1);
       // rs.modifier_etatR(r1,"Confirmé");
        // System.out.println(rs.getAll());
        //System.out.println(rs.findById(1));
        
      
        
    }
    
}
