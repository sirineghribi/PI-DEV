/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Vehicule;
import tools.MaConnection;

/**
 *
 * @author ASUS
 */
public class VehiculeServices implements InterfaceService<Vehicule> {

    Connection cnx;

    public VehiculeServices() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Vehicule v) {
        try {
            
            String sql = "insert into Vehicule(id_vehicule, cat_vehicule, poid_sup, vitesse, nbr_pas, status) "
                    + "values (?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, v.get_id_vehicule());
            ste.setString(2, v.get_cat_vehicule());
            ste.setFloat(3, v.get_poid_sup());
            ste.setInt(4, v.get_vitesse());
            ste.setInt(5, v.get_nbr_pas());
            ste.setBoolean(6, v.get_status());
            ste.executeUpdate();
            System.out.println("vehicule ajoutÃ©e");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public List<Vehicule> getAll() {
        List<Vehicule> vehicules = new ArrayList<>();
        try {
            String sql = "select * from Vehicule";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Vehicule v = new Vehicule(s.getInt(1), s.getString("cat_vehicule"), s.getFloat(3), s.getInt(4),s.getInt(5), s.getBoolean(6));
                vehicules.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return vehicules;
    }

    @Override
    public List<Vehicule> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

public void supprimerVehicule(Vehicule v) {
        String sql = "delete from Vehicule where id_vehicule = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, v.get_id_vehicule());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierVehiculeparNom(String nom_vehicule, Vehicule v) {
        String sql = "update Vehicule set cat_vehicule=? where id_vehicule=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom_vehicule);
            ste.setInt(2,v.get_id_vehicule());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Vehicule t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Vehicule t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vehicule> trier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}