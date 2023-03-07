/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Formation;
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
 * @author abder
 */
public class FormationeServices implements InterfaceService<Formation> {

    Connection cnx;

    public FormationeServices() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Formation f) {
        try {
            String sql = "insert into Formation(id_c,nbr_heure,type,date)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            
            ste.setInt(1, f.getId_c());
            ste.setInt(2, f.getNbrheur());
            ste.setString(3,f.getType());
            ste.setDate(4, f.getDate());
            ste.executeUpdate();
            System.out.println("Formation ajoutÃ©e");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Formation> getAll() {
        List<Formation> Formations = new ArrayList<>();
        try {
            String sql = "select * from Formation";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Formation f = new Formation(s.getInt("id_f"), s.getInt("id_c"),s.getString("type"), s.getDate("date"),s.getInt("nbr_heure")); 
                //int id_f, int id_c, String type, Date date, int nbr_heure)
                Formations.add(f);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Formations;
    }

    @Override
    public List<Formation> findById(int id_f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




  

    @Override
    public void supprimer(Formation t) {
String sql = "delete from Formation where id_f=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_f());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }        
    }

    @Override
    public void modifier(Formation t) {
        
           String sql = "update Formation set id_c=?, type=?, date=?, nbr_heure=? where id_f=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setInt(5, t.getId_f());
            ste.setInt(1, t.getId_c());
            ste.setInt(4, t.getNbrheur());
            ste.setString(2, t.getType());
            ste.setDate(3, t.getDate());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Formation> trier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }



