/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Maintenance;
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
          
            ste.setInt(1, m.getId_v());
            ste.setFloat(2, m.getDuree());
            ste.setString(3, m.getStatus());
            ste.setFloat(4, m.getCout());
            ste.executeUpdate();
            System.out.println("Maintenance ajoutÃ©e");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Maintenance> getAll() {
        List<Maintenance> Maintenances = new ArrayList<>();
        try {
            String sql = "select * from Maintenance";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Maintenance m = new Maintenance(s.getInt("id_m"), s.getInt("id_v"),s.getString("Status"), s.getFloat("duree"),s.getFloat("cout"));
               
                Maintenances.add(m);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Maintenances;
    }

    @Override
    public List<Maintenance> findById(int id_f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            ste.setInt(1, t.getId_v());
            ste.setFloat(4, t.getDuree());
            ste.setString(2, t.getStatus());
            ste.setFloat(3, t.getCout());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Maintenance> trier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}