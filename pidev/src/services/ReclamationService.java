/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Reclamation;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import tools.MaConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author sirin
 */
public class ReclamationService implements InterfaceService<Reclamation>{
Connection cnx;

    public ReclamationService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouter(Reclamation t) {
        try {
            String sql = "insert into reclamation(id_rec,type,description,id_c,etat)"
                    + "values (?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_rec());
            ste.setString(2, t.getType());
            ste.setString(3, t.getDescription());
            ste.setInt(4, t.getId_c());
            ste.setString(5, t.getEtat());
            ste.executeUpdate();
            System.out.println("****Reclamation ajoutée**");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> getAll() {
        List<Reclamation> reclamations = new ArrayList<>();
        try {
            String sql = "select * from reclamation";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Reclamation r = new Reclamation(s.getInt(1),s.getString(2),s.getString(3), s.getInt(4),s.getNString(5)
                        );
                reclamations.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }

    
@Override
    public void supprimer(Reclamation t) {
        if(t.getEtat().equals("non traité")){
        String sql = "delete from reclamation where id_rec=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1,t.getId_rec());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
        else{System.err.println("impossible votre reclamation est déja traitée");}
    }

   
    public void modifier(String type,Reclamation t) {
          if(t.getEtat().equals("non traité"))  
          { 
         String sql = "update reclamation set type=? where id_rec=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, type);
            ste.setInt(2,t.getId_rec());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("bien modifié");
        }
        }
        else{System.err.println("impossible votre reclamation est déja traitée");
           }
    }
    @Override
    public void modifier(Reclamation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> trier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    

