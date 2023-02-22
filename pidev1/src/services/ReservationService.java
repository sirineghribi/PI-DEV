/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Carte_fidelite;
import entity.Reservation;
import entity.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import tools.MaConnection;

/**
 *
 * @author Zeineb Ben Mami
 */
public class ReservationService implements InterfaceService<Reservation>{

     Connection cnx;

    public ReservationService() {
        cnx = MaConnection.getInstance().getCnx();
    }

   
  @Override
    public void ajouter(Reservation t) {
        
       Carte_fidelite c = null;
          try {
            String sql = "insert into carte_fidelite(nbr_point,id_u)"
                    + "values (?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, c.getNbr_point());
            ste.setInt(2, c.getId_u());
          
            ste.executeUpdate();
            System.out.println(" card added successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }
    
    
     public void ajouter_r(Reservation t) {
         
         Carte_fidelite c=null;
        try{ String sql1 = "insert into reservation(id_c)"
                    + "values (?)";
       PreparedStatement ste1 = cnx.prepareStatement(sql1);
       ste1.setInt(1,t.getId_c());
       ste1.executeUpdate();
            System.out.println(" reservation added successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
      // String sql2= "SELECT COUNT(id_c)FROM reservation WHERE id_c=utilisateur.id" ;
        
       
          try {
            
            String sql = "insert into carte_fidelite(nbr_point,id_u)"
                    + "values (?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
           
            ste.setInt(1, c.getNbr_point());
            ste.setInt(2, c.getId_u());
            
           
          
            ste.executeUpdate();
            System.out.println(" card added successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
       
    }

    @Override
    public List<Reservation> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> trier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
