/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Maintenance;
import entity.Vehicule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tools.MaConnection;

/**
 *
 * @author abder
 */
public class MaintenanceServices implements InterfaceService<Maintenance> {

    Connection cnx;

    public MaintenanceServices() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Maintenance m) {
        try {
            String sql = "insert into Maintenance(id_v,duree,status,cout)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
          
            ste.setInt(1, m.getId_v().get_id_vehicule());
            ste.setFloat(2, m.getDuree());
            ste.setBoolean(3, m.getStatus());
            ste.setFloat(4, m.getCout());
            ste.executeUpdate();
            System.out.println("Maintenance ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Maintenance> getAll() {
        List<Maintenance> Maintenances = new ArrayList<>();
        try {
            String sql = "select * from Maintenance inner join vehicule on maintenance.id_v=vehicule.id_vehicule";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Vehicule v = new Vehicule(s.getInt("vehicule.id_vehicule"),s.getString("vehicule.cat_vehicule") , s.getFloat("vehicule.poid_sup"), s.getInt("vehicule.vitesse"),s.getInt("vehicule.nbr_pas"),s.getBoolean("vehicule.status")) ;
                Maintenance m = new Maintenance(s.getInt("maintenance.id_m"),v,s.getBoolean("maintenance.Status"), s.getFloat("maintenance.duree"),s.getFloat("maintenance.cout"));
               
                Maintenances.add(m);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Maintenances;
    }

    @Override
    public List<Maintenance> findById(int id_m) {
        List<Maintenance> Maintenances = new ArrayList<>();
        try {
            String sql = "select * from Maintenance inner join vehicule on maintenance.id_v=vehicule.id_vehicule where maintenance.id_m=?";
            PreparedStatement ste=cnx.prepareStatement(sql);
            ste.setInt(1,id_m);
            ResultSet s = ste.executeQuery();
            while (s.next()) {
                Vehicule v = new Vehicule(s.getInt("vehicule.id_vehicule"),s.getString("vehicule.cat_vehicule") , s.getFloat("vehicule.poid_sup"), s.getInt("vehicule.vitesse"),s.getInt("vehicule.nbr_pas"),s.getBoolean("vehicule.status")) ;
                Maintenance m = new Maintenance(s.getInt("maintenance.id_m"),v,s.getBoolean("maintenance.Status"), s.getFloat("maintenance.duree"),s.getFloat("maintenance.cout"));
               
                Maintenances.add(m);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Maintenances;
        }
    public List<Maintenance> findByStatus(boolean b) {
        List<Maintenance> Maintenances = new ArrayList<>();
        try {
            String sql = "select * from Maintenance inner join vehicule on maintenance.id_v=vehicule.id_vehicule where maintenance.status=?";
            PreparedStatement ste=cnx.prepareStatement(sql);
            ste.setBoolean(1,b);
            ResultSet s = ste.executeQuery();
            while (s.next()) {
                Vehicule v = new Vehicule(s.getInt("vehicule.id_vehicule"),s.getString("vehicule.cat_vehicule") , s.getFloat("vehicule.poid_sup"), s.getInt("vehicule.vitesse"),s.getInt("vehicule.nbr_pas"),s.getBoolean("vehicule.status")) ;
                Maintenance m = new Maintenance(s.getInt("maintenance.id_m"),v,s.getBoolean("maintenance.Status"), s.getFloat("maintenance.duree"),s.getFloat("maintenance.cout"));
               
                Maintenances.add(m);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Maintenances;
        }


  

    @Override
    public void supprimer(Maintenance t) {
           String sql = "delete from Maintenance where id_m=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_m());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            }

    @Override
    public void modifier(Maintenance t) {
        String sql = "update Maintenance set id_v=?, status=?, cout=?, duree=? where id_m=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setInt(5, t.getId_m());
            ste.setInt(1, t.getId_v().get_id_vehicule());
            ste.setFloat(4, t.getDuree());
            ste.setBoolean(2, t.getStatus());
            ste.setFloat(3, t.getCout());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Maintenance> trier() {
        return getAll().stream().sorted().collect(Collectors.toList());
    }

    public boolean Mexists(int id){
    
        return !findById(id).isEmpty();
    
    }
    
    public Maintenance getM(int id){
    if (Mexists (id))
        return findById(id).get(0);
    else {
        System.out.println("Does not exist");
        return null;
    }
    }
    
}