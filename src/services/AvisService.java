/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Avis;
import tools.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sirin
 */
public class AvisService implements InterfaceService<Avis>{
Connection cnx;
    public AvisService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouter(Avis t) {
        try {
            String sql = "insert into avis(id_avis,note,description,id_c)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_avis());
            ste.setString(2, t.getNote());
            ste.setString(3, t.getDescription());
            ste.setInt(4, t.getId_c());
           
            ste.executeUpdate();
            System.out.println("****Avis ajoutÃ©e**");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Avis> getAll() {
        List<Avis> lavis = new ArrayList<>();
        try {
            String sql = "select * from avis";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Avis a = new Avis(s.getInt(1),s.getString(2),s.getString(3),s.getInt(4));
                lavis.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lavis;
    }

    
    public void supprimer(Avis t) {
        
        String sql = "delete from avis where id_avis=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1,t.getId_avis());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      System.out.println("suppression avec success");
    }

   
    public void modifier(String note,Avis t) {
         
         String sql = "update avis set note=? where id_avis=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, note);
            ste.setInt(2,t.getId_avis());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
      System.out.println("modifiÃ© avec success");

    }

    @Override
    public void modifier(Avis t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Avis> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Avis> trier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

  