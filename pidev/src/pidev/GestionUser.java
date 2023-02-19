/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import entity.Carte_fidelite;
import entity.Reservation;
import entity.Utilisateur;
import java.sql.Date;
import services.CarteService;
import services.ReservationService;
import services.UtilisateurService;
import tools.MaConnection;

/**
 *
 * @author Zeineb Ben Mami
 */
public class GestionUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
          
          
           UtilisateurService ut = new  UtilisateurService();
          CarteService c = new CarteService();
          ReservationService r = new ReservationService();
          
          
          Utilisateur u1 = new Utilisateur("bm","zeineb","femme","zeinebbm@gmail.com","zz",Date.valueOf("2001-06-26") );
           Utilisateur u2 = new Utilisateur("mn","imen","femme","imenmn@gmail.com","ii",Date.valueOf("2002-06-26") );
            Utilisateur u3 = new Utilisateur(5,"allani","abderahim","homme","abderahim@gmail.com","ii","a",Date.valueOf("2002-06-26") );
             Utilisateur u4=new Utilisateur();
                    
            
            // Utilisateur u4 = new Utilisateur(6,"mn","imen","femme","imenmn@gmail.com","ii","a",Date.valueOf("2002-06-26") );
            
            Carte_fidelite c1 = new Carte_fidelite(4,10,4);
         
            
            
            
            
            
          //ut.ajouter(u3);
          ut.ajouter_c(u3);
         // System.out.println(ut.getAll());
        //  ut.supprimer(u4);
        //System.out.println(ut.findById(2));
        //ut.modifier( new Utilisateur(2,"bm","sirine","femme","zeinebbm@gmail.com","zz",Date.valueOf("2001-06-26") ));
       // System.out.println(ut.trier());
        
       //carte
       
       // System.out.println(c.getAll());
        //System.out.println(c.findById(4));
       // System.out.println(c.trier());
      // c.supprimer(c1);
       
     
       
       
    }
    
}
