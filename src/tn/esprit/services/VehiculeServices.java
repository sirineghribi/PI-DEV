/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entity.CategorieVehicule;
import tn.esprit.entity.Maintenance;
import tn.esprit.entity.Vehicule;
import tn.esprit.tools.MaConnection;

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
            
            String sql = "insert into Vehicule(nom_vh, cat_vehicule, poid_sup, vitesse, nbr_pas, status) "
                    + "values (?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, v.getNom_vh());
            ste.setInt(2, v.getCategorieVehicule().get_id_cat());
            ste.setFloat(3, v.get_poid_sup());
            ste.setInt(4, v.get_vitesse());
            ste.setInt(5, v.get_nbr_pas());
            ste.setBoolean(6, v.getMaintenance().getStatus());
            ste.executeUpdate();
            System.out.println("vehicule ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
    @Override
    public List<Vehicule> getAll() {
       //ObservableList<Vehicule> vehicules=FXCollections.observableArrayList();
         List<Vehicule> vehicules = new ArrayList<>();
        try {
            String sql = "select * from Vehicule inner join CategorieVehicule on Vehicule.cat_vehicule = CategorieVehicule.id_cat";
                    
                    // inner join Maintenance on Vehicule.status = Maintenance.status ";                    
                    //inner join Maintenance on Vehicule.status = Maintenance.status;
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                  Vehicule v = new Vehicule(s.getInt("Vehicule.id_vehicule"),s.getString("Vehicule.nom_vh"), new CategorieVehicule(s.getInt("CategorieVehicule.id_cat"),CategorieVehicule.enumtypecat(s.getString("CategorieVehicule.nom_cat"))) , s.getFloat("Vehicule.poid_sup"), s.getInt("Vehicule.vitesse"),s.getInt("Vehicule.nbr_pas"), new Maintenance());
                          //new Maintenance(s.getBoolean("Maintenance.status")) );
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
            System.out.println("vehicule supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierVehiculeparNom(int nom_vh, Vehicule v) {
        String sql = "update Vehicule set nbr_pas=? where id_vehicule=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, nom_vh);
            ste.setInt(2,v.get_id_vehicule());
            ste.executeUpdate();
            System.out.println("vehicule modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}

   