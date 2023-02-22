/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Carte_fidelite;
import entity.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import tools.MaConnection;

/**
 *
 * @author Zeineb Ben Mami
 */
public class CarteService implements InterfaceService<Carte_fidelite>{

    
    Connection cnx;

    public CarteService() {
         cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Carte_fidelite t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Carte_fidelite> getAll() {
        List<Carte_fidelite> carte = new ArrayList<>();
        try {
            String sql = "select * from carte_fidelite";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Carte_fidelite u = new Carte_fidelite(s.getInt("numero"), s.getInt("nbr_point"), s.getInt("id_u"));
                carte.add(u);
               
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return carte;
    }

    @Override
    public List<Carte_fidelite> findById(int id) {
                List<Carte_fidelite> carte = new ArrayList<>();
         
        try {
            
         
            String sql = "select * from carte_fidelite where numero=?";
           
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setInt(1,id);
                 
            ResultSet s = ste.executeQuery();
            while (s.next()) {

                   Carte_fidelite u = new Carte_fidelite(s.getInt("numero"), s.getInt("nbr_point"), s.getInt("id_u"));
                carte.add(u);
               
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return carte;
    }

    @Override
    public void supprimer(Carte_fidelite t) {
            String sql = "delete from carte_fidelite where numero=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getNumero());
            ste.executeUpdate();
            System.out.println("card deleted ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Carte_fidelite t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Carte_fidelite> trier() {
         List<Carte_fidelite> carte = new ArrayList<>();
         
        try {
            
         
            String sql = "select * from carte_fidelite order by id_u ASC";
           
            PreparedStatement ste = cnx.prepareStatement(sql);
            
                 
            ResultSet s = ste.executeQuery();
            while (s.next()) {

                Carte_fidelite u = new Carte_fidelite(s.getInt("numero"), s.getInt("nbr_point"), s.getInt("id_u"));
                carte.add(u);
               
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return carte;
    }
  
         
    
    
  
}
