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
    public void ajouter(Carte_fidelite c) {
       try {
            
            String sql1 = "insert into carte_fidelite(nbr_point,id_u)"
                    + "values (?,?)";
            PreparedStatement ste1 = cnx.prepareStatement(sql1);
            ste1.setInt(1, c.getNbr_point());
            ste1.setInt(2, c.getUtilisateur().getId());
            ste1.executeUpdate();
            System.out.println(" card added successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Carte_fidelite> getAll() {
        List<Carte_fidelite> carte = new ArrayList<>();
        try {
            String sql = "select * from carte_fidelite inner join utilisateur on carte_fidelite.id_u=utilisateur.id";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Carte_fidelite u = new Carte_fidelite(s.getInt("carte_fidelite.numero"), s.getInt("carte_fidelite.nbr_point"), new Utilisateur( s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom")));
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
            
         
            String sql = "select * from carte_fidelite inner join utilisateur on carte_fidelite.id_u=utilisateur.id where numero=? ";
           
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setInt(1,id);
                 
            ResultSet s = ste.executeQuery();
            while (s.next()) {

                   Carte_fidelite u = new Carte_fidelite(s.getInt("numero"), s.getInt("nbr_point"),  new Utilisateur( s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom")));
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
          String sql = "update carte_fidelite set nbr_point=? where numero=?";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setInt(1, t.getNbr_point());
            ste.setInt(2,t.getNumero());
             
             
        ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Carte_fidelite> trier() {
         List<Carte_fidelite> carte = new ArrayList<>();
         
        try {
            
         
            String sql = "select * from carte_fidelite order by id_u ASC";
           
            PreparedStatement ste = cnx.prepareStatement(sql);
            
                 
            ResultSet s = ste.executeQuery();
            while (s.next()) {

                Carte_fidelite u = new Carte_fidelite(s.getInt("numero"), s.getInt("nbr_point"),  new Utilisateur( s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom")));
                carte.add(u);
               
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return carte;
    }
  
         
    
    public List<Carte_fidelite> findById_u(int id) {
                List<Carte_fidelite> carte = new ArrayList<>();
         
        try {
            
         
            String sql = "select * from carte_fidelite inner join utilisateur on carte_fidelite.id_u=utilisateur.id  where carte_fidelite.id_u=?";
           
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setInt(1,id);
                 
            ResultSet s = ste.executeQuery();
            while (s.next()) {

                   Carte_fidelite u = new Carte_fidelite(s.getInt("carte_fidelite.numero"), s.getInt("carte_fidelite.nbr_point"),  new Utilisateur( s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom")));
                carte.add(u);
               
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return carte;
    }
    
    public void increment_pt(Reservation t){
       
          int nb=new ReservationService().findById(t.getUtilisateur().getId()).size();
            CarteService c1=new CarteService();
            if (nb>1 && c1.findById_u(t.getUtilisateur().getId()).isEmpty() )
            {
                
                Carte_fidelite c=new Carte_fidelite(100,t.getUtilisateur());
                
                c1.ajouter(c);
                
            }
            else if(nb>1 && !c1.findById_u(t.getUtilisateur().getId()).isEmpty()){
           int nbr = c1.findById_u(t.getUtilisateur().getId()).get(0).getNbr_point();
           Carte_fidelite c = c1.findById_u(t.getUtilisateur().getId()).get(0);
           c.setNbr_point(nbr+10);
           c1.modifier(c);
               
            }
    }
  
}
